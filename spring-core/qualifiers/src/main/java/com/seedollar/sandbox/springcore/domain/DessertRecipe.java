package com.seedollar.sandbox.springcore.domain;


import com.seedollar.sandbox.springcore.annotation.Cold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DessertRecipe extends AbstractRecipe {

    private Ingredient mainDessertIngredient;

    @Autowired
    @Qualifier("sweet")
    @Cold
    public void setMainDessertIngredient(Ingredient mainDessertIngredient) {
        this.mainDessertIngredient = mainDessertIngredient;
        getIngredients().add(this.mainDessertIngredient);
    }
}
