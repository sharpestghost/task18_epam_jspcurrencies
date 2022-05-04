package com.epam.rd.jsp.currencies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Currencies {
    private final Map<String, BigDecimal> currenciesData = new TreeMap<>();
    public void addCurrency(String currency, BigDecimal weight) {
        currenciesData.put(currency, weight);
    }

    public Collection<String> getCurrencies() {
        return currenciesData.keySet();
    }

    public Map<String, BigDecimal> getExchangeRates(String referenceCurrency) {
        Map<String, BigDecimal> newMap = new TreeMap<>();
        if (currenciesData.containsKey(referenceCurrency)) {
            currenciesData.forEach((String key, BigDecimal element) -> newMap.put(key,
                    currenciesData.get(referenceCurrency).divide(element,5,RoundingMode.HALF_UP)));
        }
        return newMap;
    }
    public BigDecimal convert(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        return currenciesData.get(sourceCurrency).multiply(amount).divide(currenciesData.get(targetCurrency),5,RoundingMode.HALF_UP);
    }

}
