package ru.vbalakin.jewelrymanagerapi.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.vbalakin.jewelrymanagerapi.domain.models.Country;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.BadRequestException;

@Service
public class CountryCodeService {
    private static final String API_URL = "https://restcountries.com/v3.1/name/";

    public ClientCountryCodeEntity getNumericCountryCode(String countryName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + countryName;

        try {
            Country[] countries = restTemplate.getForObject(url, Country[].class);
            if (countries != null && countries.length > 0) {

                return ClientCountryCodeEntity
                        .builder()
                        .countryCode(countries[0].getNumericCode())
                        .build();
            }
        } catch (BadRequestException e) {
            throw new BadRequestException("This country name is not valid");
        }

        return null;
    }
}
