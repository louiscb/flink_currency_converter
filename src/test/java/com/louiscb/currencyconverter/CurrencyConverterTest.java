package com.louiscb.currencyconverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyConverterTest {

    @Test
    void convertCurrency() {
        String record = "{\"event_time\":\"2023-07-20T11:25:51.336762\",\"ticker\":\"AAPL\",\"price_usd\":100.00}";
        String expected = "{\"event_time\":\"2023-07-20T11:25:51.336762\",\"ticker\":\"AAPL\",\"price_usd\":100.00,\"price_eur\":85.00}";

        String actual = CurrencyConverter.convertCurrency(record);

        assertEquals(expected, actual);
    }
}