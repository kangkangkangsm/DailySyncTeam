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

    @GetMapping("schedule")
    public ResponseEntity<?> getschedule(@RequestParam Long userId) {
        return ResponseEntity.ok(scheduleService.AllSchedules());
    }

    @GetMapping ("Schedule/TitleAndTime/{title}/{startTime}")
    public ResponseEntity<?> getTitleAndTime(@PathVariable String title, @PathVariable LocalDateTime startTime) {
        List<ScheduleResDto> list =
                scheduleService.SearchByTitleAndStartTime(title, startTime);
        return ResponseEntity.ok(list);
    }



}

