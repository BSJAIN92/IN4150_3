
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.List;
import java.util.Map;


public interface Nodes_Interface extends Remote{
	
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

}
