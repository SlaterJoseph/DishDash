package com.dishdash.services.impl;

import com.dishdash.services.MealService;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MealServiceImpl implements MealService {
    private final MealDBClientImpl mealDBClient;
    private static final Logger logger = Logger.getLogger(MealServiceImpl.class.getName());

    public MealServiceImpl(MealDBClientImpl mealDBClient) {
        this.mealDBClient = mealDBClient;
    }
}
