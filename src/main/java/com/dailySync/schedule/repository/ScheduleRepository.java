package com.dailySync.schedule.repository;

import com.dailySync.schedule.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
//CRUD 등의 기능을 제공하는 JpaRepository를 상속받는 ScheduleRepository선언, Schedule엔티티를 사용

    List<Schedule> findByTitleAndStartTime(String title, LocalDateTime startTime);

}