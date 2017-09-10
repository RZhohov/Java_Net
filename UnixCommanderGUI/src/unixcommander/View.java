package unixcommander;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
	
	private JLabel inputLabel = new JLabel("Enter command:");
	private JTextField user_input = new JTextField(10);
	private JTextArea user_output = new JTextArea(2, 10);
	private JButton button = new JButton("Execute!");
		
	

	public View(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 150);
		Component content = this.createComponents();
		this.add(content);
		
		setDefaultLookAndFeelDecorated(true);

            
	}
	
	public Component createComponents() {
		
		//Main container for all elements
		JPanel mainPanel = new JPanel();
		
		//Layout Manager
		GridBagLayout layout = new GridBagLayout();
		mainPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		// Put constraints on different elements
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(inputLabel, gbc); //add command label

		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(user_input, gbc); //input field
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(button, gbc); //button

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		//gbc.gridwidth = 10;
		user_output.setEditable(false);
		mainPanel.add(user_output, gbc); //output field
	
        return mainPanel;
    }
	
	
	public String getInput(){
		return user_input.getText();		
	}
	
	
	public void executeListener(ActionListener executeCommand){
		button.addActionListener(executeCommand);	
	}
	
	public void displayResult(String result) throws Exception  {
		try {
       	 	user_output.setText(result);
        }catch (Exception ex) {
       	 	ex.printStackTrace();
        }
		
}
	
   

  
}