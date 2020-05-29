package com.example.cookiter.models;

public class RecipeModel {
    String recipe, name, author;
    Integer[] products;
    Integer id;
    String ar;

    public void setAr(String ar) {
        this.ar = ar;
    }

    public String getAr() {
        return ar;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
