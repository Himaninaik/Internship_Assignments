import java.util.Scanner;
public class question_1{
public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.println("enter the first_integer");
    int first_Integer=s.nextInt();
    System.out.println("enter the second_integer");
    int second_integer=s.nextInt();
    int sum =0,diff=0,product=0,quotient=0, rem=0;
    sum=first_Integer+second_integer;
    diff=first_Integer-second_integer;
    product=first_Integer*second_integer;
    System.out.println("sum of 2 integer"+sum);
    System.out.println("difference of 2 integer is "+diff);
    System.out.println("product odf 2 integer is "+product);
    if (second_integer != 0) {
    quotient=first_Integer/second_integer;
    rem = first_Integer%second_integer;
System.out.println("quotient is "+quotient);
    System.out.println("remainder of 2 integer is "+rem);
    

    } else {
                    System.out.println("Error: Division by zero is not allowed!");
                }
    
   
}
}