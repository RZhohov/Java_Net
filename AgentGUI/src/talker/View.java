package talker;

///
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jade.core.AID;
import jade.gui.GuiEvent;

public class View extends JFrame{

	private static final String[] message_types = {"AGREE", "CANCEL", "INFORM", "CFP", "CONFIRM",
			"DISCONFIRM", "FAILURE", "INFORM", "PROPOSE", "QUERY_IF", 
			"REFUSE", "REQUEST", "SUBSCRIBE"};
	
	private AgentClient myAgent;
	private JComboBox types = new JComboBox(message_types);
	//private JTextField text_type;
	private JComboBox recv = new JComboBox();
	//private JTextField text_recv;
	private JTextField text_mess;
	private JTextArea text_sent;
	private JTextArea text_received;
	
	public View(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(525, 425);
		//getContentPane().setLayout(null);
		Component content = this.createContents();
		getContentPane().add(content);
		setDefaultLookAndFeelDecorated(true);
	}
	
	public void setAgent(AgentClient a) {
		myAgent=a;
	}
	
	public void setName(String name) {
		 setTitle("Agent "+name);
	}
	
	public void setReceived(String message) {
		text_received.append(message+"\n");
	}
	
	public void setSent(String message) {
		text_sent.append(message+"\n");
	}
	
	public String getMessage() {
		return text_mess.getText();
	}
	
	public String getReceiver() {
		return recv.getSelectedItem().toString();
	}
	
	public String getPerformative() {
		return types.getSelectedItem().toString();
	}
	
	public void setRecipients(List<AID> IDs) {
		List<String> ID_names = new ArrayList<String>();
		for(int i=0; i<IDs.size(); i++){
			ID_names.add(IDs.get(i).getName()); 
			}
		String[] recipients = ID_names.toArray(new String[0]);
		DefaultComboBoxModel model = new DefaultComboBoxModel(recipients);
		recv.setModel(model);
	}

	protected Component createContents() {
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(null);
		
		JButton btnSend = new JButton();
		btnSend.setBounds(10, 299, 105, 35);
		btnSend.setText("Send");
		mainpanel.add(btnSend);
		btnSend.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			        System.out.println("Button pressed");
			        GuiEvent ge = new GuiEvent(this, 1);
			        myAgent.postGuiEvent(ge);
			   }
			});
		
		JButton btnCancel = new JButton();
		btnCancel.setBounds(131, 299, 105, 35);
		btnCancel.setText("Cancel");
		mainpanel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			        System.out.println("Cancel pressed");
			        dispose();
			        System.exit(0);
			   }
			});
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(10, 23, 181, 25);
		lblNewLabel.setText("Message performative:");
		mainpanel.add(lblNewLabel);
		
		//text_type = new JTextField();
		types.setBounds(10, 57, 181, 25);
		mainpanel.add(types);
		
		
		JLabel lblReceiver = new JLabel();
		lblReceiver.setBounds(10, 100, 81, 25);
		lblReceiver.setText("Receiver:");
		mainpanel.add(lblReceiver);
		
		//text_recv = new JTextField();
		recv.setBounds(10, 131, 181, 25);
		mainpanel.add(recv);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(10, 180, 81, 25);
		lblNewLabel_1.setText("Content:");
		mainpanel.add(lblNewLabel_1);
		
		text_mess = new JTextField();
		text_mess.setBounds(10, 211, 180, 25);
		mainpanel.add(text_mess);
		
		text_sent = new JTextArea();
		text_sent.setBounds(300, 57, 175, 99);
		text_sent.setEditable(false);
		mainpanel.add(text_sent);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(300, 23, 175, 25);
		lblNewLabel_2.setText("Sent Messages:");
		mainpanel.add(lblNewLabel_2);
		
		JLabel lblReceivedMessages = new JLabel();
		lblReceivedMessages.setBounds(300, 180, 175, 25);
		lblReceivedMessages.setText("Received messages:");
		mainpanel.add(lblReceivedMessages);
		
		text_received = new JTextArea();
		text_received.setBounds(300, 211, 175, 99);
		text_received.setEditable(false);
		mainpanel.add(text_received);
		
		return mainpanel;

	}
	
	//public static void main(String[] args){
	//	View view = new View();
	//	view.setVisible(true);
	//}
}

///

