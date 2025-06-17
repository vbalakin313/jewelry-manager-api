package ru.vbalakin.jewelrymanagerapi.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vbalakin.jewelrymanagerapi.domain.helper.ControllerHelper;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryOfAdditionDto;
import ru.vbalakin.jewelrymanagerapi.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HistoryControllerTest {

    @Mock
    ControllerHelper controllerHelper;

    @InjectMocks
    HistoryController historyController;

    @Test
    void getHistoryOfAdditions_whenHistoryOfAdditionsIsExist() {
        String uin = "8402552457377752";

        HistoryOfAdditionDto history = new HistoryOfAdditionDto();

        Mockito.when(controllerHelper.getHistoryOrThrowException(uin)).thenReturn(history);

        HistoryOfAdditionDto result = historyController.getHistoryOfAdditions(uin);

        assertNotNull(result);
    }

    @Test
    void getHistoryOfAdditions_whenHistoryOfAdditionsIsNotExist() {
        String uin = "000000000000000";

        Mockito.when(controllerHelper.getHistoryOrThrowException(uin)).thenThrow(new NotFoundException("History not found"));

        assertThrows(NotFoundException.class, () -> historyController.getHistoryOfAdditions(uin));
    }

    @Test
    void updateHistoryOfAdditions_shouldUpdateHistoryOfAdditions() {
        String uin = "8402552457377752";

        HistoryOfAdditionDto history = new HistoryOfAdditionDto();

        Mockito.when(controllerHelper.getHistoryOrThrowException(uin)).thenReturn(history);

        HistoryOfAdditionDto result = historyController.updateHistoryOfAdditions(uin);

        assertNotNull(result);
        assertEquals(history, result);
    }

    @Test
    void updateHistoryOfAdditions_whenNotFound_shouldThrowNotFoundException() {
        String uin = "000000000000000";

        Mockito.when(controllerHelper.getHistoryOrThrowException(uin)).thenThrow(new NotFoundException("History not found"));

        assertThrows(NotFoundException.class, () -> historyController.updateHistoryOfAdditions(uin));
    }

    @Test
    void cacheEvictByUin_whenCacheEvictByUin() {
        String uin = "8402552457377752";
        String expected = "Cache for UIN " + uin + " evicted";

        String result = historyController.cacheEvictByUin(uin);

        assertEquals(expected, result);
    }


}