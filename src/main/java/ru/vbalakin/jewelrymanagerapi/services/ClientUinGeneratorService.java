package ru.vbalakin.jewelrymanagerapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vbalakin.jewelrymanagerapi.entities.ClientCountryCodeEntity;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.BadRequestException;
import ru.vbalakin.jewelrymanagerapi.exceptions.UinGenerationException;
import ru.vbalakin.jewelrymanagerapi.repositories.ClientRepository;

import java.time.Year;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientUinGeneratorService {

    private static final int MAX_ATTEMPTS = 5;
    private static final long UNIQUE_PART_MODULE = 10_000_000_000L;

    private final CountryCodeService countryCodeService;
    private final ClientRepository clientRepository;

    public String generateClientUin(UUID id){

        String year = Year.now().toString().substring(2);

        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new BadRequestException("Client id is not valid")
                );

        String country = client.getCountry();
        if (country == null){
            throw new BadRequestException("Country is null or does not exist");
        }

        ClientCountryCodeEntity code = countryCodeService.getNumericCountryCode(country);
        String countryCode = String.valueOf(code.getCountryCode());

        String uniquePart = generateNumericUniquePart(id);

        WeightedSumLastNumber weightedSumLastNumber = new WeightedSumLastNumber(client);
        int lastNum = (int)weightedSumLastNumber.weightedSum(client);

        String generatedUin = countryCode + year + uniquePart + lastNum;

        int attempts = 0;
        while (clientRepository.existsByUin_Uin(generatedUin) && attempts++ < MAX_ATTEMPTS){
            generatedUin = countryCode + year + generateNumericUniquePart(UUID.randomUUID()) + lastNum;
        }

        if (attempts >= MAX_ATTEMPTS){
            throw new UinGenerationException("Maximum generation attempts " + MAX_ATTEMPTS
                    + " exceeded" + "\nLast generation uin " + generatedUin);
        }

        return generatedUin;
    }

    private String generateNumericUniquePart(UUID id) {
        long mostSignificantBits = id.getMostSignificantBits();
        long leastSignificantBits = id.getLeastSignificantBits();

        long numericPart = Math.abs((mostSignificantBits ^ leastSignificantBits) % UNIQUE_PART_MODULE);

        return String.format("%010d", numericPart);
    }

}
