package ru.vbalakin.jewelrymanagerapi.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vbalakin.jewelrymanagerapi.domain.models.Country;

@Service
public class CountryCodeService {

    private static final String API_URL = "https://restcountries.com/v3.1/name/";

    public String getNumericCountryCode(String countryName) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + countryName;

        try {
            Country[] countries = restTemplate.getForObject(url, Country[].class);
            if (countries != null && countries.length > 0) {
                return countries[0].getNumericCode(); // "276" для Germany
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
