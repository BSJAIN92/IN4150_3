import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.*;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.SimpleFormatter;

public class Nodes extends UnicastRemoteObject implements Nodes_Interface, Runnable {
    private List<Edges_Interface> neighbourEdges = new LinkedList<Edges_Interface>();

    public void setNeighbourEdges(List<Edges_Interface> neighbourEdges) {
        this.neighbourEdges = neighbourEdges;
    }

    public List<Edges_Interface> getNeighbourEdges() {
        return this.neighbourEdges;
    }

    /*
     * Getters and Setters for a nodes adjacent edges
     */

    private int fragmentLevel;

    public void setFragmentLevel(int fragmentLevel) {
        this.fragmentLevel = fragmentLevel;
    }

    public int getFragmentLevel() {
        return this.fragmentLevel;
    }

    /*
     * The level of the fragment, the node belongs to.
     */

    private int fragmentName;

    public void setFragmentName(int fragmentName)  {
        this.fragmentName = fragmentName;
    }

    public int getFragmentName() {
        return this.fragmentName;
    }

    /*
     * Name of the fragment, the node belongs to. (Weight of the core)
     */

    private boolean coreOfFragment;

    public void setCoreOfFragment(boolean coreOfFragment)  {
        this.coreOfFragment = coreOfFragment;
    }

    public boolean getCoreOfFragment()  {
        return this.coreOfFragment;
    }

    /*
     *  Determines whether the node is in the core of its fragment
     */

    private String status;

    public void setStatus(String status)  {
        this.status = status;
    }

    public String getStatus()  {
        return status;
    }

    /*
     * Determines the status of the node ("Sleeping", "find", "found")
     */

    private Edges_Interface moeCandidate = new Edges(3000);

    public void setMoeCandidate(Edges_Interface moeCandidate)  {
        this.moeCandidate = moeCandidate;
    }

    public Edges_Interface getMoeCandidate()  {
        return this.moeCandidate;
    }

    /*
     * Reveals the nodes' minimum-weight outgoing edge
     * value set to 5000 in case of infinity
     * value set to 3000 in case of nil
     */

    private int numberReportMessagesExpected;

    public void setNumberReportMessagesExpected(int numberReportMessagesExpected)  {
        this.numberReportMessagesExpected=numberReportMessagesExpected;
    }

    public int getNumberReportMessagesExpected()  {
        return this.numberReportMessagesExpected;
    }

    /*
     * Number of report messages to be received
     */


    /*
     * Message buffer containing connect requests
     */

    public LinkedList<Message> messageQueue = new LinkedList<Message>();

    public LinkedList<Message> getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(LinkedList<Message> messageQueue) {
        this.messageQueue = messageQueue;
    }



    private static final long serialVersionUID = 4745507L;

	/*
	 * For logging
	 */

    
    private static Logger logger = Logger.getLogger(Nodes.class.getName());
    ConsoleHandler handler = new ConsoleHandler();
    
	/*
	 * To store url of server
	 */

    private Map<String, Nodes_Interface> serverDetails;

	/*
	 * Index number of the server
	 */

    private int serverIndex;

	/*
	 * Return serverIndex
	 * Debugging
	 * Private Check: 0000 0000
	 */

    public int getServerIndex() {
        return this.serverIndex;
    }

	/*
	 * Total number of servers or total nodes in the graph
	 */

    private int totalServers;

    /*
     * get total servers
     */

    public int getTotalServers() {
        return totalServers;
    }

    /*
     * set total servers
     */

    public void setTotalServers(int totalServers) {
        this.totalServers = totalServers;
    }

    /*
	 * Store urls of all servers
	 */
	
	private String[] urls;
	
	/*
	 * Fetch all urls
	 */
	
	public String[] getUrls() {
		return this.urls;
	}
	
	/*
	 * set urls
	 */
	
	public void setUrls(String[] urls) {
		this.urls = urls;
	}
	
    
	/*
	 * test edge
	 * nil value at 2000
	 */
	
	private Edges_Interface testEdge = new Edges(2000);
	
	public Edges_Interface getTestEdge() {
		return this.testEdge;
	}
	
	public void setTestEdge(Edges_Interface testEdge) {
		this.testEdge = testEdge;
	}
	
