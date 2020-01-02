package maze;

import java.awt.Color;

public class EBox extends MBox { //classe case vide
	
	public EBox(int x, int y) {
		super(x,y);
	}
	
	@Override
	public Color getColor() { // la couleur d'une boîte vide sur l'interface est blanc
		return Color.WHITE;
	}
	
	@Override
	public boolean isPossible() { // on peut tracer un chemin en passant par cette case
		return true;
	}


}
