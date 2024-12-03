package com.dailySync.schedule.controller;

import com.dailySync.common.ApiResponse;
import com.dailySync.constant.ResMessage;
import com.dailySync.constant.ResStatus;
import com.dailySync.schedule.service.ScheduleService;
import com.dailySync.user.dto.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dailySync.schedule.dto.ScheduleResDto;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

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