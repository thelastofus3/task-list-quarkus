package com.thelastofus.service.impl;

import com.thelastofus.service.RemainderService;
import com.thelastofus.service.UserService;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RemainderServiceImpl implements RemainderService {

    UserService userService;

    @Override
//    @Scheduled(cron = "0 0 * * * ?")
    @Scheduled(cron = "0 * * * * ?")
    public void remindForTask() {
        log.info(userService.getUsers().toString());
    }

}
