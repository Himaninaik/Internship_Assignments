import java.time.LocalDate;
import java.util.*;


class Book {
    int bookId;
    String title;
    String author;
    String genre;
    boolean isIssued;
    Member issuedTo;
    LocalDate dueDate;
    Queue<Member> reservationQueue = new LinkedList<>();
}


abstract class Member {
    UUID memberID ;  
    String name;
    String email;
    String phone;
    int maxBooksAllowed;
    List<Book> currentlyIssuedBooks = new ArrayList<>();

    abstract int getMaxAllowedDays();
    abstract String getMemberType();

    public int getCurrentlyIssuedCount() {
        return currentlyIssuedBooks.size();
    }
}


class StudentMember extends Member {
   public int maxAllowedDays = 14;

    {
        this.maxBooksAllowed = 3;
    }

    @Override
    public int getMaxAllowedDays() {
        return maxAllowedDays;
    }

    @Override
    public String getMemberType() {
        return "Student";
    }
}

class TeacherMember extends Member {
    public int maxAllowedDays = 30;

    {
        this.maxBooksAllowed = 5;
    }

    @Override
    public int getMaxAllowedDays() {
        return maxAllowedDays;
    }

    @Override
    public String getMemberType() {
        return "Teacher";
    }
}

class GuestMember extends Member {
    public int maxAllowedDays = 7;

    {
        this.maxBooksAllowed = 1;
    }

    @Override
    public int getMaxAllowedDays() {
        return maxAllowedDays;
    }

    @Override
    public String getMemberType() {
        return "Guest";
    }
}


class Librarian extends Member {
    public List<Book> allBooks = new ArrayList<>();
    public List<Member> members = new ArrayList<>();

    {
        this.maxBooksAllowed = 0; 
    }

    @Override
    public int getMaxAllowedDays() {
        return 0;
    }

    @Override
    public String getMemberType() {
        return "Librarian";
    }

   
    public void addBook(Book book) {
        allBooks.add(book);
        System.out.println("Book added: " + book.title);
    }

    
    public void registerMember(Member member) {
        members.add(member);
        System.out.println(member.getMemberType() + " registered: " + member.name);
    }

    
    public void viewIssuedBooks(Member member) {
        System.out.println("\n" + member.getMemberType() + " " + member.name + " has:");
        if (member.currentlyIssuedBooks.isEmpty()) {
            System.out.println("  No books issued");
        } else {
            for (Book b : member.currentlyIssuedBooks) {
                System.out.println("  " + b.title + " (Due: " + b.dueDate + ")");
            }
        }
    }

    
    public void issueBook(String title, Member member) {
        for (Book book : allBooks) {
            if (book.title.equalsIgnoreCase(title.trim())) {
                if (!book.isIssued && member.currentlyIssuedBooks.size() < member.maxBooksAllowed) {
                    book.isIssued = true;
                    book.issuedTo = member;
                    book.dueDate = LocalDate.now().plusDays(member.getMaxAllowedDays());
                    member.currentlyIssuedBooks.add(book);
                    System.out.println("Book issued successfully to " + member.name);
                } else {
                    System.out.println("Cannot issue book. Either already issued or limit reached.");
                }
                return;
            }
        }
        System.out.println("Book not found in library.");
    }

   
    public void returnBook(String title, Member member) {
        for (Book book : new ArrayList<>(member.currentlyIssuedBooks)) {
            if (book.title.equalsIgnoreCase(title.trim())) {
                book.isIssued = false;
                book.issuedTo = null;
                book.dueDate = null;
                member.currentlyIssuedBooks.remove(book);
                System.out.println("Book returned successfully by " + member.name);
                return;
            }
        }
        System.out.println("This book was not issued by " + member.name);
    }
}


public class Library {
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
