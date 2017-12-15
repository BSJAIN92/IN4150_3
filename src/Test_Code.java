
import java.rmi.RemoteException;
import java.util.*;

public class Test_Code {
	
	private static Edges edge1 = new Edges(1);
	private static Edges edge2 = new Edges(2);
	private static List<Edges> edge;
	
	public static void main (String args[]) {
		
		edge = new LinkedList<Edges>();
		edge.add(edge1);
		edge.add(edge2);
		
		try {
			Nodes node1 = new Nodes(3, 1);
			node1.setNeighbourEdges(edge);
			System.out.println(node1.getNeighbourEdges().get(0).getStatus());
			System.out.println(node1.getNeighbourEdges().get(0).getWeight());

		}catch (RemoteException e){
			e.printStackTrace();
		}

	}
	
	
}
