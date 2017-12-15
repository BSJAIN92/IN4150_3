
import java.rmi.RemoteException;
import java.util.*;

public class Test_Code {
	
	private static Edges edge1 = new Edges();
	private static Edges edge2 = new Edges();
	private static List<Edges> edge;
	
	public static void main (String args[]) {
		
		edge = new LinkedList<Edges>();
		edge.add(edge1);
		edge.add(edge2);
		
		try {
			Nodes node1 = new Nodes(3, 1);
			System.out.println(node1.getStatusOfEdge());
		}catch (RemoteException e){
			e.printStackTrace();
		}

	}
	
	
}
