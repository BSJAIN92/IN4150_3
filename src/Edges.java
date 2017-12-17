import java.util.*;


public class Edges implements Edges_Interface{

	/*
	 * Weight of edge
	 * Has to be a unique value
	 */

    int weight;

	/*
	 * List to keep information about the nodes the edge is connected to.
	 * Will have 2 values
	 */

    List<Nodes_Interface> connectedNodes = new LinkedList<Nodes_Interface>();

	/*
	 * List to keep sense of direction for core
	 * 1st value is source node, 2nd value is destination node
	 */

    List<Nodes_Interface> towardsCore;

	/*
	 * Boolean to store value if the edge is part of a fragment
	 */

    Boolean inFragment;

	/*
	 * List to keep sense of direction for candidate MOE
	 * 1st value is source node, 2nd value is destination node
	 */

    List<Nodes> towardsCandidateMOE;

	/*
	 * get edge weight
	 */

    public int getWeight() {
        return this.weight;
    }

	/*
	 * set edge weight
	 */

    public void setWeight(int weight) {
        this.weight = weight;
    }

	/*
	 * fetch list of nodes connected to the edge
	 */

    public List<Nodes_Interface> getConnectedNodes(){
        return this.connectedNodes;
    }

	/*
	 * set the list of nodes the edge is connected to
	 */

    public void setConnectedNodes(Nodes_Interface firstNode, Nodes_Interface secondNode) {
        this.connectedNodes.add(firstNode);
        this.connectedNodes.add(secondNode);
    }

	/*
	 * fetch list of nodes for sense of direction for core
	 */

    public List<Nodes_Interface> getTowardsCore(){
        return this.towardsCore;
    }

	/*
	 * set source and destination nodes for sense of direction for core
	 */

    public void setTowardsCore(Nodes src, Nodes_Interface dest) {
        this.towardsCore.add(src);
        this.towardsCore.add(dest);
    }

	/*
	 * get information if edge is in fragment
	 */

    public Boolean getInFragment() {
        return this.inFragment;
    }

	/*
	 * set information if edge is in fragment
	 */

    public void setInFragment(Boolean inFragment) {
        this.inFragment = inFragment;
    }

	/*
	 * fetch list of nodes for sense of direction for candidate MOE
	 */

    public List<Nodes> getTowardsCandidateMOE(){
        return this.towardsCandidateMOE;
    }

	/*
	 * set source and destination nodes for sense of direction for candidate MOE
	 */

    public void setTowardsCandidateMOE(Nodes src, Nodes dest) {
        this.towardsCandidateMOE.add(src);
        this.towardsCandidateMOE.add(dest);
    }

    /*
     * Status of the edge (?_in_MST, in_MST, not_in_MST)
     */
    
    public String status;
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getStatus(){
        return this.status;
    }
    
    protected Edges(int weight) {
    	this.status = "?_in_MST";
    	this.weight = weight;
    }
    
}
