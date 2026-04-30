package week26;



public class CountOccurrences {
    public static int count(int[] arr, int index, int target) {
        if (index == arr.length) return 0;
        
        if (arr[index] == target) {
            return 1 + count(arr, index + 1, target);
        }
        return count(arr, index + 1, target);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 5};
        int target = 2;
        System.out.println(count(arr, 0, target)); // 3
    }
}