
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;


public class Nodes extends UnicastRemoteObject implements Nodes_Interface, Runnable{
	
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
	
	protected Nodes(int totalServers, int serverIndex) throws RemoteException{
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
