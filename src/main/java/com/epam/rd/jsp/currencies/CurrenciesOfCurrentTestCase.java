package com.epam.rd.jsp.currencies;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CurrenciesOfCurrentTestCase extends Currencies {
    private static final Logger LOGGER = Logger.getGlobal();
    private static final Collector<CharSequence, ?, String> COLLECTION_FORMATTER = Collectors.joining(" </li><li>");
    private static final String REGEX_ARGUMENT = "\\s";

    public CurrenciesOfCurrentTestCase() {
        final Path testCasePath = Paths.get("src", "test", "resources", "test-cases", "current.txt");
        try (Stream<String> lines = Files.lines(testCasePath, UTF_8)) {
            lines
                    .map(line -> line.split(REGEX_ARGUMENT))
                    .forEach(tokens -> addCurrency(tokens[0], new BigDecimal(tokens[1])));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "RuntimeException", e);
        }
    }
    public String mapToString(String currency) {
        return getExchangeRates(currency).entrySet().stream().filter(entry -> !entry.getKey().equals(currency))
                .map(entry -> "1 " + currency + " = " + entry.getValue() + " " + entry.getKey())
                .collect(COLLECTION_FORMATTER);

    }

    public String listToString() {
        return getCurrencies().stream().collect(COLLECTION_FORMATTER);
    }
}
