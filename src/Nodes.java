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
     * Determines the status of the node ("Sleeping", "Find", "Found")
     */

    private Edges moeCandidate;

    public void setMoeCandidate(Edges moeCandidate)  {
        this.moeCandidate = moeCandidate;
    }

    public Edges getMoeCandidate()  {
        return this.moeCandidate;
    }

    /*
     * Reveals the nodes' minimum-weight outgoing edge
     * value set to 5000 in case of infinity
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
                	messageQueue.add(C);
                }
                else{
                	
                	logger.info("server " + this.serverIndex + " Sending initiateMessage");
                    
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
        
    	logger.info("server " + this.serverIndex + " received connectMessage from server " + C.getSrc());
    	
    	this.setFragmentLevel(L);
        this.setFragmentName(N);
        this.setStatus(status);
        int dest;
        
        
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
                    
            		sendInitiateMessage((Edges) neighbourEdges.get(i), this.getFragmentLevel(), this.getFragmentName(), this.status);
            		
                    if(this.status == "find"){numberReportMessagesExpected++;}
                }
                
            }
            
            if(this.status == "find"){
            	//test();
            }
        	
        }	catch (RemoteException e) {
        	e.printStackTrace();
        }	catch (Exception e) {
        	e.printStackTrace();
        }
        
    }
    
    
    public void test() {
    	
    	try {
    		Edges testEdge = new Edges(1000);
        	
        	for (int i = 0; i < neighbourEdges.size(); i++) {
        		if (neighbourEdges.get(i).getStatus() == "?_in_MST" & neighbourEdges.get(i).getWeight() < testEdge.getWeight()) {
        			testEdge = (Edges) neighbourEdges.get(i);
        			//sendTestMessage();
        		}
        	}
        	
        	if (testEdge.getWeight() != 1000) {
        		
        	}
        	else {
        		reportMessage();
        	}
    		
    	}	catch (RemoteException e) {
    		e.printStackTrace();
    	}	catch (Exception e) {
    		e.printStackTrace();
    	}
    	

    	
    }
    
    //Yet to complete sendTestMessage()
    
    public void sendTestMessage(){
    	
    	    	
    }
    
    public void receiveTestMessage() {
    	
    }
    
    public void reportMessage() {
    	
    }


}
