package dijkstra;

import java.util.ArrayList;

public interface GraphInterface { //interface du graphe
	
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex); //permet d'obtenir les successeurs d'un sommet

	public int getWeight(VertexInterface pivot, VertexInterface vertex); //permet d'obtenir le poids d'une arrete entre le pivot et un vertex
	
	public ArrayList <VertexInterface> getAllVerteces(); //permet d'obtenir tous les sommets du graphe
}
