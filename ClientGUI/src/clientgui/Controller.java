package clientgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;


public class Controller {
	
	private View theView;
	private Model theModel;
		
	public Controller (View theView, Model theModel){

		this.theModel=theModel;
		this.theView=theView;
		this.theView.executeCommand(new ExecuteCommand());
		this.theView.connect(new Connect());
		
	
	}
	
	
	class ExecuteCommand implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 try {
				 theView.displayResult(theModel.execute(theView.getInput()));	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}	
	
	class Connect implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 try {
					 theView.displayResult(theModel.connServer(theView.getIP(), theView.getPort()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}	
	
	
	}
