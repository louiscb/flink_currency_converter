package com.louiscb.currencyconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CurrencyConverter {
    // Static conversion rate
    private static final double USD_TO_EUR_CONVERSION_RATE = 0.85;

    public static String convertCurrency(String record) {
        // Create a Gson instance
        Gson gson = new Gson();

        // Convert the JSON record to a JsonObject
        JsonObject jsonObject = gson.fromJson(record, JsonObject.class);

        // Extract the price from the JsonObject
        double originalPrice = jsonObject.get("price_usd").getAsDouble();

        // Convert the price to the target currency (EUR in this case)
        double convertedPrice = originalPrice * USD_TO_EUR_CONVERSION_RATE;
        BigDecimal roundedConvertedPrice = BigDecimal.valueOf(convertedPrice).setScale(2, RoundingMode.HALF_UP);

        jsonObject.addProperty("price_eur", roundedConvertedPrice);
        return gson.toJson(jsonObject);
    }
}
