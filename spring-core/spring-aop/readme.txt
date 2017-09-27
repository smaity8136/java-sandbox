This module will illustrate how to use Spring AOP concepts.

Spring AOP only supports method interception, and the weaving process is applied at Runtime.

The domain will be based on "Driving a Car" in the form of an interface called 'Car'.

The DrivingAspect will document the process, what needs to happen, before and after the drive() method is invoked on the Car interface.

The DrivingAspect will also define a @PointCut to illustrate how to apply an @Around advise for the reverse() method on the Car interface.

We also define a GearTrackingAspect which will illustrate how you can apply advice to methods with parameters, in this case it's the changeGear(int gearNumber) method on the Car interface.

We also declare a Spring AOP 'Introduction' aspect to extend the behaviour of a Spring bean, but using the @DeclareParents annotation. We are then able to cast the Car proxy instance to a Parking interface to invoke the park() method. This is also known as inter-type declarations or method injections.

To run this example, execute the following command: gradle bootRun



