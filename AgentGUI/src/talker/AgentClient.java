package talker;

import jade.core.Agent;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class AgentClient extends GuiAgent {
	
	private View GUI;
	private String[] message_types = {"AGREE", "CANCEL", "INFORM", "CFP", "CONFIRM",
			"DISCONFIRM", "FAILURE", "INFORM", "PROPOSE", "QUERY_IF", 
			"REFUSE", "REQUEST", "SUBSCRIBE"};
	private Integer[] int_types =  {ACLMessage.AGREE, ACLMessage.CANCEL, ACLMessage.INFORM, 
			ACLMessage.CFP, ACLMessage.CONFIRM, ACLMessage.DISCONFIRM, ACLMessage.FAILURE, 
			ACLMessage.INFORM, ACLMessage.PROPOSE, ACLMessage.QUERY_IF, 
			ACLMessage.REFUSE, ACLMessage.REQUEST, ACLMessage.SUBSCRIBE};
	private Hashtable<String, Integer> types = new Hashtable<String, Integer>();
	
	
	protected void setup() {
		for (int i = 0; i < int_types.length; i++) {
		    types.put(message_types[i], int_types[i]);
		}
		
		AMSAgentDescription [] agents = null;
      	try {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults (new Long(-1));
			agents = AMSService.search( this, new AMSAgentDescription (), c );
		}
		catch (Exception e) {
            System.out.println( "Problem searching AMS: " + e );
            e.printStackTrace();
		}
      	
      	
      	List<AID> IDs = new ArrayList<AID>();
		for (int i=0; i<agents.length;i++)
		{
			AID agentID = agents[i].getName();
			IDs.add(agentID);
		}
				
		System.out.println("Hello World! My name is " + getLocalName());
        GUI = new View();
        GUI.setVisible(true);
        GUI.setRecipients(IDs);
        GUI.setAgent(this);
        GUI.setName(getLocalName());
        addBehaviour(new TalkBehaviour(this));
	}

	@Override
	protected void onGuiEvent(GuiEvent ge) {
		
		ACLMessage msg = new ACLMessage(types.get(GUI.getPerformative())); 
		msg.setContent(GUI.getMessage());
		msg.addReceiver(new AID(GUI.getReceiver()));
		send(msg);
		GUI.setSent(msg.getContent());
		//GUI.setReceived("SEND");
		System.out.println("Pressed");
	}
	
    class TalkBehaviour extends CyclicBehaviour		
    {
    	
    	public TalkBehaviour(Agent a) {
			super(a);
    	}

		public void action() {
			ACLMessage msg = receive();
			if (msg!=null) {
				GUI.setReceived(msg.getContent());
				//System.out.println( " - " + myAgent.getLocalName() + " received: " + msg.getContent() );
			 }
			 block();
			 }
		}
	

}

