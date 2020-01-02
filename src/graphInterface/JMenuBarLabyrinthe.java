package graphInterface;

import javax.swing.JMenuBar;

import Model.Launch;
import Model.Reset;

public class JMenuBarLabyrinthe extends JMenuBar { //bar de menu du labyrinthe
	
	private File file;
	private Reset reset;
	private Launch launch;
	private Exit exit;

	
	public JMenuBarLabyrinthe(Window window) {
		super();
		add(file = new File(window));
		add(launch = new Launch(window));
		add(reset = new Reset(window));
		
	}
}
