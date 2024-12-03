package com.dailySync.schedule.controller;

import com.dailySync.common.ApiResponse;
import com.dailySync.constant.ResMessage;
import com.dailySync.schedule.dto.ScheduleReqDto;
import com.dailySync.schedule.entities.Schedule;
import com.dailySync.schedule.service.ScheduleService;
import com.dailySync.user.dto.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dailySync.schedule.dto.ScheduleResDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    final private ScheduleService scheduleService;

//    @GetMapping ("Schedule1")
//    public ResponseEntity<?> getAllUser() {
//        List<ScheduleResDto> list = scheduleService.AllSchedules();
//        return ResponseEntity.ok(list);
//    }

//    @GetMapping ("Schedule/TitleAndTime/{}")
//    public ResponseEntity<?> getTitleAndTime() {
//        List<ScheduleResDto> list =
//                scheduleService.SearchByTitleAndStartTime(String Title, LocalDateTime StartTime);
//        return ResponseEntity.ok(list);
//    }

    @GetMapping("schedule")
    public ResponseEntity<?> getschedule(@RequestParam Long userId) {
//        return ResponseEntity.ok(scheduleService.getSchedule(userId));
        return ResponseEntity.ok(true);
    }

}

