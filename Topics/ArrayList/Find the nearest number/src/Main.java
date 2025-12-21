import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        List<Integer> numbersList = Arrays.stream(input.nextLine().trim().split("\\s+"))
                .map(Integer::parseInt)
                .toList();
        int numberSearch = input.nextInt();

        List<Integer> result = new ArrayList<>();
        int minimunDelta = Integer.MAX_VALUE;

        for (int number : numbersList) {
            int distance = Math.abs(number - numberSearch);

            if (distance < minimunDelta) {
                minimunDelta = distance;
                result.clear();
                result.add(number);
            } else if (distance == minimunDelta) {
                result.add(number);
            }

        }

        //region Idiomatic sentence
        System.out.println(result.stream().sorted().map(String::valueOf).
                collect(Collectors.joining(" ")));
        //endregion

        Collections.sort(result);

        //region Step by step implementation using StringBuilder
        /* StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (i != result.size() - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb);*/
        //endregion
    }
}