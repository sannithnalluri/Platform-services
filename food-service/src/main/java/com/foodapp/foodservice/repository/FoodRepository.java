package com.foodapp.foodservice.repository;

import com.foodapp.foodservice.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodItem, Long> {
}