    /*
	 * Default Constructor
	 */

    protected Nodes(String[] urls, int serverIndex) throws RemoteException {
        super();
        
        this.urls = urls;
        this.serverIndex = serverIndex;
        this.totalServers = urls.length;
        this.status = "Sleeping";


    }

	/*
	 * Reset Server
	 */


    public void reset() {

    }

	/*
	 * Server start Logger
	 */


    public void run() {
    	logger.setLevel(Level.ALL);
        logger.info("Server successfully initialized and started");
        
    }


    public void wakeup() {
        
    	try {
    		logger.info("Inside wakeup funciton of server " + this.getServerIndex());
    		
    		Edges e = new Edges(1000);
    		Edges_Interface minimumWeightEdge;
    		minimumWeightEdge = (Edges_Interface) e;
    		
    		logger.info("Temporary edge of weight "+ e.getWeight());
    		
    		for (int i = 0; i < neighbourEdges.size(); i++) {
            	int w = minimumWeightEdge.getWeight();
                if (minimumWeightEdge.getWeight() > neighbourEdges.get(i).getWeight()){
                    minimumWeightEdge = (Edges_Interface) neighbourEdges.get(i);
                }
            }
            
    		logger.info("minimum weight edge is: " + minimumWeightEdge.getWeight());
    		
            minimumWeightEdge.setStatus("in_MST");
            this.fragmentLevel = 0;
            this.status = "Found";
            this.numberReportMessagesExpected = 0;
            
            logger.info("server " + this.serverIndex + " sending connect message");
            
            sendConnectMessage(minimumWeightEdge, this.fragmentLevel);
    		
    	}	catch (RemoteException e) {
    		e.printStackTrace();
    	}	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }

    public void sendConnectMessage(Edges_Interface minimumWeightEdge, int fragmentLevel) {
        
    	logger.info("Inside sendConnectMessage");
    	
    	int src = this.serverIndex;
        int dest;
        
        try {
        	if (minimumWeightEdge.getConnectedNodes().get(0).getServerIndex() == this.serverIndex){
                dest = minimumWeightEdge.getConnectedNodes().get(1).getServerIndex();
            }
            else {
                dest = minimumWeightEdge.getConnectedNodes().get(0).getServerIndex();
            }
        	
        	logger.info("Destination server is: " + dest);
        	
        	Connect_Message C = new Connect_Message(src, dest);
        	C.channel = minimumWeightEdge;
            Nodes_Interface destination = (Nodes_Interface) Naming.lookup(urls[dest]);
        	
            logger.info("server " + this.serverIndex + " Sending connectMessage to server " + dest);
            
            destination.receiveConnectMessage(C, fragmentLevel); 
        	
        } catch (RemoteException e1) {
        	e1.printStackTrace();
        }	catch (MalformedURLException e2) {
        	e2.printStackTrace();
        } catch (NotBoundException e3) {
			e3.printStackTrace();
		}	catch (Exception e) {
			logger.warning("error");
			e.printStackTrace();
			
		}
        
        
    }


    /*
     * Receiving a connect message
     * A connect message must contain: senderNode, receiverNode, edge
     * sendInitiateMessage must contain LN+1, w(j), status of senderNode
     */

    public void receiveConnectMessage(Connect_Message C, int fragmentLevel){
        
    	logger.info("server " + this.serverIndex + " received connectMessage from server " + C.getSrc());
    	
    	if(this.status == "Sleeping"){
            this.wakeup();
        }
        
    	try {
    		if(fragmentLevel<this.getFragmentLevel()) {
                C.channel.setStatus("in_MST");
                
                logger.info("server " + this.serverIndex + " Sending initiateMessage");
                
                sendInitiateMessage(C.channel, this.fragmentLevel, C.channel.getWeight(), this.status);
                
                if (this.status == "find") {
                    numberReportMessagesExpected++;
                }
            }
            else{
                if(C.channel.getStatus() == "?_in_MST"){
                	
                	logger.info("Adding to message queue");
                	
                	messageQueue.add(C);
                }
                else{
                	
                	logger.info("server " + this.serverIndex + " Sending initiateMessage with find");
                    
                	sendInitiateMessage(C.channel, this.getFragmentLevel()+1, C.channel.getWeight(), "find");
                }
            }
    		
    	}	catch (RemoteException e) {
    		e.printStackTrace();
    	}	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }


