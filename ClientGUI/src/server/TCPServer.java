package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
	
   private static int PORT = 8090;
   Socket csocket;
   TCPServer(Socket csocket) {
      this.csocket = csocket;
   }
   
   public void run() {
      try {
    	//I/O streams  
  		InputStream in = csocket.getInputStream(); 
  		OutputStream out = csocket.getOutputStream();
  		BufferedReader din = new BufferedReader(new InputStreamReader(in));
  		PrintStream pstream = new PrintStream(out);
  		
  		while (true)
  		{
  		String command = din.readLine();
  			if (command.equals("quit"))
  				break;
  		UnixExecuter cli = new UnixExecuter();
  		String response = cli.execute(command);
  		pstream.println(response);
  		}
         pstream.close();
         csocket.close();
      } catch (IOException e) {
         System.out.println(e);
      }
   }
   
   
   public static void main(String args[]) throws Exception { 
	      ServerSocket ssock = new ServerSocket(PORT);
	      System.out.println("Listening");
	      
	      while (true) {
	         Socket sock = ssock.accept();
	         System.out.println("Connected");
	         new Thread(new TCPServer(sock)).start();
	      }
	   }
   
}

