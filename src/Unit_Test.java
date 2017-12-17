
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.*;


public class Unit_Test {
	
	private final static Logger logger = Logger.getLogger(Unit_Test.class.getName());
	
	private List<Nodes_Interface> servers;
	private String[] urls;
	
	@Before
	public void init() {
		
		servers = new ArrayList<Nodes_Interface>();
		Configuration config = null;
		
		try {
			config = new PropertiesConfiguration("network.cfg");
			
		}	catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		
		urls = config.getStringArray("node.url");
		servers = new ArrayList<Nodes_Interface>();
		
		for (String url : urls) {
			
			try {
				Nodes_Interface server = (Nodes_Interface) Naming.lookup(url);
				server.reset();
				servers.add(server);
			}	catch (RemoteException e1) {
				e1.printStackTrace();
			}	catch (NotBoundException e2) {
				e2.printStackTrace();
			}	catch (MalformedURLException e3) {
				e3.printStackTrace();
			}
		}
		
	}
	
	
	@Test
	public void test() {
		
		Nodes_Interface server1 = servers.get(0);
		Nodes_Interface server2 = servers.get(1);
		Nodes_Interface server3 = servers.get(2);
		Nodes_Interface server4 = servers.get(3);
		
		Edges edge1 = new Edges(1);
		Edges edge2 = new Edges(2);
		List<Edges> edge;
		
		
		edge = new LinkedList<Edges>();
		edge.add(edge1);
		edge.add(edge2);
		
		
		try {
			server1.setNeighbourEdges(edge);
			System.out.println(server1.getNeighbourEdges().get(0).getStatus());
			System.out.println(server1.getNeighbourEdges().get(0).getWeight());
			
			server1.wakeup();
			
			System.out.println(server1.getNeighbourEdges().get(0).getStatus());
			System.out.println(server1.getNeighbourEdges().get(1).getWeight());
			
			edge1.setConnectedNodes(server1, server2);
			
			
		}	catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