    /*
     * Sending initiate message
     * requires senderNode, receiverNode, Edge, levelFragment(senderNode), nameFragment(senderNode), statusEdge,
     */

    public void sendInitiateMessage(Edges_Interface E, int L, int w, String status) {
        
    	logger.info("Inside sendInitiateMessage");
    	
    	int src = this.serverIndex;
        int dest;
        
        try {
        	if (E.getConnectedNodes().get(0).getServerIndex() == this.serverIndex){
                dest = E.getConnectedNodes().get(1).getServerIndex();
            }
            else {
                dest = E.getConnectedNodes().get(0).getServerIndex();
            }
        	
        	logger.info("Destination server is: " + dest);
        	
            Initiate_Message I = new Initiate_Message(src, dest);
            I.channel = E;
            
            Nodes_Interface destination = (Nodes_Interface) Naming.lookup(urls[dest]);
        	
            logger.info("server " + this.serverIndex + " Sending connectMessage to server " + dest);
            
            destination.receiveInitiateMessage(I, L, w, status);
        	
        }	catch (RemoteException e) {
        	e.printStackTrace();
        }	catch (MalformedURLException e2) {
        	e2.printStackTrace();
        } catch (NotBoundException e3) {
			e3.printStackTrace();
		}	catch (Exception e) {
			logger.warning("error");
			e.printStackTrace();
		}
        
    }


    /*
     * Receiving Initiate Message
     */

    public void receiveInitiateMessage(Initiate_Message C, int L, int N, String status) {
        
    	logger.info("server " + this.serverIndex + " received initiateMessage from server " + C.getSrc());
    	
    	this.setFragmentLevel(L);
        this.setFragmentName(N);
        this.setStatus(status);
        int dest;
        
        logger.info("status is " + this.getStatus());
        
        try {
        	if(C.channel.getConnectedNodes().get(0).getServerIndex()==this.getServerIndex()){
            	dest = C.getChannel().getConnectedNodes().get(1).getServerIndex();
            }
            else {
            	dest = C.getChannel().getConnectedNodes().get(0).getServerIndex();
            }
            
        	Nodes_Interface destination = (Nodes_Interface) Naming.lookup(urls[dest]);
        	
            C.getChannel().setTowardsCore((Nodes_Interface)this, destination);
            
            moeCandidate = new Edges(5000);
            
        	for (int i = 0; i < neighbourEdges.size(); i++) {
                
            	if(!(neighbourEdges.get(i) == C.channel) && (neighbourEdges.get(i).getStatus() == "in_MST")){
                    
            		logger.info("server " + this.serverIndex + " Sending initiateMessage");
                    
            		sendInitiateMessage( neighbourEdges.get(i), this.getFragmentLevel(), this.getFragmentName(), this.status);
            		
                    if(this.status == "find"){
                    	numberReportMessagesExpected++;
                    }
                }
                
            }
            
        	logger.info("status is " + this.getStatus());
        	
            if(this.getStatus().equals("find")){
            	
            	logger.info("server " + this.getServerIndex() + " is Calling test function");
            	
            	test();
            }
        	
        }	catch (RemoteException e) {
        	e.printStackTrace();
        }	catch (Exception e) {
        	e.printStackTrace();
        }
        
    }
    
    
    public void test() {
    	
    	logger.info("Inside test function");
    	
    	try {
    		
        	for (int i = 0; i < neighbourEdges.size(); i++) {
        		if (neighbourEdges.get(i).getStatus() == "?_in_MST" & neighbourEdges.get(i).getWeight() < testEdge.getWeight()) {
        			this.testEdge = neighbourEdges.get(i);
        			
        		}
        	}
        	
        	logger.info("The weight of test edge is: " + testEdge.getWeight());
        	
        	if (this.testEdge.getWeight() != 2000) {
        		
        		logger.info("Sending testMessage");
                
        		sendTestMessage(this.testEdge, this.getFragmentLevel(), this.getFragmentName());
        	}
        	else {
        		
        		logger.info("Calling report function");
        		
        		report();
        	}
    		
    	}	catch (RemoteException e) {
    		e.printStackTrace();
    	}	catch (Exception e) {
    		e.printStackTrace();
    	}
    	

    	
    }
    
    
    
