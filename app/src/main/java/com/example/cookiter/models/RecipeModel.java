package com.example.cookiter.models;

public class RecipeModel {
    String recipe, name, author;
    Integer[] products;

    public RecipeModel(String recipe, String name, String author, Integer[] products) {
        this.recipe = recipe;
        this.name = name;
        this.author = author;
        this.products = products;
    }

    public String getRecipe() {
        return recipe;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer[] getProducts() {
        return products;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setProducts(Integer[] products) {
        this.products = products;
    }
}
