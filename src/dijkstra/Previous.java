package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

public final class Previous extends Dijkstra implements PreviousInterface{ //fonction pere
	
	private final Hashtable<VertexInterface, VertexInterface> previous; //classe associant a chaque sommet son pere

	public Previous()
	{
		previous = new Hashtable<VertexInterface, VertexInterface>(); // creation table vide
	}
	@Override
	public void setValue(VertexInterface y, VertexInterface pivot) { //ajoute un sommet et son pere
		previous.put(y, pivot);
	}
	
	public VertexInterface getPrevious(VertexInterface vertex) //renvoie le pere d'un sommet
	{
		return previous.get(vertex);
	}
	
	@Override
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) { //retourne le plus court chemin
		ArrayList<VertexInterface> shortestPath = new ArrayList<VertexInterface>();
		VertexInterface path = vertex;
		while(path!=null) //tant qu'on a des sommets a examiner
		{	
			shortestPath.add(path);
			path = getPrevious(path); //on recupere le pere
		}
		return shortestPath;
	}
}


