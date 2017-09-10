package unixcommander;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {
	
	private View theView;
	private Model theModel;
		
	public Controller (View theView, Model theModel){

		this.theModel=theModel;
		this.theView=theView;
		this.theView.executeListener(new ExecuteListener());
	
	}
	
	
	class ExecuteListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			 try {
				theView.displayResult(theModel.execute(theView.getInput()));
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			 
			}
		}	
	}

	
	

