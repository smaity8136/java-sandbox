package za.co.seedollar.sandbox.springloud.service;

import io.vavr.Function0;
import io.vavr.Tuple;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.InboundChannelAdapter;
import za.co.seedollar.sandbox.springloud.binding.PetSink;
import za.co.seedollar.sandbox.springloud.binding.PetSource;
import za.co.seedollar.sandbox.springloud.domain.Cat;
import za.co.seedollar.sandbox.springloud.domain.Dog;
import za.co.seedollar.sandbox.springloud.domain.Parrot;
import za.co.seedollar.sandbox.springloud.domain.Pet;

import java.util.Random;

import static io.vavr.API.*;

/**
 * Created by seedollar on 7/5/17.
 */
@EnableBinding(value = {PetSource.class, PetSink.class})
public class PetService {

    private static final Logger logger = LoggerFactory.getLogger(PetService.class);

    private static final Seq<?> PETS = Tuple.of(Dog.class, Cat.class, Parrot.class).toSeq();
    private static final String[] PET_NAMES = {"Fluffy", "Dookie", "Pookie", "Carter", "Randy", "Wolfy"};
    private static final String[] COLORS = {"RED", "GREEN", "BLUE", "YELLOW", "PURPLE", "ORANGE"};

    private static Function0<String> randomPetNamesFunction = () -> PET_NAMES[new Random().nextInt(PET_NAMES.length-1)];

    private static Function0<String> randomColorsFunction = () -> COLORS[new Random().nextInt(COLORS.length-1)];

    @InboundChannelAdapter(value = PetSource.OUTPUT)
    public Object publishPet() {
        logger.info("About to publish random Pet....");
        // We retrieve a random pet class template
        Object randomPetTemplate = PETS.get(new Random().nextInt(PETS.size()));
        // Now we generate a concrete implementation and publish it to the output binding
        return Try.of(() -> Match(randomPetTemplate).of(
                Case($(Dog.class), new Dog(randomPetNamesFunction.apply(), randomColorsFunction.apply())),
                Case($(Cat.class), new Cat(randomColorsFunction.apply())),
                Case($(Parrot.class), new Parrot(randomPetNamesFunction.apply()))
        )).get();
    }

    @StreamListener(value = PetSink.INPUT)
    public void processPet(Pet petInstance) {
        logger.info(String.format("Processing incoming pet of type: %s", petInstance.toString()));
    }
}
