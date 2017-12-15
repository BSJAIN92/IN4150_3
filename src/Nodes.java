

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.*;

public class Nodes extends UnicastRemoteObject implements Nodes_Interface, Runnable {
    private List<Edges> neighbourEdges;

    public void setNeighbourEdges(List<Edges> neighbourEdges) {
        this.neighbourEdges = neighbourEdges;
    }

    public List<Edges> getNeighbourEdges() {
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

    public LinkedList<Message> messageQueue = new LinkedList<Message>;

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
	 * Default Constructor
	 */
    
	protected Nodes(int totalServers, int serverIndex) throws RemoteException {
        super();
        
        
        this.serverIndex = serverIndex;
        this.totalServers = totalServers;
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
        logger.info("Server successfully initialized and started");
    }
    
    /*
     * Receiving a connect message
     * A connect message must contain: senderNode, receiverNode, edge
     * sendInitiateMessage must contain LN+1, w(j), status of senderNode
     */

    public void receiveConnectMessage(ConnectMessage C){
        if(this.status == "sleeping"){
            this.wakeUp();
        }
        if(C.getSenderNode().getFragmentLevel()<this.getFragmentLevel()) {
            C.Edge.setStatus("inMST");
            sendInitiateMessage(C.senderNode, this.getFragmentLevel(), C.Edge.weight, this.status);
            if (this.status == "find") {
                numberReportMessagesExpected++;
            }
        }
        else{
            if(C.edge.status == "?_in_MST"){messageQueue.add(C);}
            else{sendInitiateMessage(C.senderNode, this.getFragmentLevel()+1, C.edge.weight, "find");}
        }
    }


}

    /*
     * A node waking up
     */


    /*
     * Sending initiate message
     * requires senderNode, receiverNode, Edge, levelFragment(senderNode), nameFragment(senderNode), statusEdge,
     */

    public void sendInitiateMessage() {
    }
	
	
	
    
}
