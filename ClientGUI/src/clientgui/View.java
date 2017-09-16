package clientgui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
	
	private JLabel IPLabel = new JLabel("IP");
	private JLabel PortLabel = new JLabel("PORT");
	private JTextField IP_input = new JTextField(10);
	private JTextField Port_input = new JTextField(10);
	private JButton conn_button = new JButton("Connect!");
	
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
		
		//IP, PORT labels, text fields and button
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(IPLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		mainPanel.add(PortLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(IP_input, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(Port_input, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		mainPanel.add(conn_button, gbc);

		// Put constraints on different elements
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		mainPanel.add(inputLabel, gbc); //add command label

		gbc.gridx = 1;
		gbc.gridy = 2;
		mainPanel.add(user_input, gbc); //input field
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		mainPanel.add(button, gbc); //button

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		//gbc.gridwidth = 10;
		user_output.setEditable(false);
		mainPanel.add(user_output, gbc); //output field
	
        return mainPanel;
    }
	
	
	public String getInput(){
		return user_input.getText();		
	}
	
	public String getIP(){
		return IP_input.getText();		
	}
	
	public int getPort(){
		return Integer.parseInt(Port_input.getText());		
	}
	
	
	public void executeCommand(ActionListener executeCommand){
		button.addActionListener(executeCommand);	
	}
	
	public void connect(ActionListener Connect){
		conn_button.addActionListener(Connect);	
	}
	
	public void displayResult(String result) throws Exception  {
		try {
       	 	user_output.setText(result);
        }catch (Exception ex) {
       	 	ex.printStackTrace();
        }
		
}
	
   

  
}