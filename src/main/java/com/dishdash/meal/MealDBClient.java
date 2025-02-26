package com.dishdash.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MealDBClient {
    private final RestTemplate restTemplate;
    private final String baseUrl;
    private static final Logger logger = LoggerFactory.getLogger(MealDBClient.class.getSimpleName());

    public MealDBClient(RestTemplate restTemplate, @Value("${meal.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * A method which gets a meal from its ID from MealDB
     * @param mealId: The ID of the given meal
     * @return The JSON data of the meal from MealDB
     */
    public String MealById(String mealId) {
        String url = baseUrl + "search.php?i=" + mealId;
        logger.info("Getting Meal by Id from {}", url);
        String response = restTemplate.getForObject(url, String.class);
        getDebug(response);
        return response;
    }

    /**
     * A Method which lists all meal categories
     * @return A list of all categories
     */
    public String ListMealCategories() {
        logger.info("Getting List of Meal Categories");
        String response = restTemplate.getForObject(baseUrl + "list.php?c=list", String.class);
        getDebug(response);
        return response;
    }

    /**
     * A Method which lists all meal area origins
     * @return A list of all areas
     */
    public String ListMealAreas() {
        logger.info("Getting List of Meal Areas");
        String response =  restTemplate.getForObject(baseUrl + "list.php?a=list", String.class);
        getDebug(response);
        return response;
    }

    /**
     * A Method which lists all meal ingredients
     * @return A list of all ingredients
     */
    public String ListMealIngredients() {
        logger.info("Getting List of Meal Ingredients");
        String response = restTemplate.getForObject(baseUrl + "list.php?i=list", String.class);
        getDebug(response);
        return response;
    }

    /**
     * A Method which filters by the chosen category
     * @param category: The category to filter by
     * @return The meals which fit into this category
     */
    public String FilterByCategory(String category) {
        String url = baseUrl + "search.php?c=" + category;
        logger.info("Getting Meal By Category with filter from {}", url);
        String response = restTemplate.getForObject(url, String.class);
        getDebug(response);
        return response;
    }

    /**
     * A Method which filters by the chosen area of origin
     * @param area: The chosen area of origin
     * @return The meals which originate from this area
     */
    public String FilterByArea(String area) {
        String url = baseUrl + "search.php?a=" + area;
        logger.info("Getting Meal By Area with filter from {}", url);
        String response = restTemplate.getForObject(url, String.class);
        getDebug(response);
        return response;
    }

    /**
     * A Method which filters by a chosen ingredient
     * @param ingredient: The chosen ingredient
     * @return The meals which include this ingredient
     */
    public String FilterByIngredient(String ingredient) {
        String url = baseUrl + "search.php?i=" + ingredient;
        logger.info("Getting Meal By Ingredient with filter from {}", url);
        String response = restTemplate.getForObject(url, String.class);
        getDebug(response);
        return response;
    }

    /**
     * Logs the response when in debug mode
     * @param response: The response of the query
     */
    private static void getDebug(String response) {
        logger.debug("Response: {}", response);
    }
}
