package maze;

import java.awt.Color;

public final class DBox extends EBox{ //Classe case de départ
	
	public DBox(int x, int y) {
		super(x,y);

	}
	
	public Color getColor() { //La couleur de la boîte de départ est bleue sur l'interface
		return Color.BLUE;
	}
	
	@Override
	public boolean isPossible() { //on peut tracer un chemin en passant par cette boîte
		return true;
	}
}

