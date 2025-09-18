
    import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        manager.loadFromFile("students.dat");

        while (true) {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    manager.addStudent(new Student(id, name, age, grade, address));
                    break;

                case 2:
                    System.out.print("Enter ID to remove: ");
                    int rid = sc.nextInt();
                    manager.removeStudent(rid);
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String nname = sc.nextLine();
                    System.out.print("Enter New Age: ");
                    int nage = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Grade: ");
                    String ngrade = sc.nextLine();
                    System.out.print("Enter New Address: ");
                    String naddr = sc.nextLine();
                    manager.updateStudent(uid, nname, nage, ngrade, naddr);
                    break;

                case 4:
                    System.out.print("Enter ID to search: ");
                    int sid = sc.nextInt();
                    Student s = manager.searchStudent(sid);
                    if (s != null) {
                        System.out.println(s);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5:
                    manager.displayAllStudents();
                    break;

                case 6:
                    manager.saveToFile("students.dat");
                    System.out.println("Exiting");
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}


