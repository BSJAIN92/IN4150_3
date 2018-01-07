
import java.io.Serializable;

public class Message implements Message_Interface, Serializable{
	
	private static final long serialVersionUID = 4745507L;

	
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
	 * Store the type of message
	 * values:
	 * Accept_Message = AM
	 * Change_Root_Message = CRM
	 * Connect_Message = CM
	 * Initiate_Message = IM
	 * Reject_Message = RejM
	 * Report_Message = RepM
	 * Test_Message = TM
	 */
	
	String messageType;
	
	/*
	 * get messageType
	 */
	
	public String getMessageType () {
		return this.messageType;
	}
	
	/*
	 * set messageType
	 */
	
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
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
