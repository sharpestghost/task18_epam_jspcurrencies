package com.epam.rd.jsp.currencies;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Currencies {
    private Map<String, BigDecimal> curs = new TreeMap<>();

    public void addCurrency(String currency, BigDecimal weight) {
        throw new UnsupportedOperationException();
    }

    public Collection<String> getCurrencies() {
        throw new UnsupportedOperationException();
    }

    public Map<String, BigDecimal> getExchangeRates(String referenceCurrency) {
        throw new UnsupportedOperationException();
    }

    public BigDecimal convert(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        throw new UnsupportedOperationException();
    }
}
