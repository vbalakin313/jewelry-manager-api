package ru.vbalakin.jewelrymanagerapi.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    @JsonProperty("ccn3")
    private int numericCode;

    public int getNumericCode() {
        return numericCode;
    }
}
