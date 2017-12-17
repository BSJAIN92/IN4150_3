import java.util.List;

public interface Edges_Interface {
	
	/*
	 * get edge weight
	 */
	
	public int getWeight();	

	/*
	 * set edge weight
	 */
	
	public void setWeight(int weight);
	
	/*
	 * fetch list of nodes connected to the edge
	 */
	
	public List<Nodes_Interface> getConnectedNodes();
	
	/*
	 * set the list of nodes the edge is connected to
	 */
	
	public void setConnectedNodes(Nodes_Interface firstNode, Nodes_Interface secondNode);
	
	/*
	 * fetch list of nodes for sense of direction for core
	 */
	
	public List<Nodes_Interface> getTowardsCore();
	
	/*
	 * set source and destination nodes for sense of direction for core
	 */
	
	public void setTowardsCore(Nodes src, Nodes_Interface dest);
	
	/*
	 * get information if edge is in fragment
	 */
	
	public Boolean getInFragment();
	
	/*
	 * set information if edge is in fragment
	 */
	
	public void setInFragment(Boolean inFragment);
	
	/*
	 * fetch list of nodes for sense of direction for candidate MOE
	 */
	
	public List<Nodes> getTowardsCandidateMOE();
	
	/*
	 * set source and destination nodes for sense of direction for candidate MOE
	 */
	
	public void setTowardsCandidateMOE(Nodes src, Nodes dest);
	
	public void setStatus(String status);
	
	public String getStatus();
	
}
