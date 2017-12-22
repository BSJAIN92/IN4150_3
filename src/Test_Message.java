
public class Test_Message extends Message{
	
	Test_Message(int src, int dest, String messageType){
        this.src = src;
        this.dest = dest;
        this.messageType = messageType;
    }
	
	public int fragmentLevel;
    
    /*
     * set fragmentLevel
     */
    
    public void setFragmentLevel(int fragmentLevel) {
    	this.fragmentLevel = fragmentLevel;
    }
    
    /*
     * get fragmentLevel
     */
    
    public int getFragmentLevel() {
    	return this.fragmentLevel;
    }
    
    public int fragmentName;
    
    /*
     * set fragmentLevel
     */
    
    public void setFragmentName(int fragmentName) {
    	this.fragmentName= fragmentName;
    }
    
    /*
     * get fragmentLevel
     */
    
    public int getFragmentName() {
    	return this.fragmentName;
    }


}
