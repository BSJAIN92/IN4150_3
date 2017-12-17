import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Edges_Interface extends Remote{
	
	/*
	 * get edge weight
	 */
	
	public int getWeight() throws RemoteException;	

	/*
	 * set edge weight
	 */
	
	public void setWeight(int weight) throws RemoteException;
	
	/*
	 * fetch list of nodes connected to the edge
	 */
	
	public List<Nodes_Interface> getConnectedNodes() throws RemoteException;
	
	/*
	 * set the list of nodes the edge is connected to
	 */
	
	public void setConnectedNodes(Nodes_Interface firstNode, Nodes_Interface secondNode) throws RemoteException;
	
	/*
	 * fetch list of nodes for sense of direction for core
	 */
	
	public List<Nodes_Interface> getTowardsCore() throws RemoteException;
	
	/*
	 * set source and destination nodes for sense of direction for core
	 */
	
	public void setTowardsCore(Nodes src, Nodes_Interface dest) throws RemoteException;
	
	/*
	 * get information if edge is in fragment
	 */
	
	public Boolean getInFragment() throws RemoteException;
	
	/*
	 * set information if edge is in fragment
	 */
	
	public void setInFragment(Boolean inFragment) throws RemoteException;
	
	/*
	 * fetch list of nodes for sense of direction for candidate MOE
	 */
	
	public List<Nodes> getTowardsCandidateMOE() throws RemoteException;
	
	/*
	 * set source and destination nodes for sense of direction for candidate MOE
	 */
	
	public void setTowardsCandidateMOE(Nodes src, Nodes dest) throws RemoteException;
	
	public void setStatus(String status) throws RemoteException;
	
	public String getStatus() throws RemoteException;
	
}
