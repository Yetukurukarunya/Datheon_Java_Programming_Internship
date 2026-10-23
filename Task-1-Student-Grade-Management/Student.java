
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Student {

    private String id;
    private String name;
    private final Map<String, Integer> grades;

    public Student(String id, String name) {
        setId(id);
        setName(name);
        this.grades = new HashMap<>();
    }

    // Validate and set student ID
    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        this.id = id.trim();
    }

    public String getId() {
        return id;
    }

    // Validate and set student name
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    // Return a read-only view of the grades
    public Map<String, Integer> getGrades() {
        return Collections.unmodifiableMap(grades);
    }

    // Add or update marks for a course
    public void setGrade(String course, int score) {
        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException(
                "Score must be between 0 and 100."
            );
        }

        grades.put(course.trim(), score);
    }

    // Calculate average marks across recorded courses
    public double getAverageMarks() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (int score : grades.values()) {
            total += score;
        }

        return (double) total / grades.size();
    }

    // Project convention: GPA is on a 10-point scale
    public double getGPA() {
        return getAverageMarks() / 10.0;
    }
}
