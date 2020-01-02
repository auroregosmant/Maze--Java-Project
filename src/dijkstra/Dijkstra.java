package dijkstra;

import java.util.ArrayList;

public class Dijkstra { //classe d'application de l'algorithme de Dijkstra
	
	public static PreviousInterface dijkstra(GraphInterface g, VertexInterface r) // appelle l'algorithme de Dijsktra depuis l'exterieur
	{
		return dijkstra(g, r, new ASet(), new Pi(), new Previous());
	}
		
	private static final PreviousInterface dijkstra(GraphInterface g, VertexInterface r, 
											 ASetInterface aSet, PiInterface pi, 
											 PreviousInterface previous) 
	{
				
		ArrayList<VertexInterface> verteces = g.getAllVerteces(); //on recupere tous les sommets du labyrinthe
		int size = verteces.size();
		
		for (VertexInterface x : verteces) //initialisation poids sommets à l'infini.
		{ 															
			pi.setValue(x, Integer.MAX_VALUE);
		}
		VertexInterface pivot = r; //on lance la resolution de dijkstra depuis la case de depart
		pi.setValue(pivot, 0); 	// initialisation valeur du sommet r à 0
		aSet.add(pivot);		// ajout du sommet r dans A.
		
		for (int j=0 ; j< size-1 ; j++) 
		{
			ArrayList<VertexInterface> neighborsPivot = g.getSuccessors(pivot); //on recupere les sommets accessibles depuis pivot
			for (VertexInterface y : neighborsPivot)	// permet calculer un chemin et d'associer un poids aux voisins du pivot 
			{
				if (!aSet.contains(y))	// Si le sommet accessible n'est pas dans A
				{
					int weight = pi.getValue(pivot) + g.getWeight(pivot, y); //on lui calcule un poids depuis le pivot
					if (weight< pi.getValue(y))
					{
						pi.setValue(y, weight);
						previous.setValue(y, pivot); //on definit pivot comme le pere de y
					}
				}
			}
			
			VertexInterface newPivot = null;
			int piNewPivot = Integer.MAX_VALUE;
			
			for (VertexInterface v : verteces) // permet de trouver le plus court chemin de pivot à autre sommet non dans A 
			{
				if (!aSet.contains(v)) //si le sommet n'est pas dans aset
				{
					int piV = pi.getValue(v);
					if (piV < piNewPivot)
					{
						newPivot = v; //on definit comme le nouveau pivot
						piNewPivot = piV;
					}
				}
			}
			
			if (newPivot == null) //si on a pas definit de nouveaux boutons
			{
				return previous; //on retourne la liste du plus court chemin
			}		
			pivot = newPivot;
			pi.setValue(pivot, piNewPivot);
			aSet.add(newPivot);
		}
		return previous;
	}
		
}
