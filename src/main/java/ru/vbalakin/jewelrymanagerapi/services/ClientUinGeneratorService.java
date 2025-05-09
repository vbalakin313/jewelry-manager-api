package ru.vbalakin.jewelrymanagerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.BadRequestException;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;

import java.time.Year;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientUinGeneratorService {

    private CountryCodeService countryCodeService;
    private ClientRepository clientRepository;

    public String clientUinGenerated(UUID id){

        String year = Year.now().toString().substring(2);

        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new BadRequestException("Client id is not valid")
                );

        ClientCountryCodeEntity code = countryCodeService.getNumericCountryCode(client.getCountry());
        String countryCode = String.valueOf(code.getCountryCode());




        //TODO:SSS-YY-NNNNNNNNNN-C

        return countryCode + year;
    }
}
