This example will give an illustration of how we can create a custom message converter to convert message payload of different types into a consolidated class type. In our example, we define the following:

Pet <Abstraction>
    - name
    - color
    - sound
    - type

Dog <Implementation>
    - name
    - color

Cat <Implementation>
    - name

Parrot <Implementation>
    - color

The purpose of these classes is utilize the custom message converter to parse each class type, and transform them into a valid Pet instance.

Thus we define a custom message converters called "PetMessageConverter.java"

We have an source binding which publishes either a Dog, Cat or Parrot instance based on some randomness. Then we define a Sink which should utilize the appropriate message converter to receive all payloads of type Pet.

We will define a "petChannel" to give the output and input conversion illustration.

To start this microservice, execute the following: gradle bootRun
