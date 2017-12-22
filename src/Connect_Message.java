
public class Connect_Message extends Message{
	
	public int fragmentLevel;
	
	Connect_Message(int src, int dest, String messageType){
		this.src = src;
		this.dest = dest;
		this.messageType = messageType;
	}
	
	public void setFragmentLevel(int fragmentLevel) {
		this.fragmentLevel = fragmentLevel;
	}
	
	public int getFragmentLevel() {
		return this.fragmentLevel;
	}

}
