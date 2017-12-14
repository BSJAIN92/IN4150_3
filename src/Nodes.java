import com.sun.javafx.geom.Edge;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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

    private String fragmentName;

    public void setFragmentName(String fragmentName)  {
        this.fragmentName = fragmentName;
    }

    public String getFragmentName() {
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
     * Determines the status of the node (Sleep, Find, Found)
     */

    private Edge moeCandidate;

    public void setMoeCandidate(Edge moeCandidate)  {
        this.moeCandidate = moeCandidate;
    }

    public Edge getMoeCandidate()  {
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

    private Map<Edge, String> statusOfEdge;

    public void setStatusOfEdge(Map<Edge, String> statusOfEdge) {
        this.statusOfEdge = statusOfEdge;
    }

    public Map<Edge, String> getStatusOfEdge()  {
        return this.statusOfEdge;
    }

    /*
     * Status of process' adjacent edges. For loop needed to fill the values
     */

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
	 * Default Constructor
	 */

    protected Nodes(int totalServers, int serverIndex) throws RemoteException {
        super();
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
}
