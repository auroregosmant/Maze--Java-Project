package dijkstra;

import java.util.Hashtable;

public final class Pi implements PiInterface { //fonction pi

	private Hashtable<VertexInterface,Integer> pi;
	
	public Pi()
	{
		this.pi = new Hashtable<VertexInterface, Integer>(); //table avec la valeur de pi associee a chaque vertex
	}
	
	@Override
	public void setValue(VertexInterface vertex, int value) { //change la valeur de pi
		pi.put(vertex, value);
	}

	@Override
	public int getValue(VertexInterface vertex) { //renvoie la valeur de pi
		return pi.get(vertex);
	}

}
