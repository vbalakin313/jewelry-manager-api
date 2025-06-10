package ru.vbalakin.jewelrymanagerapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Operation history", description = "Allows you to perform operations on history")
public class HistoryController {

    private static final String HISTORY_OF_ADDITIONS = "/api/v1/history";
    private static final String HISTORY_OF_ADDITIONS_UPDATE = "/api/v1/history/update";
    private static final String HISTORY_OF_ADDITIONS_CACHE_EVICT = "/api/v1/history/evict";

    private final ControllerHelper controllerHelper;

    @Operation(
            summary = "Getting transaction history by UIN"
    )
    @Cacheable(value = "history", key = "#uin")
    @GetMapping(HISTORY_OF_ADDITIONS)
    public HistoryOfAdditionDto getHistoryOfAdditions(@RequestParam String uin) {

        return controllerHelper.getHistoryOrThrowException(uin);
    }

    @Operation(
            summary = "Updating the history of transactions by UIN"
    )
    @CachePut(value = "history", key = "#uin")
    @PostMapping(HISTORY_OF_ADDITIONS_UPDATE)
    public HistoryOfAdditionDto updateHistoryOfAdditions(@RequestParam String uin) {

        return controllerHelper.getHistoryOrThrowException(uin);
    }

    @Operation(
            summary = "Clearing user cache by UIN"
    )
    @CacheEvict(value = "history", key = "#uin")
    @PostMapping(HISTORY_OF_ADDITIONS_CACHE_EVICT)
    public String cacheEvictByUin(@RequestParam String uin) {
        return "Cache for UIN " + uin + " evicted";
    }

}
