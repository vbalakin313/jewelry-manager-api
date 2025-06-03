package ru.vbalakin.jewelrymanagerapi.controllers;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vbalakin.jewelrymanagerapi.domain.helper.ControllerHelper;
import ru.vbalakin.jewelrymanagerapi.dto.historyDtos.HistoryOfAdditionDto;


@RestController
@Transactional
@AllArgsConstructor
public class HistoryController {

    private static final String HISTORY_OF_ADDITIONS = "/api/v1/history";
    private static final String HISTORY_OF_ADDITIONS_UPDATE = "/api/v1/history/update";
    private static final String HISTORY_OF_ADDITIONS_CACHE_EVICT = "/api/v1/history/evict";

    private final ControllerHelper controllerHelper;

    @Cacheable(value = "history", key = "#uin")
    @GetMapping(HISTORY_OF_ADDITIONS)
    public HistoryOfAdditionDto getHistoryOfAdditions(@RequestParam String uin) {

        return controllerHelper.getHistoryOrThrowException(uin);
    }

    @CachePut(value = "history", key = "#uin")
    @PostMapping(HISTORY_OF_ADDITIONS_UPDATE)
    public HistoryOfAdditionDto updateHistoryOfAdditions(@RequestParam String uin) {

        return controllerHelper.getHistoryOrThrowException(uin);
    }

    @CacheEvict(value = "history", key = "#uin")
    @PostMapping(HISTORY_OF_ADDITIONS_CACHE_EVICT)
    public String cacheEvictByUin(@RequestParam String uin) {
        return "Cache for UIN " + uin + " evicted";
    }

}
