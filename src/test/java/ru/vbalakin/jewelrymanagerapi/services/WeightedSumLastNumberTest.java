package ru.vbalakin.jewelrymanagerapi.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.entities.ClientEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeightedSumLastNumberTest {

    @Test
    void weightedSum() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setName("John");
        clientEntity.setSurname("Doe");
        clientEntity.setCountry("Russia");

        WeightedSumLastNumber weightedSumLastNumber = new WeightedSumLastNumber(clientEntity);

        Double ws = weightedSumLastNumber.weightedSum(clientEntity);
        assertNotNull(ws);
    }
}