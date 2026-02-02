import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int firstHeight = scanner.nextInt();
        int secondHeight = scanner.nextInt();
        int thirdHeight = scanner.nextInt();

        System.out.println((firstHeight >= secondHeight && secondHeight >= thirdHeight) || (thirdHeight >= secondHeight && secondHeight >= firstHeight));
    }
}