import java.util.Scanner;
public class question_4 {
    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        System.out.println("enter the value");
        boolean b=s.nextBoolean();
        boolean opposite=!b;
        System.out.println("original value is "+b);
        System.out.println("opposite value is "+opposite);
    }
}
