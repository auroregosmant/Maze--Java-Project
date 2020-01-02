package maze;

import java.awt.Color;

public final class WBox extends MBox { //classe des murs
	
	public WBox(int x, int y) {
		super(x,y);
	}
	
	@Override
	public Color getColor() { //la couleur des murs sur l'interface est noire
		return Color.BLACK;
	}
	
	@Override
	public boolean isPossible() { //on ne peut pas tracer un chemin en passant par un mur
		return false;
	}
}
