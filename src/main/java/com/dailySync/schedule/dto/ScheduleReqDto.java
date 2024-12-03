package com.dailySync.schedule.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data //
@NoArgsConstructor
public class ScheduleReqDto {
    //클라이언트에게 데이터를 보낼 때는 DTO를, 클라이언트가 서버에 보낼 때는 변환이 필요없음
    private String userId;
    private String startTime;
    private String EndTime;
    private String title;
    private String description;

}
