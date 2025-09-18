 import java.util.Scanner;
public class question1in1 {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number ");
        int first_Integer = sc.nextInt();
        System.out.print("Enter second number ");
        int second_integer = sc.nextInt();
        System.out.println("\nChoose an operation:");
        System.out.println("1. Sum");
        System.out.println("2. Difference");
        System.out.println("3. Product");
        System.out.println("4. Quotient and Remainder");

        System.out.print("Enter choice (1-4): ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Sum = " + (first_Integer + second_integer));
                break;

            case 2:
                System.out.println("Difference = " + (first_Integer - second_integer));
                break;

            case 3:
                System.out.println("Product = " + (first_Integer* second_integer));
                break;

            case 4:
                if (second_integer != 0) {
                    int quotient = first_Integer / second_integer;
                    int remainder = first_Integer % second_integer;
                    System.out.println("Quotient = " + quotient);
                    System.out.println("Remainder = " + remainder);
                } else {
                    System.out.println("Error: Division by zero is not allowed!");
                }
                break;

            default:
                System.out.println("Invalid choice! Please select 1-4.");
        }

        sc.close();
    }
}



    