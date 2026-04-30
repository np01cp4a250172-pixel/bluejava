package week26;

    public class peakElement {
        public static void main(String[] args) {
            int[] num={15,10,7,9,16,25,20};
            System.out.println(" Peak element is at index:"+ findPeak(num,0,num.length-1));
        }
        
    public static int findPeak(int[] arr, int left, int right)
    {
        if (left == right)
        {
            return left;
        }
        int mid = (left + right) / 2;
        
        return (arr[mid]<arr[mid+1]) ? findPeak(arr, mid+1, right):findPeak(arr,left,mid);

    
}
}