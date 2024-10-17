import java.util.Scanner;

/**
 * @author Robbie
 * @since 2024/08/30
 */

public class HelloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Hello, " + name + "!");
        int age = sc.nextInt();
        System.out.println("Your age is " + age + " years old.");
    }
}
