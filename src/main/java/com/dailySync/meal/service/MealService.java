package com.dailySync.meal.service;

import com.dailySync.constant.MealCategory;
import com.dailySync.meal.dto.*;
import com.dailySync.meal.entities.Meal;
import com.dailySync.meal.repository.MealRepository;
import com.dailySync.user.entities.User;
import com.dailySync.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public MealListResDto getUserMealList(Integer year, Integer month) {
        List<Meal> meals = mealRepository.findMealsByUserIdAndYearAndMonth(7L, year, month);
        Map<LocalDate, List<MealResDto>> mealList =  meals
                .stream().map(Meal::toRes)
                .collect(Collectors.groupingBy(MealResDto::getDate, TreeMap::new, Collectors.toList()));
        return new MealListResDto(mealList);
    }

    public MealDayResDto getUserDayMealList(Integer year, Integer month) {
        List<MealDayCntResDto> mealList = mealRepository.findDayByUserIdAndYearAndMonth(7L, year, month);
        return new MealDayResDto(mealList.stream().map(Meal::toDayCnt).toList());
    }

    public MealRecomResDto getRecommandList() {
        List<Meal> recommandList = mealRepository.findByUserId(1L);
        return new MealRecomResDto(recommandList.stream().map(Meal::toRecom).toList());
    }

    public MealRecomResDto getFavoriteList(Long id) {
        List<Meal> favoriteList = mealRepository.findByUserIdAndIsFavorite(id, true);
        return new MealRecomResDto(favoriteList.stream().map(Meal::toRecom).toList());
    }

    public List<MealCategoryResDto> getCategoryList() {
        return List.of(MealCategory.values())  // MealCategory enum 값 가져오기
                .stream()                   // 스트림으로 처리
                .map(MealCategoryResDto::fromMealCategory)  // 각 enum을 DTO로 변환
                .collect(Collectors.toList());
    }

    public Boolean insertMealList(List<Meal> meals) {
        User userId = userRepository.findById(7L).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + "Kim"));

        for (Meal meal : meals) {
            if(meal.getIsFavorite()) {
                mealRepository.save(Meal.ofSaveReq(userId, meal, true));
                mealRepository.save(Meal.ofSaveReq(userId, meal, false));
            } else {
                mealRepository.save(Meal.ofSaveReq(userId, meal, false));
            }
        }
        return true;
    }

    public Boolean updateMeal(MealReqDto mealReqDto) {
        Meal meal = mealRepository.findById(mealReqDto.getId()).orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + mealReqDto.getId()));

        meal.update(mealReqDto);
        mealRepository.save(meal);
        return true;
    }

    public Boolean deleteMeal(Long id) {
        Meal meal = mealRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + id));
        System.out.println(id);
        mealRepository.delete(meal);
        return true;
    }
}
