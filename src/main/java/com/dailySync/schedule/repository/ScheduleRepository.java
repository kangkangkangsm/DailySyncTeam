package com.dailySync.schedule.repository;

import com.dailySync.schedule.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.user.id = :userId AND s.startTime <= :endOfMonth AND s.endTime >= :startOfMonth")
    List<Schedule> findByMonth(Long userId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);

    List<Schedule> findByUserIdAndTitleContainingOrderByStartTimeAsc(Long userId, String title);
//    List<Schedule> findByUserIdAndStartTimeYearAndStartTimeMonthOrderByStartTimeAsc(Long userId, int year, int month);
    // 특정 사용자와 날짜 범위에 맞는 일정 조회

    @Query(value = "SELECT s.* FROM schedule s "
            + "WHERE s.user_id = :userId "
            + "AND (YEAR(s.start_time) = :year OR YEAR(s.end_time) = :year) "
            + "ORDER BY s.start_time ASC", nativeQuery = true)
    List<Schedule> findByUserId_Year(@Param("userId") Long userId, @Param("year") int year);

    @Query(value = "SELECT s.* FROM schedule s "
            + "WHERE s.user_id = :userId "
            + "AND (YEAR(s.start_time) = :year AND MONTH(s.start_time) = :month "
            + "     OR YEAR(s.end_time) = :year AND MONTH(s.end_time) = :month) "
            + "ORDER BY s.start_time ASC", nativeQuery = true)
    List<Schedule> findByUserId_YearAndMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT s FROM Schedule s WHERE s.user.id = :userId "
            + "AND s.startTime <= :endOfMonth "
            + "AND s.endTime >= :startOfMonth")
    List<Schedule> findByRange(Long userId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}

