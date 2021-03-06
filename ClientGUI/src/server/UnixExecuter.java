package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UnixExecuter {
	public String execute(String command)
	{
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			if (command.toLowerCase().contains("&&") || command.toLowerCase().contains("|"))
			{
				String[] cmd = {"/bin/bash", "-c", command};
				p = Runtime.getRuntime().exec(cmd);
				p.waitFor();
			}
			else
			{
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			}
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
}
