import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner input = new Scanner(System.in);
        String sequence = input.nextLine();

        System.out.println(isIsBalanced(sequence));
    }

    private static boolean isIsBalanced(String sequence) {
        Deque<Character> bracketStack = new ArrayDeque<>();

        for (char bracket : sequence.toCharArray()) {
            switch (bracket) {
                case '(':
                    bracketStack.push(')');
                    break;
                case '[':
                    bracketStack.push(']');
                    break;
                case '{':
                    bracketStack.push('}');
                    break;

                case ')':
                case ']':
                case '}':
                    if (bracketStack.isEmpty() || bracketStack.pop() != bracket) {
                        return false;
                    }
                    break;
            }
        }
        return bracketStack.isEmpty();
    }
}