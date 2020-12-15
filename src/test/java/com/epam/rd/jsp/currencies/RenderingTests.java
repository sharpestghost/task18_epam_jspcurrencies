package com.epam.rd.jsp.currencies;

import com.google.common.collect.ImmutableMap;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xhtmlrenderer.simple.Graphics2DRenderer;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


class RenderingTests {

    @Test
    void testSmall() throws IOException, URISyntaxException {

        initTestCase("small");

        testCase("currencies.jsp", ImmutableMap.of(), "small-crs.png");

        testCase("exchangeRates.jsp", ImmutableMap.of("from", "GBP"), "small-er-gbp.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "USD"), "small-er-usd.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "EUR"), "small-er-eur.png");

        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "USD", "amount", 36.6),
                "small-cvt-gbp-usd-366.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "USD", "amount", 123.123),
                "small-cvt-gbp-usd-123132.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "EUR", "amount", 78.88),
                "small-cvt-gbp-usd-7888.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "EUR", "to", "USD", "amount", 55.5),
                "small-cvt-eur-usd-555.png");
    }

    @Test
    void testMedium() throws IOException, URISyntaxException {

        initTestCase("medium");

        testCase("currencies.jsp", ImmutableMap.of(), "medium-crs.png");

        testCase("exchangeRates.jsp", ImmutableMap.of("from", "GBP"), "medium-er-gbp.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "USD"), "medium-er-usd.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "EUR"), "medium-er-eur.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "NOK"), "medium-er-nok.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "CNY"), "medium-er-cny.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "BRL"), "medium-er-brl.png");

        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "USD", "amount", 36.6),
                "medium-cvt-gbp-usd-366.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "USD", "amount", 123.123),
                "medium-cvt-gbp-usd-123132.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "EUR", "amount", 78.88),
                "medium-cvt-gbp-usd-7888.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "EUR", "to", "USD", "amount", 55.5),
                "medium-cvt-eur-usd-555.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "NOK", "to", "CNY", "amount", 10.1),
                "medium-cvt-nok-cny-101.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "BRL", "to", "GBP", "amount", 1133),
                "medium-cvt-brl-gbp-1133.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "EUR", "to", "CNY", "amount", 7.7),
                "medium-cvt-eur-cny-77.png");
    }

    @Test
    void testLarge() throws IOException, URISyntaxException {

        initTestCase("large");

        testCase("currencies.jsp", ImmutableMap.of(), "large-crs.png");

        testCase("exchangeRates.jsp", ImmutableMap.of("from", "GBP"), "large-er-gbp.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "USD"), "large-er-usd.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "EUR"), "large-er-eur.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "NOK"), "large-er-nok.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "CNY"), "large-er-cny.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "BRL"), "large-er-brl.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "JPY"), "large-er-jpy.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "SEK"), "large-er-sek.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "RUB"), "large-er-rub.png");
        testCase("exchangeRates.jsp", ImmutableMap.of("from", "PLN"), "large-er-pln.png");

        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "USD", "amount", 36.6),
                "large-cvt-gbp-usd-366.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "USD", "amount", 123.123),
                "large-cvt-gbp-usd-123132.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "GBP", "to", "EUR", "amount", 78.88),
                "large-cvt-gbp-usd-7888.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "EUR", "to", "USD", "amount", 55.5),
                "large-cvt-eur-usd-555.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "NOK", "to", "CNY", "amount", 10.1),
                "large-cvt-nok-cny-101.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "BRL", "to", "GBP", "amount", 1133),
                "large-cvt-brl-gbp-1133.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "EUR", "to", "CNY", "amount", 7.7),
                "large-cvt-eur-cny-77.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "RUB", "to", "USD", "amount", 5560),
                "large-cvt-rub-usd-5560.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "PLN", "to", "SEK", "amount", 2323),
                "large-cvt-pln-sek-2323.png");
        testCase("convert.jsp",
                ImmutableMap.of("from", "JPY", "to", "CNY", "amount", 7825),
                "large-cvt-jpy-cny-7825.png");
    }

    private static Tomcat tomcat;

    @BeforeAll
    public static void startServer() throws ServletException, LifecycleException {

        int port = 8080;

        tomcat = new Tomcat();
        tomcat.setPort(port);

        String webappDirLocation = "src/main/webapp/";
        StandardContext ctx = (StandardContext) tomcat.addWebapp(
                "", new File(webappDirLocation).getAbsolutePath()
        );
        System.out.println(
                "configuring app with basedir: "
                        + new File("./" + webappDirLocation).getAbsolutePath()
        );

        File additionWebInfClasses = new File("target/classes");
        File jstlLib = new File("src/test/resources/taglib");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(
                        resources,
                        "/WEB-INF/classes",
                        additionWebInfClasses.getAbsolutePath(), "/"));
        resources.addPreResources(
                new DirResourceSet(
                        resources,
                        "/WEB-INF/lib",
                        jstlLib.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();

    }

    @AfterAll
    public static void stopServer() throws LifecycleException, IOException {
        tomcat.stop();
        initTestCase("small");
    }

    private static void testCase(final String page,
                                 final ImmutableMap<String, Object> params,
                                 final String refImageName) throws URISyntaxException, IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final URI uri = buildRequestUri(page, params);
            final Document responseXhtml = executeRequest(httpClient, uri);
            final BufferedImage rendered = renderPage(responseXhtml);

            final double diffPercentage = computeDiff(rendered, "src/test/resources/ref-img/" + refImageName);
            System.out.println("diff percentage: " + diffPercentage);
            assertTrue(diffPercentage < 0.1);
        }
    }

    private static double computeDiff(BufferedImage rendered, String refPath) throws IOException {
        BufferedImage reference = ImageIO.read(new File(refPath));
        int w1 = rendered.getWidth();
        int w2 = reference.getWidth();
        int h1 = rendered.getHeight();
        int h2 = reference.getHeight();
        if ((w1 != w2) || (h1 != h2)) {
            throw new IllegalArgumentException("Images sizes are different");
        } else {
            long diff = 0;
            for (int j = 0; j < h1; j++) {
                for (int i = 0; i < w1; i++) {
                    int pixel1 = rendered.getRGB(i, j);
                    Color color1 = new Color(pixel1, true);
                    int r1 = color1.getRed();
                    int g1 = color1.getGreen();
                    int b1 = color1.getBlue();
                    int pixel2 = reference.getRGB(i, j);
                    Color color2 = new Color(pixel2, true);
                    int r2 = color2.getRed();
                    int g2 = color2.getGreen();
                    int b2 = color2.getBlue();

                    long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
                    diff = diff + data;
                }
            }
            double avg = ((double) diff) / (w1 * h1 * 3);
            double percentage = (avg / 255) * 100;
            return percentage;
        }
    }

    private static BufferedImage renderPage(final Document respDoc) throws IOException {

        Dimension dim = new Dimension(600, 1000);

        BufferedImage image = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        final Graphics2DRenderer renderer = new Graphics2DRenderer();
        renderer.setDocument(respDoc, "");
        renderer.layout(graphics, dim);
        renderer.render(graphics);

        Rectangle rect = renderer.getMinimumSize();

        image = new BufferedImage((int) rect.getWidth(), (int) rect.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        renderer.layout(graphics, rect.getSize());
        renderer.render(graphics);
        return image;
    }

    private static Document executeRequest(final CloseableHttpClient httpClient, final URI uri) throws IOException {
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        final String responseText = EntityUtils.toString(response.getEntity()).trim();
        return respXhtml(responseText);
    }

    private static URI buildRequestUri(final String page, final ImmutableMap<String, Object> params) throws URISyntaxException {
        final URIBuilder uriBuilder = new URIBuilder()
                .setScheme("http")
                .setHost("localhost:8080")
                .setPath("/" + page);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            uriBuilder.setParameter(param.getKey(), String.valueOf(param.getValue()));
        }

        return uriBuilder.build();
    }

    private static Document respXhtml(final String responseText) {
        W3CDom w3cDom = new W3CDom();
        return w3cDom.fromJsoup(Jsoup.parse(responseText));
    }

    private static void initTestCase(final String testCase) throws IOException {
        Files.copy(
                Paths.get("src/test/resources/test-cases/" + testCase + ".txt"),
                Paths.get("src/test/resources/test-cases/current.txt"),
                StandardCopyOption.REPLACE_EXISTING);
    }

}