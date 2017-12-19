

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
	
	public void setNeighbourEdges(List<Edges_Interface> neighbourEdges) throws RemoteException;
	
	public List<Edges_Interface> getNeighbourEdges() throws RemoteException;
	
	public void setFragmentLevel(int fragmentLevel) throws RemoteException;
	
	public int getFragmentLevel() throws RemoteException;
	
	public void setFragmentName(int fragmentName)throws RemoteException;
	
	public int getFragmentName() throws RemoteException;
	
	public void setCoreOfFragment(boolean coreOfFragment) throws RemoteException;
	
	public boolean getCoreOfFragment() throws RemoteException;
	
	public void setStatus(String status) throws RemoteException;
	
	public String getStatus() throws RemoteException;
	
	public void setMoeCandidate(Edges_Interface moeCandidate) throws RemoteException;
	
	public Edges_Interface getMoeCandidate() throws RemoteException;
	
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
	
	public Edges_Interface getTestEdge()  throws RemoteException;
	
	public void setTestEdge(Edges_Interface testEdge) throws RemoteException;
	
	public void wakeup() throws RemoteException;
	
	public void sendConnectMessage(Edges_Interface minimumWeightEdge, int fragmentLevel) throws RemoteException;
	
	public void receiveConnectMessage(Connect_Message C, int fragmentLevel) throws RemoteException;
	
	public void sendInitiateMessage(Edges_Interface E, int L, int w, String status) throws RemoteException;
	
	public void receiveInitiateMessage(Initiate_Message C, int L, int N, String status) throws RemoteException;
	
	public void sendTestMessage(Edges_Interface test, int fragmentLevel, int fragmentName) throws RemoteException;
	
    public void test() throws RemoteException;
	
	public void report() throws RemoteException;
	
	public void sendReportMessage(Edges_Interface toCore, int w) throws RemoteException;
	
	public void receiveReportMessage(Report_Message RE, int w) throws RemoteException;
	
	public void changeRoot() throws RemoteException;
	
	public void sendChangeRootMessage (Edges_Interface changeRoot) throws RemoteException;
	
	public void receiveChangeRootMessage(Change_Root_Message CR) throws RemoteException;
	
	public void receiveTestMessage(Test_Message T, int fragmentLevel, int fragmentName) throws RemoteException;
	
	public void sendAcceptMessage(Edges_Interface accept) throws RemoteException;
	
	public void sendRejectMessage(Edges_Interface reject) throws RemoteException;
	
	public void receiveRejectMessage(Reject_Message R) throws RemoteException;
	
	public void receiveAcceptMessage(Accept_Message A) throws RemoteException;
}


