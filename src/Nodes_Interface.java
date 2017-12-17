

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.List;
import java.util.Map;


    public interface Nodes_Interface extends Remote {

	/*
	 * Return serverIndex
	 * Debugging
	 * Private Check: 0000 0000
	 */

    public int getServerIndex() throws RemoteException;

	/*
	 * Reset Server
	 */


	public void reset() throws RemoteException;
	
	public void setNeighbourEdges(List<Edges> neighbourEdges) throws RemoteException;
	
	public List<Edges> getNeighbourEdges() throws RemoteException;
	
	public void setFragmentLevel(int fragmentLevel) throws RemoteException;
	
	public int getFragmentLevel() throws RemoteException;
	
	public void setFragmentName(int fragmentName)throws RemoteException;
	
	public int getFragmentName() throws RemoteException;
	
	public void setCoreOfFragment(boolean coreOfFragment) throws RemoteException;
	
	public boolean getCoreOfFragment() throws RemoteException;
	
	public void setStatus(String status) throws RemoteException;
	
	public String getStatus() throws RemoteException;
	
	public void setMoeCandidate(Edges moeCandidate) throws RemoteException;
	
	public Edges getMoeCandidate() throws RemoteException;
	
	public void setNumberReportMessagesExpected(int numberReportMessagesExpected) throws RemoteException;
	
	public int getNumberReportMessagesExpected() throws RemoteException;
	
	
	/*
	 * get total servers
	 */
	
	public int getTotalServers() throws RemoteException;
	
	/*
	 * set total servers
	 */
	
	public void setTotalServers(int totalServers) throws RemoteException;
	
	/*
	 * Fetch all urls
	 */
	
	public String[] getUrls() throws RemoteException;
	
	/*
	 * set urls
	 */
	
	public void setUrls(String[] urls) throws RemoteException;
	
	
	public void wakeup() throws RemoteException;
	
	public void sendConnectMessage(Edges minimumWeightEdge, int fragmentLevel) throws RemoteException;
	
	public void receiveConnectMessage(Connect_Message C, int fragmentLevel) throws RemoteException;
	
	public void sendInitiateMessage(Edges E, int L, int w, String status) throws RemoteException;
	
	public void receiveInitiateMessage(Initiate_Message C, int L, int N, String status) throws RemoteException;
	
	public void sendTestMessage() throws RemoteException;
	
	public void reportMessage() throws RemoteException;
	
	
}


