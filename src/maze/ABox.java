package maze;

import java.awt.Color;

public final class ABox extends EBox{ //classe case d'arrivée

	public ABox(int x, int y) {
		super(x,y);
		
	}
	
	public Color getColor() { // Couleur de l'arrivée sur l'interface : rouge
		return Color.RED;
	}
	
	
	@Override
	public boolean isPossible() { //On peut tracer un chemin en passant par cette boîte
		return true;
	}

	public ABox getArrivalBox() {
		return this;
	}

}
