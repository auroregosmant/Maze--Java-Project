package dijkstra;

public interface ASetInterface { //interface de Aset
	
	public void add(VertexInterface vertex); // ajout sommet à l'ensemble A
	
	public boolean contains(VertexInterface vertex); //regarde si aset contient un sommet en particulier
		
}
