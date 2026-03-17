// Java code template starts here
import java.util.*;

public class Main {

    // Define Animal class here
    static class Animal {
        String name;
        Animal(String name) {
            this.name = name;
        }
        Animal() {
        }
        void makeSound() {
            System.out.println("Animal sound");
        }
    }

    // Define Dog class here
    static class Dog extends Animal {
        Dog(String name) {
            super(name);
        }
        Dog() {
        }
        @Override
        void makeSound() {
            System.out.println("Bark!");
        }
    }

    // Define Cat class here
    static class Cat extends Animal {
        Cat(String name) {
            super(name);
        }
        Cat() {
        }
        @Override
        void makeSound() {
            System.out.println("Meow!");
        }
    }

    public static void main(String[] args) {

        // In this section, you need to add code to receive input
        // and create the corresponding animal object
        Scanner scanner = new Scanner(System.in);
        String animalType = scanner.nextLine();
        String name = scanner.nextLine();

        // Replace the placeholders with the appropriate calls
        if(animalType.equals("Dog")) {
            Dog dog = new Dog(name);
            dog.makeSound();

        } else if(animalType.equals("Cat")) {
            Cat cat = new Cat(name);
            cat.makeSound();

        } else {
            System.out.println("Animal type not recognized");
        }
    }
} 

// Java code template ends here