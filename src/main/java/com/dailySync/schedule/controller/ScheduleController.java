package com.dailySync.schedule.controller;

import com.dailySync.common.ApiResponse;
import com.dailySync.constant.ResMessage;
import com.dailySync.constant.ResStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//dfdfdffdfd
@RestController
@RequiredArgsConstructor
public class ScheduleController {
    @GetMapping ("schedule/getSchedule")
    public ResponseEntity<Object> getSchedule() {
        return ResponseEntity.ok(
                ApiResponse.builder().
                        status(ResStatus.SUCCESS).
                        message(ResMessage.SUCCESS).
                        data(true).
                        build()
        );
    }
}