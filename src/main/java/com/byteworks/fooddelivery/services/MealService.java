package com.byteworks.fooddelivery.services;

import com.byteworks.fooddelivery.models.Meal;

public interface MealService {
    Meal findById(Integer id);
}
