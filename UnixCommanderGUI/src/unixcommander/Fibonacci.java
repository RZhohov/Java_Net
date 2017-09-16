package unixcommander;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	

    public static int fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }
    
    public String toFibonacci(int n)
    {
    	List<Integer> numbers = new ArrayList<>();
    	for (int i = 1; i <= n; i++)
    	{
    		numbers.add(fibonacci(i));
    	}
    	
    	return numbers.toString();
    }
}