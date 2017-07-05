package za.co.seedollar.sandbox.springloud.domain;

/**
 * Created by seedollar on 7/5/17.
 */
public class Pet {

    private String name;
    private String color;
    private String sound;
    private String type;

    public Pet(String name, String color, String sound, String type) {
        this.name = name;
        this.color = color;
        this.sound = sound;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", sound='" + sound + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
