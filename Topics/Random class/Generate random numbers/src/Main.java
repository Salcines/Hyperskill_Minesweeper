import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numbers = scanner.nextInt();
        int lowerBound = scanner.nextInt();
        int upperBound = scanner.nextInt();

        int total = 0;

        Random random = new Random(lowerBound + upperBound);

        for (int i = 0; i < numbers; i++) {
            int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            total += randomNumber;
        }

        System.out.println(total);
        scanner.close();
    }
}