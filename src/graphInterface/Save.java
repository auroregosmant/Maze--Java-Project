package graphInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import maze.Maze;

public final class Save extends JMenuItem implements ActionListener { //enregistre le labyrinthe dans le fichier texte d'origine
	private Window window;
	
	
	public Save(Window window) {
		super("Save");
		this.window = window;
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String fileName = window.getFile();
		Maze maze = window.getMazeModel();
		try {
			maze.saveToTextFile(fileName);
			JOptionPane.showMessageDialog(null, "Maze saved !" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "File not found" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}