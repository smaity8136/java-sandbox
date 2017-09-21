package com.seedollar.sandbox.springcore.domain;

import com.seedollar.sandbox.springcore.annotation.Asian;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MainRecipe extends AbstractRecipe implements InitializingBean {

    private Ingredient mainIngredient;

    @Autowired
    @Qualifier("red-meat")
    private Ingredient meatIngredient;

    @Autowired
    @Asian
    public void setMainIngredient(Ingredient mainIngredient) {
        this.mainIngredient = mainIngredient;
        getIngredients().add(this.mainIngredient);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getIngredients().add(meatIngredient);
    }
}
