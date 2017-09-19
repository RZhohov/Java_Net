package server;

import java.io.BufferedReader;
import java.util.logging.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
	
   private static Logger logger = Logger.getLogger(TCPServer.class.getName());
   private static FileHandler fh = null;
   private static Level log_level = Level.ALL; // OFF-SEVERE-WARNING-INFO-CONFIG-FINE-FINER-FINEST-ALL
   public static void init(){
	   try {
	   fh=new FileHandler("loggerExample.log", false);
	   } catch (SecurityException | IOException e) {
	   e.printStackTrace();
	   }
	   Logger l = Logger.getLogger("");
	   fh.setFormatter(new SimpleFormatter());
	   l.addHandler(fh);
	   }
   
   
   private static int PORT = 8080;
   Socket csocket;
   TCPServer(Socket csocket) {
      this.csocket = csocket;
   }
   
   public void run() {
      try {
    	//I/O streams  
    	logger.info("Creating I/O Streams");
  		InputStream in = csocket.getInputStream(); 
  		OutputStream out = csocket.getOutputStream();
  		BufferedReader din = new BufferedReader(new InputStreamReader(in));
  		PrintStream pstream = new PrintStream(out);
  		
  		while (true)
  		{
  		String command = din.readLine();
  			if (command.equals("quit"))
  			{
  				logger.info("User quits");
  				break;
  			}
  			else {
  			    logger.fine("User executes command " + command);
  				UnixExecuter cli = new UnixExecuter();
  		  		String response = cli.execute(command);
  		  		pstream.println(response);
  			}
  		}
  		 logger.info("Stream closed, Socket closed");
         pstream.close();
         csocket.close();
      } catch (IOException e) {
    	 logger.severe(e.getMessage());
      }
   }
   
   
   public static void main(String args[]) {
	   	  init();
	   	  logger.setLevel(log_level);
	   	  
	   	  logger.info("Creating Server Socket");
	      ServerSocket ssock = null;
		try {
			ssock = new ServerSocket(PORT);
		} catch (IOException e) {
			logger.severe(e.getMessage());
		}
	      System.out.println("Listening");
	      
	      while (true) {
	         Socket sock = null;
			try {
				sock = ssock.accept();
				logger.info("Accepting connection from client");
			} catch (IOException e) {
				logger.severe(e.getMessage());
			}
			logger.info("Creating new thread for client");
	         System.out.println("Connected");
	         new Thread(new TCPServer(sock)).start();
	      }
	   }
   
}

