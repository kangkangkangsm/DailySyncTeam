package com.dailySync.schedule.service;

import com.dailySync.schedule.dto.ScheduleResDto;
import com.dailySync.schedule.entities.Schedule;
import com.dailySync.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ScheduleService {
    final private ScheduleRepository scheduleRepository;

   public List<ScheduleResDto> AllSchedules() {
        List<Schedule> list = scheduleRepository.findAll();
        List<ScheduleResDto> resList = new ArrayList<>();
        for (Schedule schedule : list) {
                resList.add(ScheduleResDto.of(schedule));
        }
        return resList;
    }


    //제목,시작날짜 검색하면 해당 학목들이 모두 나오도록
    public List<ScheduleResDto> SearchByTitleAndStartTime(String title, LocalDateTime startTime) {
        List<Schedule> list = scheduleRepository.findByTitleAndStartTime(title, startTime);
        List<ScheduleResDto> reList = new ArrayList<>();
        for (Schedule schedule : list) {
           reList.add(ScheduleResDto.of(schedule));
       }
       return reList;
    }


}




