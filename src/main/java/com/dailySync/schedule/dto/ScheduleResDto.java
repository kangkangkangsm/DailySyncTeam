package com.dailySync.schedule.dto;

import com.dailySync.schedule.entities.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor  //기본 생성자 생성
@AllArgsConstructor
public class ScheduleResDto {
    //클라이언트와 서버 간 전용 데이터 전송용 객체, 응답 데이터를 표현
//클라이언트에게 데이터를 보낼 때는 DTO를, 클라이언트가 서버에 보낼 때는 변환이 필요없음
    private String userId;
    private String startTime;
    private String endTime;
    private String title;
    private String description;
    //전달할 데이터들

    public static ScheduleResDto nullSchedule() {
        return new ScheduleResDto();
    }//데이터가 없을 때 빈 객체를 반환

    public static ScheduleResDto of(Schedule schedule) {
        //매개변수로 schedule객체를 받아 ScheduleResDto로 반환하는 역할

        if(schedule == null) {
            return new ScheduleResDto();
        }
        //schedule이 null이변 비어있는 ScheduleREsDto를 반환

        ScheduleResDto scheduleResDto = new ScheduleResDto();
        //ScheduleResDto 객체 생성




       // scheduleResDto.setStartTime(schedule.getStartTime());
       // scheduleResDto.setEndTime(schedule.getEndTime());
        scheduleResDto.setTitle(schedule.getTitle());
        scheduleResDto.setDescription(schedule.getDescription());
        //schedule객체의 userId값을 가져와서 schduleResDto로 설정

        return scheduleResDto;



    }


}
