import java.util.HashSet;

public class DuplicateEmailDetector {

    public static String findFirstDuplicateEmail(String[] emails) {
        HashSet<String> seen = new HashSet<>();

        for (String email : emails) {
            if (!seen.add(email)) {  // add() returns false if already present
                return email;        // first duplicate found
            }
        }
        return null; // no duplicates
    }

    public static void main(String[] args) {
        String[] emails = {
            "alice@email.com",
            "bob@email.com",
            "alice@email.com",
            "charlie@email.com"
        };

        String result = findFirstDuplicateEmail(emails);
        System.out.println("First duplicate: " + result);
    }
}