package ru.vbalakin.jewelrymanagerapi.services;

import ru.vbalakin.jewelrymanagerapi.domain.enums.Gender;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;

import java.util.HashMap;
import java.util.Map;


public class WeightedSumLastNumber {

    public WeightedSumLastNumber(ClientEntity client) {

    }

    public double weightedSum(ClientEntity client) {
        Gender gender = client.getGender();

        Map<String, Double> weights = new HashMap<>();
        weights.put("gender", 0.3);
        weights.put("country", 1.0);

        Double genderValue = (gender == Gender.MALE) ? 1.0 : 0.0;
        Double countryValue = mapCountryValue(client.getCountry());

        return (weights.get("gender") * genderValue) + (weights.get("country") * countryValue);
    }

    private double mapCountryValue(String country){
        if(country == null || country.isEmpty()) return 0.0;

        return switch (country){
            case "Russia" -> 1.0;
            case "Netherlands" -> 1.2;
            case "United Kingdom" -> 2.0;
            case "Sweden" -> 2.3;
            case "Canada" -> 2.5;
            case "Australia" -> 2.1;
            case "New Zealand" -> 2.2;
            case "Ireland" -> 2.6;
            case "USA" -> 1.7;
            case "Singapore" -> 2.4;
            case "Japan" -> 1.3;
            case "France" -> 1.9;
            default -> {
                int hash = Math.abs(country.hashCode());
                double value = 1.5 + (hash % 150) / 100.0;
                yield Math.min(value, 3.0);
            }
        };
    }
}

