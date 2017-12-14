
public interface Message_Interface {
	
	/*
	 * get source node
	 */
	
	public Nodes getSrc();
	
	/*
	 * set source node
	 */
	
	public void setSrc(Nodes src);
	
	/*
	 * get source node
	 */
	
	public Nodes getDest();
	
	/*
	 * set source node
	 */
	
	public void setDest(Nodes dest);
	
	/*
	 * get connecting edge
	 */
	
	public Edges getChannel();
	
	/*
	 * set connecting edge
	 */
	
	public void setChannel(Edges channel);

}
