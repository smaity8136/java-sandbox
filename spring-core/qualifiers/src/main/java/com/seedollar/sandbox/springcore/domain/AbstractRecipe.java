package com.seedollar.sandbox.springcore.domain;

import com.google.common.collect.Lists;

import java.util.List;

public class AbstractRecipe implements Recipe {

    protected List<Ingredient> ingredients;

    public AbstractRecipe() {
        this.ingredients = Lists.newArrayList();
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
