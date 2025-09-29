import model.*;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Librarian librarian = new Librarian();
        System.out.print("Enter librarian name: ");
        librarian.name = sc.nextLine();

        StudentMember student = new StudentMember();
        System.out.print("Enter student name: ");
        student.name = sc.nextLine();
        librarian.registerMember(student);

        TeacherMember teacher = new TeacherMember();
        System.out.print("Enter teacher name: ");
        teacher.name = sc.nextLine();
        librarian.registerMember(teacher);

        GuestMember guest = new GuestMember();
        System.out.print("Enter guest name: ");
        guest.name = sc.nextLine();
        librarian.registerMember(guest);

        Book book1 = new Book();
        System.out.print("Enter title of first book: ");
        book1.title = sc.nextLine();
        librarian.addBook(book1);

        Book book2 = new Book();
        System.out.print("Enter title of second book: ");
        book2.title = sc.nextLine();
        librarian.addBook(book2);

        System.out.print("Enter title of book to issue to " + student.name + ": ");
        String issueStudent = sc.nextLine();
        librarian.issueBook(issueStudent, student);
        librarian.viewIssuedBooks(student);

        System.out.print("\nEnter title of book to issue to " + teacher.name + ": ");
        String issueTeacher = sc.nextLine();
        librarian.issueBook(issueTeacher, teacher);
        librarian.viewIssuedBooks(teacher);

        System.out.print("\nEnter title of book to issue to " + guest.name + ": ");
        String issueGuest = sc.nextLine();
        librarian.issueBook(issueGuest, guest);
        librarian.viewIssuedBooks(guest);

        sc.close();
    }
}
