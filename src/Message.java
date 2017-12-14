
public class Message implements Message_Interface{
	
	/*
	 * source node - node sending the message
	 */
	
	Nodes src;
	
	/*
	 * destination node - node receiving the message
	 */
	
	Nodes dest;
	
	/*
	 * edge connecting the source and destination nodes
	 */
	
	Edges channel;
	
	/*
	 * get source node
	 */
	
	public Nodes getSrc() {
		return this.src;
	}
	
	/*
	 * set source node
	 */
	
	public void setSrc(Nodes src) {
		this.src = src;
	}
	
	/*
	 * get source node
	 */
	
	public Nodes getDest() {
		return this.dest;
	}
	
	/*
	 * set source node
	 */
	
	public void setDest(Nodes dest) {
		this.dest = dest;
	}
	
	/*
	 * get connecting edge
	 */
	
	public Edges getChannel() {
		return this.channel;
	}
	
	/*
	 * set connecting edge
	 */
	
	public void setChannel(Edges channel) {
		this.channel = channel;
	}
	
}
