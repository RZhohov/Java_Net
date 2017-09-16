package unixcommander;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Model {
	
	public String execute(String command)
	{
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();
	}
	
	public String fibonacci(String[] input)
	{
		Fibonacci f = new Fibonacci();
		String output = f.toFibonacci(Integer.parseInt(input[1]));
		return output;
	}

}
