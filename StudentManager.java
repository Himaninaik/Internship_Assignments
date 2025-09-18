import java.io.*;
import java.util.*;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private HashMap<Integer, Student> studentMap = new HashMap<>();
    private TreeSet<Student> studentSet = new TreeSet<>(Comparator.comparing(Student::getName));

    public void addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            System.out.println("Student with this ID already exists!");
            return;
        }
        students.add(student);
        studentMap.put(student.getId(), student);
        studentSet.add(student);
        System.out.println("Student added successfully.");
    }

    public void removeStudent(int id) {
        Student st = studentMap.get(id);
        if (st != null) {
            students.remove(st);
            studentMap.remove(id);
            studentSet.remove(st);
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void updateStudent(int id, String name, int age, String grade, String address) {
        Student st = studentMap.get(id);
        if (st != null) {
            st.setName(name);
            st.setAge(age);
            st.setGrade(grade);
            st.setAddress(address);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public Student searchStudent(int id) {
        return studentMap.get(id);
    }

    public void displayAllStudents() {
        if (studentSet.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : studentSet) {
                System.out.println(s);
            }
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }


    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (ArrayList<Student>) ois.readObject();
            studentMap.clear();
            studentSet.clear();
            for (Student s : students) {
                studentMap.put(s.getId(), s);
                studentSet.add(s);
            }
            System.out.println("Data loaded from file");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting fresh");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}