package com.foodapp.foodservice.controller;

import com.foodapp.foodservice.model.FoodItem;
import com.foodapp.foodservice.repository.FoodRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private static final Logger log = LoggerFactory.getLogger(FoodController.class);
    private final FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @PostConstruct
    public void initDatabase() {
        if (foodRepository.count() == 0) {
            log.info("Pre-populating database with mock food items...");
            foodRepository.save(new FoodItem("Pepperoni Pizza", "Classic cheese and pepperoni pizza", 12.99));
            foodRepository.save(new FoodItem("Cheeseburger", "Beef burger with cheddar cheese, lettuce, tomato", 8.99));
            foodRepository.save(new FoodItem("Caesar Salad", "Romaine lettuce, croutons, parmesan cheese, Caesar dressing", 7.49));
            log.info("Pre-population complete. Loaded 3 food items.");
        }
    }

    @GetMapping
    public List<FoodItem> getAllFoods() {
        log.info("Fetching all food items from database");
        return foodRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodById(@PathVariable Long id) {
        log.info("Searching database for food item with id: {}", id);
        return foodRepository.findById(id)
                .map(food -> {
                    log.info("Found food item: {}", food.getName());
                    return ResponseEntity.ok(food);
                })
                .orElseGet(() -> {
                    log.warn("Food item with id: {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public FoodItem createFoodItem(@RequestBody FoodItem foodItem) {
        log.info("Saving new food item to database: {}", foodItem.getName());
        return foodRepository.save(foodItem);
    }
}
