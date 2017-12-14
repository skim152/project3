/**
 *
 * @author Shinyeob Kim
 */
package shinyeobproject3;


public class ShinyeobProject3 {

  
  private static int efficiency = 0;
  
  public static int computeIterative(int n) {
    int previous = 0;
    int current = 1;
    int next;
    
    efficiency = 0;
    for (int i = 1; i <= n; i++)
    {
        next = previous + (2 * current);
        previous = current;
        current = next;
        efficiency++;
    }
    return previous;
  } // end computeIterative

  private static int computeRecursive(int placeHolder, int n) {
    // the efficiency value is passed as 1 to count calls with n<=1
    // when n>1, efficiency is incremented in the call to the recursive method
    
    if (n <= 1){
      return n;
    }
    else {
      return 2 * computeRecursive(efficiency++, n-1) + computeRecursive(efficiency++, n-2);
    }
    
  } // end computeRecursive
  
  public static int computeRecursive(int n) {
    // reinitialize the efficiency variable to 1
    // this counts the first call to the recursive method
    
    efficiency = 1;
    return computeRecursive(efficiency, n);
  } // end computeRecursive helper method
  
  public static int getEfficency() {
    return efficiency;
  } // end getEfficency
  
} // end class
    

