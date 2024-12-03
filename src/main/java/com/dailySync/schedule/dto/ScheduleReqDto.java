package com.dailySync.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleReqDto {
    //클라이언트에게 데이터를 보낼 때는 DTO를, 클라이언트가 서버에 보낼 때는 변환이 필요없음
    private LocalDateTime startTime;
    private LocalDateTime EndTime;
    private String title;
    private String description;


}
