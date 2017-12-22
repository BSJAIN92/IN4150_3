
public class Report_Message extends Message{
	
	Report_Message(int src, int dest, String messageType){
        this.src = src;
        this.dest = dest;
        this.messageType = messageType;
    }
	
	int weight;
	
	/*
	 * set weight
	 */
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/*
	 * get weight
	 */
	
	public int getWeight() {
		return this.weight;
	}
	
}
