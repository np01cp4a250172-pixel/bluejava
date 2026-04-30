package week26;



public class reverse_string {

    public static String reverseString(String s) {
        if (s.equals("")) {
            return s;
        }
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    public static void main(String[] args) {
        String input = "hello";
        String result = reverseString(input);
        System.out.println(result); // olleh
    }
}