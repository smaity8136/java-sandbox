package com.seedollar.sandbox.springcore.domain;

import com.seedollar.sandbox.springcore.annotation.European;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StarterRecipe extends AbstractRecipe {

    private Ingredient mainIngredient;

    private Ingredient condiment;

    /**
     * Constructor injection
     *
     * @param mainIngredient
     */
    public StarterRecipe(@Qualifier("white-meat") Ingredient mainIngredient, @European Ingredient condiment) {
        this.mainIngredient = mainIngredient;
        this.condiment = condiment;
        getIngredients().add(this.mainIngredient);
        getIngredients().add(this.condiment);
    }
}
