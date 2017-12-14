

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

        public void setFragmentName(String fragmentName)throws RemoteException;

        public String getFragmentName() throws RemoteException;

        public void setCoreOfFragment(boolean coreOfFragment) throws RemoteException;

        public boolean getCoreOfFragment() throws RemoteException;

        public void setStatus(String status) throws RemoteException;

        public String getStatus() throws RemoteException;

        public void setMoeCandidate(Edges moeCandidate) throws RemoteException;

        public Edges getMoeCandidate() throws RemoteException;

        public void setNumberReportMessagesExpected(int numberReportMessagesExpected) throws RemoteException;

        public int getNumberReportMessagesExpected() throws RemoteException;

        public void setStatusOfEdge(Map<Edges, String> statusOfEdge) throws RemoteException;

        public Map<Edges, String> getStatusOfEdge() throws RemoteException;
    }


