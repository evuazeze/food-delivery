package com.byteworks.fooddelivery.services;

import com.byteworks.fooddelivery.models.Meal;
import com.byteworks.fooddelivery.repositories.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Meal findById(Integer id) {
        return mealRepository.findById(id).get();
    }
}
