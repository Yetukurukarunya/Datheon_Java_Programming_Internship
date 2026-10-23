
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GradeManager {

    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("==========================================");
        System.out.println("     STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> recordMarks();
                    case 3 -> displayClassStatistics();
                    case 4 -> generateReportCards();
                    case 5 -> displayAllStudents();
                    case 0 -> {
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--------------- MAIN MENU ---------------");
        System.out.println("1. Add Student");
        System.out.println("2. Record Marks");
        System.out.println("3. Class Average and Median");
        System.out.println("4. Generate Report Cards");
        System.out.println("5. Display All Students");
        System.out.println("0. Exit");
        System.out.println("-----------------------------------------");
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        if (findStudent(id) != null) {
            throw new IllegalArgumentException("Student ID already exists.");
        }

        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        Student student = new Student(id, name);
        students.add(student);

        System.out.println("Student added successfully!");
    }

    
private static void recordMarks() {
    if (students.isEmpty()) {
        System.out.println("No students registered yet.");
        return;
    }

    System.out.print("Enter student ID: ");
    String id = scanner.nextLine().trim();

    Student student = findStudent(id);

    if (student == null) {
        System.out.println("Student not found.");
        return;
    }

    System.out.print("Enter course name: ");
    String course = scanner.nextLine().trim();

    while (course.isEmpty()) {
        System.out.print("Course name cannot be empty. Enter course name: ");
        course = scanner.nextLine().trim();
    }

    int score;

    while (true) {
        score = readInt("Enter marks (0-100): ");

        if (score >= 0 && score <= 100) {
            break;
        }

        System.out.println("Invalid marks. Enter a score from 0 to 100.");
    }

    student.setGrade(course, score);
    System.out.println("Marks recorded successfully!");
}


    private static void displayClassStatistics() {
        List<Double> averages = new ArrayList<>();

        for (Student student : students) {
            if (!student.getGrades().isEmpty()) {
                averages.add(student.getAverageMarks());
            }
        }

        if (averages.isEmpty()) {
            System.out.println("No marks available to calculate statistics.");
            return;
        }

        double total = 0;

        for (double average : averages) {
            total += average;
        }

        averages.sort(Comparator.naturalOrder());

        double median;
        int size = averages.size();

        if (size % 2 == 0) {
            median = (averages.get(size / 2 - 1)
                    + averages.get(size / 2)) / 2.0;
        } else {
            median = averages.get(size / 2);
        }

        System.out.printf("Students with marks: %d%n", size);
        System.out.printf("Class Average: %.2f%n", total / size);
        System.out.printf("Class Median:  %.2f%n", median);
    }

    private static void generateReportCards() {
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        System.out.println("\n================ REPORT CARDS ================");

        System.out.printf("%-12s %-20s %-12s %-10s%n",
                "Student ID", "Name", "Average", "GPA");

        System.out.println("-------------------------------------------------------");

        for (Student student : students) {
            System.out.printf("%-12s %-20s %-12.2f %-10.2f%n",
                    student.getId(),
                    student.getName(),
                    student.getAverageMarks(),
                    student.getGPA());

            if (student.getGrades().isEmpty()) {
                System.out.println("  No marks recorded.");
            } else {
                for (var entry : student.getGrades().entrySet()) {
                    System.out.printf("    %-20s : %d%n",
                            entry.getKey(), entry.getValue());
                }
            }

            System.out.println();
        }
    }

    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered yet.");
            return;
        }

        System.out.println("\n---------- REGISTERED STUDENTS ----------");

        for (Student student : students) {
            System.out.printf("%s - %s%n",
                    student.getId(), student.getName());
        }
    }

    private static Student findStudent(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }

        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
