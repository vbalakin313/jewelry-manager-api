package ru.vbalakin.jewelrymanagerapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CountryCodeServiceTest {

    @Test
    void getNumericCountryCode_whenCountryNameIsValid_returnsCountryCode() {
        String countryName = "Belgium";
        CountryCodeService countryCodeService = new CountryCodeService();

        ClientCountryCodeEntity clientCountryCode = countryCodeService.getNumericCountryCode(countryName);

        assertNotNull(clientCountryCode.getCountryCode());
    }
}