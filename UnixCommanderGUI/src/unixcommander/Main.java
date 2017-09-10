package unixcommander;
import javax.swing.UIManager;


public class Main {

	public static void main(String[] args) {
		 try {
        	 UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        	 View theView = new View();
     		 Model theModel = new Model();
     		 Controller theController = new Controller(theView, theModel);
        	 theView.setVisible(true);
        	 
         }catch (Exception ex) {
        	 ex.printStackTrace();
         }
		
		

	}

}