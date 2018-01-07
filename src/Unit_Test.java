
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
		/*
		Nodes_Interface server1 = servers.get(0);
		Nodes_Interface server2 = servers.get(1);
		Nodes_Interface server3 = servers.get(2);
		Nodes_Interface server4 = servers.get(3);
		Nodes_Interface server5 = servers.get(4);
		Nodes_Interface server6 = servers.get(5);
		Nodes_Interface server7 = servers.get(6);
		Nodes_Interface server8 = servers.get(7);
		Nodes_Interface server9 = servers.get(8);
		Nodes_Interface server10 = servers.get(9);
		Nodes_Interface server11 = servers.get(10);
		Nodes_Interface server12 = servers.get(11);
		Nodes_Interface server13 = servers.get(12);
		Nodes_Interface server14 = servers.get(13);
		Nodes_Interface server15 = servers.get(14);
		Nodes_Interface server16 = servers.get(15);
		Nodes_Interface server17 = servers.get(16);
		Nodes_Interface server18 = servers.get(17);
		Nodes_Interface server19 = servers.get(18);
		Nodes_Interface server20 = servers.get(19);
		Nodes_Interface server21 = servers.get(20);
		Nodes_Interface server22 = servers.get(21);
		Nodes_Interface server23 = servers.get(22);
		Nodes_Interface server24 = servers.get(23);
		Nodes_Interface server25 = servers.get(24);
		Nodes_Interface server26 = servers.get(25);
		Nodes_Interface server27 = servers.get(26);
		Nodes_Interface server28 = servers.get(27);
		Nodes_Interface server29 = servers.get(28);
		Nodes_Interface server30 = servers.get(29);
		*/
		
		Nodes_Interface server1 = servers.get(0);
		Nodes_Interface server2 = servers.get(1);
		Nodes_Interface server3 = servers.get(2);
		Nodes_Interface server4 = servers.get(3);
		
		
		try {
			Edges edge1 = new Edges(1);
			Edges edge2 = new Edges(2);
			Edges edge3 = new Edges(3);
			Edges edge4 = new Edges(4);
			
			List<Edges_Interface>s1edges;
			List<Edges_Interface>s2edges;
			List<Edges_Interface>s3edges;
			List<Edges_Interface>s4edges;
			
			s1edges = new LinkedList<Edges_Interface>();
			s1edges.add(edge2);
			s1edges.add(edge4);
			
			s2edges = new LinkedList<Edges_Interface>();
			s2edges.add(edge2);
			s2edges.add(edge3);
			
			s3edges = new LinkedList<Edges_Interface>();
			s3edges.add(edge3);
			s3edges.add(edge1);
			
			s4edges = new LinkedList<Edges_Interface>();
			s4edges.add(edge4);
			s4edges.add(edge1);
			
			server1.setNeighbourEdges(s1edges);
			server2.setNeighbourEdges(s2edges);
			server3.setNeighbourEdges(s3edges);
			server4.setNeighbourEdges(s4edges);
			
			edge1.setConnectedNodes(server4,server3);
			edge2.setConnectedNodes(server1,server2);
			edge3.setConnectedNodes(server2,server3);
			edge4.setConnectedNodes(server1,server4);
			
			/*
			Edges edge1 = new Edges(1);
			Edges edge2 = new Edges(2);
			Edges edge3 = new Edges(3);
			Edges edge4 = new Edges(4);
			Edges edge5 = new Edges(5);
			Edges edge6 = new Edges(6);
			Edges edge7 = new Edges(7);
			Edges edge8 = new Edges(8);
			Edges edge9 = new Edges(9);
			Edges edge10 = new Edges(10);
			Edges edge11 = new Edges(11);
			Edges edge12 = new Edges(12);
			Edges edge13 = new Edges(13);
			Edges edge14 = new Edges(14);
			Edges edge15 = new Edges(15);
			Edges edge16 = new Edges(16);
			Edges edge17 = new Edges(17);
			Edges edge18 = new Edges(18);
			Edges edge19 = new Edges(19);
			Edges edge20 = new Edges(20);
			Edges edge21 = new Edges(21);
			Edges edge22 = new Edges(22);
			Edges edge23 = new Edges(23);
			Edges edge24 = new Edges(24);
			Edges edge25 = new Edges(25);
			Edges edge26 = new Edges(26);
			Edges edge27 = new Edges(27);
			Edges edge28 = new Edges(28);
			Edges edge29 = new Edges(29);
			Edges edge30 = new Edges(30);
			Edges edge31 = new Edges(31);
			Edges edge32 = new Edges(32);
			Edges edge33 = new Edges(33);
			Edges edge34 = new Edges(34);
			Edges edge35 = new Edges(35);
			Edges edge36 = new Edges(36);
			Edges edge37 = new Edges(37);
			Edges edge38 = new Edges(38);
			Edges edge39 = new Edges(39);
			Edges edge40 = new Edges(40);
			Edges edge41 = new Edges(41);
			Edges edge42 = new Edges(42);
			Edges edge43 = new Edges(43);
			Edges edge44 = new Edges(44);
			Edges edge45 = new Edges(45);
			Edges edge46 = new Edges(46);
			Edges edge47 = new Edges(47);
			Edges edge48 = new Edges(48);
			Edges edge49 = new Edges(49);


			List<Edges_Interface>s1edges;
			List<Edges_Interface>s2edges;
			List<Edges_Interface>s3edges;
			List<Edges_Interface>s4edges;
			List<Edges_Interface>s5edges;
			List<Edges_Interface>s6edges;
			List<Edges_Interface>s7edges;
			List<Edges_Interface>s8edges;
			List<Edges_Interface>s9edges;
			List<Edges_Interface>s10edges;
			List<Edges_Interface>s11edges;
			List<Edges_Interface>s12edges;
			List<Edges_Interface>s13edges;
			List<Edges_Interface>s14edges;
			List<Edges_Interface>s15edges;
			List<Edges_Interface>s16edges;
			List<Edges_Interface>s17edges;
			List<Edges_Interface>s18edges;
			List<Edges_Interface>s19edges;
			List<Edges_Interface>s20edges;
			List<Edges_Interface>s21edges;
			List<Edges_Interface>s22edges;
			List<Edges_Interface>s23edges;
			List<Edges_Interface>s24edges;
			List<Edges_Interface>s25edges;
			List<Edges_Interface>s26edges;
			List<Edges_Interface>s27edges;
			List<Edges_Interface>s28edges;
			List<Edges_Interface>s29edges;
			List<Edges_Interface>s30edges;
			
			
			
			s1edges = new LinkedList<Edges_Interface>();
			s1edges.add(edge8);
			s1edges.add(edge28);
			
			s2edges = new LinkedList<Edges_Interface>();
			s2edges.add(edge28);
			s2edges.add(edge45);
			s2edges.add(edge9);
			
			s3edges = new LinkedList<Edges_Interface>();
			s3edges.add(edge9);
			s3edges.add(edge49);
			s3edges.add(edge39);
			
			s4edges = new LinkedList<Edges_Interface>();
			s4edges.add(edge39);
			s4edges.add(edge46);
			s4edges.add(edge44);
			
			s5edges = new LinkedList<Edges_Interface>();
			s5edges.add(edge44);
			s5edges.add(edge24);
			
			s6edges = new LinkedList<Edges_Interface>();
			s6edges.add(edge8);
			s6edges.add(edge18);
			s6edges.add(edge30);
			
			s7edges = new LinkedList<Edges_Interface>();
			s7edges.add(edge30);
			s7edges.add(edge45);
			s7edges.add(edge13);
			s7edges.add(edge23);
			
			s8edges = new LinkedList<Edges_Interface>();
			s8edges.add(edge23);
			s8edges.add(edge49);
			s8edges.add(edge43);
			s8edges.add(edge20);
						
			s9edges = new LinkedList<Edges_Interface>();
			s9edges.add(edge20);
			s9edges.add(edge46);
			s9edges.add(edge12);
			s9edges.add(edge33);
			
			s10edges = new LinkedList<Edges_Interface>();
			s10edges.add(edge33);
			s10edges.add(edge24);
			s10edges.add(edge5);
			
			s11edges = new LinkedList<Edges_Interface>();
			s11edges.add(edge18);
			s11edges.add(edge29);
			s11edges.add(edge4);
			
			s12edges = new LinkedList<Edges_Interface>();
			s12edges.add(edge4);
			s12edges.add(edge13);
			s12edges.add(edge7);
			s12edges.add(edge38);
			
			s13edges = new LinkedList<Edges_Interface>();
			s13edges.add(edge38);
			s13edges.add(edge43);
			s13edges.add(edge1);
			s13edges.add(edge27);
			
			s14edges = new LinkedList<Edges_Interface>();
			s14edges.add(edge27);
			s14edges.add(edge12);
			s14edges.add(edge40);
			s14edges.add(edge17);
			
			s15edges = new LinkedList<Edges_Interface>();
			s15edges.add(edge17);
			s15edges.add(edge5);
			s15edges.add(edge47);
			
			s16edges = new LinkedList<Edges_Interface>();
			s16edges.add(edge29);
			s16edges.add(edge19);
			s16edges.add(edge14);
			
			s17edges = new LinkedList<Edges_Interface>();
			s17edges.add(edge14);
			s17edges.add(edge7);
			s17edges.add(edge26);
			s17edges.add(edge42);
			
			s18edges = new LinkedList<Edges_Interface>();
			s18edges.add(edge42);
			s18edges.add(edge1);
			s18edges.add(edge31);
			s18edges.add(edge34);
			
			s19edges = new LinkedList<Edges_Interface>();
			s19edges.add(edge34);
			s19edges.add(edge40);
			s19edges.add(edge11);
			s19edges.add(edge21);
			
			s20edges = new LinkedList<Edges_Interface>();
			s20edges.add(edge21);
			s20edges.add(edge47);
			s20edges.add(edge16);
			
			s21edges = new LinkedList<Edges_Interface>();
			s21edges.add(edge19);
			s21edges.add(edge35);
			s21edges.add(edge3);
			
			s22edges = new LinkedList<Edges_Interface>();
			s22edges.add(edge3);
			s22edges.add(edge26);
			s22edges.add(edge41);
			s22edges.add(edge22);
			
			s23edges = new LinkedList<Edges_Interface>();
			s23edges.add(edge22);
			s23edges.add(edge31);
			s23edges.add(edge25);
			s23edges.add(edge2);
			
			s24edges = new LinkedList<Edges_Interface>();
			s24edges.add(edge2);
			s24edges.add(edge11);
			s24edges.add(edge6);
			s24edges.add(edge37);
			
			s25edges = new LinkedList<Edges_Interface>();
			s25edges.add(edge37);
			s25edges.add(edge16);
			s25edges.add(edge36);
			
			s26edges = new LinkedList<Edges_Interface>();
			s26edges.add(edge35);
			s26edges.add(edge32);
			
			s27edges = new LinkedList<Edges_Interface>();
			s27edges.add(edge32);
			s27edges.add(edge41);
			s27edges.add(edge10);
			
			s28edges = new LinkedList<Edges_Interface>();
			s28edges.add(edge10);
			s28edges.add(edge25);
			s28edges.add(edge48);
			
			s29edges = new LinkedList<Edges_Interface>();
			s29edges.add(edge48);
			s29edges.add(edge6);
			s29edges.add(edge15);
			
			s30edges = new LinkedList<Edges_Interface>();
			s30edges.add(edge15);
			s30edges.add(edge36);
			
			
			server1.setNeighbourEdges(s1edges);
			server2.setNeighbourEdges(s2edges);
			server3.setNeighbourEdges(s3edges);
			server4.setNeighbourEdges(s4edges);
			server5.setNeighbourEdges(s5edges);
			server6.setNeighbourEdges(s6edges);
			server7.setNeighbourEdges(s7edges);
			server8.setNeighbourEdges(s8edges);
			server9.setNeighbourEdges(s9edges);
			server10.setNeighbourEdges(s10edges);
			server11.setNeighbourEdges(s11edges);
			server12.setNeighbourEdges(s12edges);
			server13.setNeighbourEdges(s13edges);
			server14.setNeighbourEdges(s14edges);
			server15.setNeighbourEdges(s15edges);
			server16.setNeighbourEdges(s16edges);
			server17.setNeighbourEdges(s17edges);
			server18.setNeighbourEdges(s18edges);
			server19.setNeighbourEdges(s19edges);
			server20.setNeighbourEdges(s20edges);
			server21.setNeighbourEdges(s21edges);
			server22.setNeighbourEdges(s22edges);
			server23.setNeighbourEdges(s23edges);
			server24.setNeighbourEdges(s24edges);
			server25.setNeighbourEdges(s25edges);
			server26.setNeighbourEdges(s26edges);
			server27.setNeighbourEdges(s27edges);
			server28.setNeighbourEdges(s28edges);
			server29.setNeighbourEdges(s29edges);
			server30.setNeighbourEdges(s30edges);

			
			edge1.setConnectedNodes(server13,server18);
			edge2.setConnectedNodes(server23,server24);
			edge3.setConnectedNodes(server21,server22);
			edge4.setConnectedNodes(server11,server12);
			edge5.setConnectedNodes(server10,server15);
			edge6.setConnectedNodes(server24,server29);
			edge7.setConnectedNodes(server12,server17);
			edge8.setConnectedNodes(server1,server6);
			edge9.setConnectedNodes(server2,server3);
			edge10.setConnectedNodes(server27,server28);
			edge11.setConnectedNodes(server19,server24);
			edge12.setConnectedNodes(server9,server14);
			edge13.setConnectedNodes(server7,server12);
			edge14.setConnectedNodes(server16,server17);
			edge15.setConnectedNodes(server29,server30);
			edge16.setConnectedNodes(server20,server25);
			edge17.setConnectedNodes(server14,server15);
			edge18.setConnectedNodes(server6,server11);
			edge19.setConnectedNodes(server16,server21);
			edge20.setConnectedNodes(server8,server9);
			edge21.setConnectedNodes(server19,server20);
			edge22.setConnectedNodes(server22,server23);
			edge23.setConnectedNodes(server7,server8);
			edge24.setConnectedNodes(server5,server10);
			edge25.setConnectedNodes(server23,server28);
			edge26.setConnectedNodes(server17,server22);
			edge27.setConnectedNodes(server13,server14);
			edge28.setConnectedNodes(server1,server2);
			edge29.setConnectedNodes(server11,server16);
			edge30.setConnectedNodes(server6,server7);
			edge31.setConnectedNodes(server18,server23);
			edge32.setConnectedNodes(server26,server27);
			edge33.setConnectedNodes(server9,server10);
			edge34.setConnectedNodes(server18,server19);
			edge35.setConnectedNodes(server21,server26);
			edge36.setConnectedNodes(server25,server30);
			edge37.setConnectedNodes(server24,server25);
			edge38.setConnectedNodes(server12,server13);
			edge39.setConnectedNodes(server3,server4);
			edge40.setConnectedNodes(server14,server19);
			edge41.setConnectedNodes(server22,server27);
			edge42.setConnectedNodes(server17,server18);
			edge43.setConnectedNodes(server8,server13);
			edge44.setConnectedNodes(server4,server5);
			edge45.setConnectedNodes(server2,server7);
			edge46.setConnectedNodes(server4,server9);
			edge47.setConnectedNodes(server15,server20);
			edge48.setConnectedNodes(server28,server29);
			edge49.setConnectedNodes(server3,server8);
			*/
			//System.out.println(server1.getNeighbourEdges().get(0).getStatus());
			//System.out.println(server1.getNeighbourEdges().get(0).getWeight());
			
			server1.wakeup();
			
			Thread.sleep(10000);
			
			System.out.println(server1.getMessageQueue().size());
			
			if (server1.getMessageQueue().size() > 0) {
				server1.getMessageQueue().get(0).getMessageType();
			}
			
			
			System.out.println(edge1.getStatus());
			System.out.println(edge2.getStatus());
			System.out.println(edge3.getStatus());
			System.out.println(edge4.getStatus());
			/*
			System.out.println(edge5.getStatus());
			System.out.println(edge6.getStatus());
			System.out.println(edge7.getStatus());
			System.out.println(edge8.getStatus());
			System.out.println(edge9.getStatus());
			System.out.println(edge10.getStatus());
			System.out.println(edge11.getStatus());
			System.out.println(edge12.getStatus());
			System.out.println(edge13.getStatus());
			System.out.println(edge14.getStatus());
			System.out.println(edge15.getStatus());
			System.out.println(edge16.getStatus());
			System.out.println(edge17.getStatus());
			System.out.println(edge18.getStatus());
			System.out.println(edge19.getStatus());
			System.out.println(edge20.getStatus());
			System.out.println(edge21.getStatus());
			System.out.println(edge22.getStatus());
			System.out.println(edge23.getStatus());
			System.out.println(edge24.getStatus());
			System.out.println(edge25.getStatus());
			System.out.println(edge26.getStatus());
			System.out.println(edge27.getStatus());
			System.out.println(edge28.getStatus());
			System.out.println(edge29.getStatus());
			System.out.println(edge30.getStatus());
			System.out.println(edge31.getStatus());
			System.out.println(edge32.getStatus());
			System.out.println(edge33.getStatus());
			System.out.println(edge34.getStatus());
			System.out.println(edge35.getStatus());
			System.out.println(edge36.getStatus());
			System.out.println(edge37.getStatus());
			System.out.println(edge38.getStatus());
			System.out.println(edge39.getStatus());
			System.out.println(edge40.getStatus());
			System.out.println(edge41.getStatus());
			System.out.println(edge42.getStatus());
			System.out.println(edge43.getStatus());
			System.out.println(edge44.getStatus());
			System.out.println(edge45.getStatus());
			System.out.println(edge46.getStatus());
			System.out.println(edge47.getStatus());
			System.out.println(edge48.getStatus());
			System.out.println(edge49.getStatus());
			*/
			
			Assert.assertTrue(edge1.getStatus().equals("in_MST"));
			Assert.assertTrue(edge2.getStatus().equals("in_MST"));
			Assert.assertTrue(edge3.getStatus().equals("in_MST"));
			Assert.assertTrue(edge4.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge5.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge6.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge7.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge8.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge9.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge10.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge11.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge12.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge13.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge14.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge15.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge16.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge17.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge18.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge19.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge20.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge21.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge22.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge23.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge24.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge25.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge26.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge27.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge28.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge29.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge30.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge31.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge32.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge33.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge34.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge35.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge36.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge37.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge38.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge39.getStatus().equals("in_MST"));
			//Assert.assertTrue(edge40.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge41.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge42.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge43.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge44.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge45.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge46.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge47.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge48.getStatus().equals("not_in_MST"));
			//Assert.assertTrue(edge49.getStatus().equals("not_in_MST"));

			
			//System.out.println(server1.getNeighbourEdges().get(0).getStatus());
			//System.out.println(server1.getNeighbourEdges().get(1).getStatus());
			
			
			
		}	catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
