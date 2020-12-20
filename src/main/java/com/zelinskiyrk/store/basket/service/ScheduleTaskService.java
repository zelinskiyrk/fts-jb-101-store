package com.zelinskiyrk.store.basket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor

public class ScheduleTaskService {
    private final BasketApiService basketApiService;

    @Scheduled(cron = "0 00 1 * * ?")
    public void showTime() {
        basketApiService.deleteOldBaskets();
    }


}
