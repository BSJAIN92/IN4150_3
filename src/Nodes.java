
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;


public class Nodes extends UnicastRemoteObject implements Nodes_Interface, Runnable{
	
	private static final long serialVersionUID = 4745507L;
	
	private static Logger logger = Logger.getLogger(Nodes.class.getName());
	
	
	protected Nodes(int totalServers, int serverIndex) throws RemoteException{
		super();
	}
	
	public void run() {
		logger.info("Server successfully initialized and started");
	}
	

}
