package com.dailySync.meal.dto;

import com.dailySync.constant.MealCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealResDto {
    private Long id;
    private String foodName;
    private MealCategory category;
    private String description;
    private String icon;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate date;
    private Integer kcalories;
    private Integer sugar;
    private Integer sodium;
    private Integer protein;
    private Integer fat;
    private Integer carbs;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isFavorite;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer week;
}
