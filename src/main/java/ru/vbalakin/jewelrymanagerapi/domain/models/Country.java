package ru.vbalakin.jewelrymanagerapi.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    @JsonProperty("ccn3")
    private String numericCode;

    public String getNumericCode() {
        return numericCode;
    }
}
