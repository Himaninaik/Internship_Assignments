import java.sql.*;
import java.util.*;
import java.io.FileWriter;

public class StudentManagementApp {

   
    static class Student {
        Integer id;
        String firstName;
        String lastName;
        String email;
        int age;
        String grade;

        public Student(Integer id, String fn, String ln, String em, int age, String g) {
            this.id = id;
            this.firstName = fn;
            this.lastName = ln;
            this.email = em;
            this.age = age;
            this.grade = g;
        }

        public Student(String fn, String ln, String em, int age, String g) {
            this(null, fn, ln, em, age, g);
        }

        @Override
        public String toString() {
            return "Student{id=" + id + ", name=" + firstName + " " + lastName +
                   ", email=" + email + ", age=" + age + ", grade=" + grade + "}";
        }
    }

   
    static class DB {
        static final String URL = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&serverTimezone=UTC";
        static final String USER = "root";
        static final String PASS = ""; 

        static {
            try {
                
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC", USER, PASS);
                     Statement st = con.createStatement()) {
                    st.executeUpdate("CREATE DATABASE IF NOT EXISTS studentdb");
                }
                
                try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                     Statement st = con.createStatement()) {
                    String sql = "CREATE TABLE IF NOT EXISTS students (" +
                                 "id INT AUTO_INCREMENT PRIMARY KEY," +
                                 "first_name VARCHAR(100)," +
                                 "last_name VARCHAR(100)," +
                                 "email VARCHAR(150) UNIQUE," +
                                 "age INT," +
                                 "grade VARCHAR(5))";
                    st.executeUpdate(sql);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static Connection getConnection() throws Exception {
            return DriverManager.getConnection(URL, USER, PASS);
        }
    }

    // dao
    static class StudentDao {
        public Student save(Student s) throws Exception {
            String sql = "INSERT INTO students(first_name,last_name,email,age,grade) VALUES(?,?,?,?,?)";
            try (Connection con = DB.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, s.firstName);
                pst.setString(2, s.lastName);
                pst.setString(3, s.email);
                pst.setInt(4, s.age);
                pst.setString(5, s.grade);
                pst.executeUpdate();
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) s.id = rs.getInt(1);
                }
            }
            return s;
        }

        public List<Student> findAll() throws Exception {
            List<Student> list = new ArrayList<>();
            String sql = "SELECT * FROM students";
            try (Connection con = DB.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("grade")));
                }
            }
            return list;
        }

        public boolean delete(int id) throws Exception {
            String sql = "DELETE FROM students WHERE id=?";
            try (Connection con = DB.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, id);
                return pst.executeUpdate() > 0;
            }
        }

        public boolean update(Student s) throws Exception {
            String sql = "UPDATE students SET first_name=?,last_name=?,email=?,age=?,grade=? WHERE id=?";
            try (Connection con = DB.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, s.firstName);
                pst.setString(2, s.lastName);
                pst.setString(3, s.email);
                pst.setInt(4, s.age);
                pst.setString(5, s.grade);
                pst.setInt(6, s.id);
                return pst.executeUpdate() > 0;
            }
        }
    }

    //  Utility 
    static class CsvExporter {
        public static void export(List<Student> list, String file) throws Exception {
            try (FileWriter w = new FileWriter(file)) {
                w.write("id,first_name,last_name,email,age,grade\n");
                for (Student s : list) {
                    w.write(s.id + "," + s.firstName + "," + s.lastName + "," +
                            s.email + "," + s.age + "," + s.grade + "\n");
                }
            }
        }
    }

    //Main 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDao dao = new StudentDao();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Export to CSV");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("First name: "); String fn = sc.nextLine();
                        System.out.print("Last name: "); String ln = sc.nextLine();
                        System.out.print("Email: "); String em = sc.nextLine();
                        System.out.print("Age: "); int age = Integer.parseInt(sc.nextLine());
                        System.out.print("Grade: "); String g = sc.nextLine();
                        Student s = new Student(fn, ln, em, age, g);
                        dao.save(s);
                        System.out.println("Added: " + s);
                        break;

                    case "2":
                        List<Student> all = dao.findAll();
                        all.forEach(System.out::println);
                        break;

                    case "3":
                        System.out.print("Enter id to update: ");
                        int uid = Integer.parseInt(sc.nextLine());
                        System.out.print("New first name: "); String nfn = sc.nextLine();
                        System.out.print("New last name: "); String nln = sc.nextLine();
                        System.out.print("New email: "); String nem = sc.nextLine();
                        System.out.print("New age: "); int nage = Integer.parseInt(sc.nextLine());
                        System.out.print("New grade: "); String ng = sc.nextLine();
                        Student us = new Student(uid, nfn, nln, nem, nage, ng);
                        boolean ok = dao.update(us);
                        System.out.println(ok ? "Updated" : "Update failed");
                        break;

                    case "4":
                        System.out.print("Enter id to delete: ");
                        int did = Integer.parseInt(sc.nextLine());
                        boolean del = dao.delete(did);
                        System.out.println(del ? "Deleted" : "Not found");
                        break;

                    case "5":
                        List<Student> list = dao.findAll();
                        CsvExporter.export(list, "students.csv");
                        System.out.println("Exported to students.csv");
                        break;

                    case "0": exit = true; break;
                    default: System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        sc.close();
        System.out.println("Bye!");
    }
}
