package com.byteworks.fooddelivery.repositories;

import com.byteworks.fooddelivery.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Integer> {
}
