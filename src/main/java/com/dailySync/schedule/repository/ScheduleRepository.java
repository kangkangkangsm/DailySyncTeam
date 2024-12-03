package com.dailySync.schedule.repository;

import com.dailySync.schedule.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
//CRUD 등의 기능을 제공하는 JpaRepository를 상속받는 ScheduleRepository선언, Schedule엔티티를 사용

//   List<Schedule> findByUserId (Long userId);
    //UserId를 기준으로 Schedule을 조회, Optional로 감싸서 null를 피할 수 있게 함

//    List<Schedule> findByTitleAndStartTime(String Title, LocalDateTime startTime);

}