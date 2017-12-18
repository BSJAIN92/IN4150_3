
import java.io.Serializable;

public class Message implements Message_Interface, Serializable{
	
	/*
	 * source node - node sending the message
	 */
	
	int src;
	
	/*
	 * destination node - node receiving the message
	 */
	
	int dest;
	
	/*
	 * edge connecting the source and destination nodes
	 */
	
	Edges_Interface channel;
	
	/*
	 * get source node
	 */
	
	public int getSrc() {
		return this.src;
	}
	
	/*
	 * set source node
	 */
	
	public void setSrc(int src) {
		this.src = src;
	}
	
	/*
	 * get source node
	 */
	
	public int getDest() {
		return this.dest;
	}
	
	/*
	 * set source node
	 */
	
	public void setDest(int dest) {
		this.dest = dest;
	}
	
	/*
	 * get connecting edge
	 */
	
	public Edges_Interface getChannel() {
		return this.channel;
	}
	
	/*
	 * set connecting edge
	 */
	
	public void setChannel(Edges_Interface channel) {
		this.channel = channel;
	}
	
}
