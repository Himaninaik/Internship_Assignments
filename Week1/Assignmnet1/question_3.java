import java.util.Scanner;
public class question_3 {
    public static void main(String[] args) {
    Scanner s =new Scanner(System.in);
        System.out.println("enter a char");
        String character=s.next();

        char ch=character.charAt(0);
        char next = (char)( ch+1);
        char prev =(char) (ch-1);
        System.out.println(next);
        System.out.println(prev);
    }
}
