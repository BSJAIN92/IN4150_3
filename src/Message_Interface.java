
public interface Message_Interface {
	
	/*
	 * get source node
	 */
	
	public int getSrc();
	
	/*
	 * set source node
	 */
	
	public void setSrc(int src);
	
	/*
	 * get source node
	 */
	
	public int getDest();
	
	/*
	 * set source node
	 */
	
	public void setDest(int dest);
	
	/*
	 * get connecting edge
	 */
	
	public Edges getChannel();
	
	/*
	 * set connecting edge
	 */
	
	public void setChannel(Edges channel);

}
