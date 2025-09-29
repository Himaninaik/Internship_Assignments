package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Librarian extends Member {
    public List<Book> allBooks = new ArrayList<>();
    public List<Member> members = new ArrayList<>();

    {
        this.maxBooksAllowed = 0; // Librarian does not issue books
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
