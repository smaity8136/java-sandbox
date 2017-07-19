This module is a support module that simply publishes a random color (String datatype) every 2 seconds to a rabbitMQ exchange called 'colorChannel'.

It also adds a header named 'mood' with the random color value.

To start the microservice, execute: gradle bootRun
