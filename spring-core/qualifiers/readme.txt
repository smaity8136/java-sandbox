This module illustrates how you can apply @Qualifier annotation and also define custom qualifiers. 

We define 2 domain concepts, Recipe and Ingredient. We then apply @Qualifier annotation to all Ingredient concrete implementations and allow Spring to autowire implementations into Recipe implementations.

Something to note is how when we define out own custom Qualifer annotations, Spring only does proper autowiring injection on a setter accessor methods and constructor injection, but not field injection.

