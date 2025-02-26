package com.dishdash.services;

public interface MealDBClient {
    public String MealById(String id);
    public String ListMealCategories();
    public String ListMealAreas();
    public String ListMealIngredients();
    public String FilterByCategory(String c);
    public String FilterByArea(String a);
    public String FilterByIngredient(String i);
}
