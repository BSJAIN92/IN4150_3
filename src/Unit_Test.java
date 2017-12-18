
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
		
		try {
			Edges edge1 = new Edges(1);
			Edges edge2 = new Edges(2);
			Edges edge3 = new Edges(3);
			List<Edges_Interface> s1edges;
			List<Edges_Interface> s2edges;
			List<Edges_Interface> s3edges;
						
			s1edges = new LinkedList<Edges_Interface>();
			s1edges.add(edge1);
			s1edges.add(edge2);
			
			s2edges = new LinkedList<Edges_Interface>();
			s2edges.add(edge1);
			s2edges.add(edge3);
			
			s3edges = new LinkedList<Edges_Interface>();
			s3edges.add(edge2);
			s3edges.add(edge3);
						
			server1.setNeighbourEdges(s1edges);
			server2.setNeighbourEdges(s2edges);
			server3.setNeighbourEdges(s3edges);
			
			edge1.setConnectedNodes(server1, server2);
			edge2.setConnectedNodes(server1, server3);
			edge3.setConnectedNodes(server2, server3);
						
			System.out.println(server1.getNeighbourEdges().get(0).getStatus());
			System.out.println(server1.getNeighbourEdges().get(0).getWeight());
			
			server1.wakeup();
			
			System.out.println(server1.getNeighbourEdges().get(0).getStatus());
			System.out.println(server1.getNeighbourEdges().get(1).getStatus());
			
			
			
		}	catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
