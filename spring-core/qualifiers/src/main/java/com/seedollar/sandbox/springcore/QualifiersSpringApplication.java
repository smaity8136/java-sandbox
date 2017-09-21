package com.seedollar.sandbox.springcore;

import com.seedollar.sandbox.springcore.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QualifiersSpringApplication implements ApplicationRunner {

    @Autowired
    private Recipe starterRecipe;

    @Autowired
    private Recipe mainRecipe;

    @Autowired
    private Recipe dessertRecipe;

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{QualifiersSpringApplication.class}, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\nStarter Recipe Ingredients: ");
        starterRecipe.getIngredients().stream().forEach(ing -> System.out.println("Ingredient = " + ing));

        System.out.println("\nDessert Recipe Ingredients: ");
        dessertRecipe.getIngredients().stream().forEach(ing -> System.out.println("Ingredient = " + ing));

        System.out.println("\nMain Recipe Ingredients: ");
        mainRecipe.getIngredients().stream().forEach(ing -> System.out.println("Ingredient = " + ing));
    }
}
