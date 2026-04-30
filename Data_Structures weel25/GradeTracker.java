import java.util.HashMap;

public class GradeTracker {
    public static void main(String[] args) {
        HashMap<Integer, Integer> grades = new HashMap<>();

        // Add entries//int id and int marks
        grades.put(101, 85);
        grades.put(102, 92);
        grades.put(103, 78);

        // Update score for ID 102
        grades.put(102, 95);

        // Remove entry for ID 101
        grades.remove(101);

        // Check if ID 103 exists and print score
        if (grades.containsKey(103)) {
            System.out.println("ID 103 score: " + grades.get(103));
        }

        // Print all student IDs and scores
        System.out.println("\nAll Students:");
        for (int id : grades.keySet()) {
            System.out.println("ID: " + id + ", Score: " + grades.get(id));
        }
    }
}
