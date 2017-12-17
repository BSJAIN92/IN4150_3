
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.*;
import java.util.logging.Logger;
import java.net.*;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;



public class Manage_Servers implements Manage_Servers_Interface{
	
	private static Logger logger = Logger.getLogger(Manage_Servers.class.getName());
	
	private String[] urls;
	private List<Nodes_Interface> servers;
	
	
	public void startServers() {
		
		Configuration config = null;
		
		//String file = "network.cfg";
		
		/*
		Parameters params = new Parameters();
		File propertiesFile = new File("network.cfg");

		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class).configure(params.properties().setFileName("network.properties"));
		try
		{
		    Configuration config = builder.getConfiguration();
		    //System.out.println(config.getStringArray("node.url"));
		    // config contains all properties read from the file
		}
		catch(ConfigurationException cex)
		{
		    // loading of the configuration file failed
		}
		*/
		
		/*
		 * Registering RMI
		 * Remember to kill PID on 1099
		 */
		
		try {
			LocateRegistry.createRegistry(1099);
		}	catch (RemoteException e) {
			e.printStackTrace();
		}
		
		/*
		 * Starting Security Manager
		 */
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		
		/*
		 * Configuring servers
		 */
		
		try {
			config = new PropertiesConfiguration("network.cfg");
		}catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		
		
		urls = config.getStringArray("node.url");
		servers = new ArrayList<Nodes_Interface>();
		int serverIndex = 0;
		
		for (String url : urls) {
			try {
				
				Nodes_Interface server = new Nodes(urls, serverIndex);
				/*
				 * Starting individual servers and binding url to server
				 */
				new Thread((Nodes) server).start();
				Naming.bind(url, server);
				servers.add(server);
				serverIndex ++;
				System.out.println(server.getServerIndex());
			}	catch (RemoteException e1) {
				e1.printStackTrace();
			}	catch (MalformedURLException e2) {
				e2.printStackTrace();
			}	catch (AlreadyBoundException e3) {
				e3.printStackTrace();
			}
		}
		
		//DA_SES_RMI_Interface server;
		
		for (String url : urls) {
			try {
				Nodes_Interface server = (Nodes_Interface) Naming.lookup(url);
				server.reset();
				servers.add(server);
				
			}	catch (RemoteException e1) {
				e1.printStackTrace();
			}	catch(MalformedURLException e2) {
				e2.printStackTrace();
			}	catch (NotBoundException e3) {
				e3.printStackTrace();
			}
			
		}
	
	return;
	}
	
	/*
	 * Fetch the list of servers
	 */
	
	public List<Nodes_Interface> getServers(){
		return servers;
	}
	
	/*
	 * Fetch list of URLs
	 */
	
	public String[] getUrls() {
		return urls;
	}

}
