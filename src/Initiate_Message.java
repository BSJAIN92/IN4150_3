public class Initiate_Message extends Message {

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

    Initiate_Message(int src, int dest, String messageType){
        this.src = src;
        this.dest = dest;
        this.messageType = messageType;
    }
    
    public int fragmentName;
    
    /*
     * set fragmentName
     */
    
    public void setFragmentName(int fragmentName) {
    	this.fragmentName= fragmentName;
    }
    
    /*
     * get weight
     */
    
    public int getFragmentName() {
    	return this.fragmentName;
    }
    
    public String status;
    
    /*
     * set status
     */
    
    public void setStatus(String status) {
    	this.status = status;
    }
    
    public String getStatus() {
    	return this.status;
    }
    
}
    /*

    @Override
    public void setSrc(Nodes src) {

    }

    @Override
    public void setDest(Nodes dest) {

    }

}
    */