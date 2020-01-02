package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import graphInterface.Window;

public final class Launch extends JMenuItem implements ActionListener { //Launch permet de lancer la résolution du labyrinthe
	private Window window;
	
	public Launch(Window window) {
		super("Launch");
		addActionListener(this);
		this.window = window;
		
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		JOptionPane.showMessageDialog(null,"Choose a departure case" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
		window.setLaunchClicked(true); //booleen indiquant qu'on  lancé la résolution du labyrinthe
	}
	
		
}