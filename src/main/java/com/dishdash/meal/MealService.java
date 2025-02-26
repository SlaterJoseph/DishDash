package com.dishdash.meal;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MealService {
    private final MealDBClient mealDBClient;
    private static final Logger logger = Logger.getLogger(MealService.class.getName());

    public MealService(MealDBClient mealDBClient) {
        this.mealDBClient = mealDBClient;
    }
}
