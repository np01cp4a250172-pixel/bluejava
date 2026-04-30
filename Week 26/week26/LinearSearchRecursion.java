package week26;



public class LinearSearchRecursion
{
    public static int search(int[]arr, int index , int target){
        if (index>=arr.length){
            return -1;//not found
            
        }
        if (arr[index]== target){
            return index;
        }
        return search(arr,index +1, target);
    }
    
    public static void main(String[]args){
        int[]arr={4,2,7,1,9};
        int target=7;
        
        int result =search(arr,0,target);
        System.out.println(result);//2
    }
}