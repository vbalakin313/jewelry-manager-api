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

        String uuidHash = String.valueOf(id.hashCode());
        String codePart = uuidHash.replace("-","");
        codePart = codePart.substring(0, Math.min(10, codePart.length()));
        codePart = String.format("%10s", codePart).replace(' ','0');

        WeightedSumLastNumber weightedSumLastNumber = new WeightedSumLastNumber(client);
        int lastNum = (int)weightedSumLastNumber.weightedSum(client);

        return countryCode + year + codePart + lastNum;
    }
}
