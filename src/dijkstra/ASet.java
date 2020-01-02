package dijkstra;

import java.util.ArrayList;

public final class ASet implements ASetInterface { //ensemble des sommets deja visites
	
	ArrayList<VertexInterface> aset;
	
	public ASet()
	{
		aset = new ArrayList<VertexInterface>();
	}

	@Override
	public void add(VertexInterface vertex) { //ajoute un sommet a aset
		aset.add(vertex);
	}

	@Override
	public boolean contains(VertexInterface vertex) { //regarde si aset contient un vertex en particulier
		for (VertexInterface x : aset)
		{
			if (x==vertex)
			{
				return true;
			}
		}	
		return false;	
	}
}