    public void sendTestMessage(Edges_Interface test, int fragmentLevel, int fragmentName){
    	
    	logger.info("Inside sendTestMessage");
    	
    	int src = this.serverIndex;
        int dest;
        
        try {
        	if (test.getConnectedNodes().get(0).getServerIndex() == this.serverIndex){
                dest = test.getConnectedNodes().get(1).getServerIndex();
            }
            else {
                dest = test.getConnectedNodes().get(0).getServerIndex();
            }
        	
        	logger.info("Destination server is: " + dest);
        	
        	Test_Message T = new Test_Message(src, dest);
        	T.channel = test;
            Nodes_Interface destination = (Nodes_Interface) Naming.lookup(urls[dest]);
        	
            logger.info("server " + this.serverIndex + " Sending testMessage to server " + dest);
            
            destination.receiveTestMessage(T, fragmentLevel, fragmentName); 
        	
        } catch (RemoteException e1) {
        	e1.printStackTrace();
        }	catch (MalformedURLException e2) {
        	e2.printStackTrace();
        } catch (NotBoundException e3) {
			e3.printStackTrace();
		}	catch (Exception e) {
			logger.warning("error");
			e.printStackTrace();
			
		}
    	
    }
    
    public void receiveTestMessage(Test_Message T, int fragmentLevel, int fragmentName) {
    	
    	logger.info("server " + this.serverIndex + " received testMessage from server " + T.getSrc());
    	
    	if(this.status == "Sleeping"){
            this.wakeup();
    	}
    	
    	try {
    		
    		if (fragmentLevel > this.getFragmentLevel()) {
        		this.messageQueue.add(T);
        	}
        	
        	else {
        		if (fragmentName != this.getFragmentName()) {
        			
        			logger.info(this.getServerIndex() + " is sending acceptMessage");
        			
        			sendAcceptMessage(T.getChannel());
        		}
        		else {
        			if (T.getChannel().getStatus().equals("?_in_MST")) {
        				T.getChannel().setStatus("not_in_MST");
        			}
        			
        			if (!(this.getTestEdge().equals(T.getChannel()))) {
        				
        				logger.info(this.getServerIndex() + " is sending rejectMessage");
        				
        				sendRejectMessage(T.getChannel());
        			}
        			else {
        				logger.info("server " + this.getServerIndex() + " is Calling test function");
                    	
                    	test();
        			}
        		}
        	}
    		
    	}	catch (RemoteException e1) {
    		e1.printStackTrace();
    	}	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    
    }
    
