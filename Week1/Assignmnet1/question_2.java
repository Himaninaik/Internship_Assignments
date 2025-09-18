import java.util.Scanner;
public class question_2 {
    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);

        float number=s.nextFloat();
        System.out.println("enter a floating point ");
        float answer=number*2;
        float square =number*number;
        System.out.println("multiply by 2: "+answer);
        System.out.println("square of number: "+square);
    }
}
