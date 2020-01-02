package maze;
import java.awt.Color;
import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface { //classe m�re des diff�rents types de box

	private int x;
	private int y;


	public MBox(int x, int y) { // Initialisation des coordonnees
		super();
		this.x = x;
		this.y = y;
	}


	public final int getX() {
		return x;
	}


	public final void setX(int x) {
		this.x = x;
	}


	public final int getY() {
		return y;
	}


	public final void setY(int y) {
		this.y = y;
	}

	public abstract Color getColor() ; //attribut une couleur � chaque type de box pour l'interface, permet aussi de les diff�rencier
	
	public abstract boolean isPossible(); //booleen indiquant si une case est un mur ou non, ie s'il elle est accessible ou non


	public boolean isDifferentFrom(MBox box) { //test si 2 box sont �gales
		
		return ((this.x != box.getX()) && (this.y != box.getY()));
	}

}

	