    public void report() {
    	
    	logger.info("Inside report function");
    	
    	
    	
    	try {
    		Edges ed = new Edges(1000);
    		Edges_Interface toCore;
    		toCore = (Edges_Interface) ed;
    		
    		
    		if (this.getNumberReportMessagesExpected() == 0 & this.getTestEdge().getWeight() == 2000) {
        		this.setStatus("found");
        		
        		logger.info(this.getServerIndex() + " is sending reportMessage");
				
        		for (int i = 0; i < neighbourEdges.size(); i++) {
            		if (!(neighbourEdges.get(i).getTowardsCore().isEmpty())) {
            			toCore = (Edges_Interface) neighbourEdges.get(i);
            			
            		}
            	}
        		
        		sendReportMessage(toCore, this.getMoeCandidate().getWeight());
        	}
        	
    	}	catch (RemoteException e1) {
    		e1.printStackTrace();
    	}	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void sendReportMessage(Edges_Interface toCore, int w) {
    	
    	logger.info("Inside sendReportMessage function");
    	
    	int src = this.serverIndex;
        int dest;
        
        try {
        	if (toCore.getConnectedNodes().get(0).getServerIndex() == this.serverIndex){
                dest = toCore.getConnectedNodes().get(1).getServerIndex();
            }
            else {
                dest = toCore.getConnectedNodes().get(0).getServerIndex();
            }
        	
        	logger.info("Destination server is: " + dest);
        	
        	Report_Message RE = new Report_Message(src, dest);
        	RE.channel = toCore;
            Nodes_Interface destination = (Nodes_Interface) Naming.lookup(urls[dest]);
        	
            logger.info("server " + this.serverIndex + " Sending reportMessage to server " + dest);
            
            destination.receiveReportMessage(RE, w); 
        	
        } catch (RemoteException e1) {
        	e1.printStackTrace();
        }	catch (MalformedURLException e2) {
        	e2.printStackTrace();
        } catch (NotBoundException e3) {
			e3.printStackTrace();
		}	catch (Exception e) {
			logger.warning("error");
			e.printStackTrace();
			
		}
    	
    }
    
    public void receiveReportMessage(Report_Message RE, int w) {
    	
    	logger.info("server " + this.serverIndex + " received reportMessage from server " + RE.getSrc());
    	
    	
    }
    
    public void sendAcceptMessage(Edges_Interface accept) {
    	
    	logger.info("Inside sendAcceptMessage function");
    	
    	int src = this.serverIndex;
        int dest;
        
        try {
        	if (accept.getConnectedNodes().get(0).getServerIndex() == this.serverIndex){
                dest = accept.getConnectedNodes().get(1).getServerIndex();
            }
            else {
                dest = accept.getConnectedNodes().get(0).getServerIndex();
            }
        	
        	logger.info("Destination server is: " + dest);
        	
        	Accept_Message A = new Accept_Message(src, dest);
        	A.channel = accept;
            Nodes_Interface destination = (Nodes_Interface) Naming.lookup(urls[dest]);
        	
            logger.info("server " + this.serverIndex + " Sending rejectMessage to server " + dest);
            
            destination.receiveAcceptMessage(A); 
        	
        } catch (RemoteException e1) {
        	e1.printStackTrace();
        }	catch (MalformedURLException e2) {
        	e2.printStackTrace();
        } catch (NotBoundException e3) {
			e3.printStackTrace();
		}	catch (Exception e) {
			logger.warning("error");
			e.printStackTrace();
			
		}
    	    	
    }
    
    public void receiveAcceptMessage(Accept_Message A) {
    	
    	logger.info("server " + this.serverIndex + " received acceptMessage from server " + A.getSrc());
    	
    	try {
    		
    		this.testEdge.setWeight(2000);
    		
    		if(A.getChannel().getWeight() < moeCandidate.getWeight()) {
    			moeCandidate = A.getChannel();
    		}
    		
    		logger.info("Calling report function");
    		
    		report();
    		
    	}	catch (RemoteException e1) {
    		e1.printStackTrace();
    	}	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    
    
    public void sendRejectMessage(Edges_Interface reject) {
    	
    	logger.info("Inside sendRejectMessage function");
    	
    	int src = this.serverIndex;
        int dest;
        
        try {
        	if (reject.getConnectedNodes().get(0).getServerIndex() == this.serverIndex){
                dest = reject.getConnectedNodes().get(1).getServerIndex();
            }
            else {
                dest = reject.getConnectedNodes().get(0).getServerIndex();
            }
        	
        	logger.info("Destination server is: " + dest);
        	
        	Reject_Message R = new Reject_Message(src, dest);
        	R.channel = reject;
            Nodes_Interface destination = (Nodes_Interface) Naming.lookup(urls[dest]);
        	
            logger.info("server " + this.serverIndex + " Sending rejectMessage to server " + dest);
            
            destination.receiveRejectMessage(R); 
        	
        } catch (RemoteException e1) {
        	e1.printStackTrace();
        }	catch (MalformedURLException e2) {
        	e2.printStackTrace();
        } catch (NotBoundException e3) {
			e3.printStackTrace();
		}	catch (Exception e) {
			logger.warning("error");
			e.printStackTrace();
			
		}
    	
    	
    }
    
    public void receiveRejectMessage(Reject_Message R) {
    	
    	logger.info("server " + this.serverIndex + " received rejectMessage from server " + R.getSrc());
    	
    	try {
    		
    		if (R.getChannel().getStatus().equals("?_in_MST")) {
        		R.getChannel().setStatus("not_in_MST");
        	}
        	
        	logger.info("server " + this.getServerIndex() + " is Calling test function");
        	
        	test();
        	
    	}	catch (RemoteException e1) {
    		e1.printStackTrace();
    	}	catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
}
