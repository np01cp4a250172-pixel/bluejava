package week26;


import java.util.Arrays;

public class Recursion
{
    public static void main(String [] args)
    {
        int fact = factorial(5);
        System.out.println("Factorial: " + fact);
        
        int sum =sum(5);
        System.out.println("sum: " + sum);
        
        
    }
    
    public static int factorial(int n)
    {
        //base case
        if(n==0 || n==1)
        {
            return 1;
            
        }
        return n * factorial(n-1);
    }
    
    public static int sum(int n)
    {
        //base case
        if (n==0)
        {
            return 0;
            
        }
        if (n==1)
        {
            return 1;
        }
        return n +sum(n-1);
    }
    
    
    public static int linearSearch(int[] arr, int target)
    {
        if (arr == null || arr.length==0)
        {
            return -1;
        }
        
        for(int i=0; i< arr.length;i++)
        {
            if(arr[i]== target)
            {
                return i;
            }
        }
        return -1;
        
    }
    
    public static int binarySearch(int[] arr, int target, int left ,int right )
    {
         Arrays.sort(arr);
        if (arr == null || arr.length==0)
        {
            return -1;
            
        }
        while(left <=right)
        {
            int mid= (left +right)/2;
            
            if (arr[mid] == target)
            {
                return mid;
            }
            else if (arr[mid]< target)
            {
                left =mid+1;
            }
            else
            {
                right=mid-1;
            }
        }
        
        return -1;
    }
    
    
}