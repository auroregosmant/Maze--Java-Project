package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface { //interface de la fonction pere

	public void setValue(VertexInterface pivot, VertexInterface y);
	//Attribue une valeur à un sommet
	
	public VertexInterface getPrevious(VertexInterface vertex) ;
	//Retourne la valeur du pere d'un sommet
	
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) ;
	//retourne liste des plus courts chemins
}

