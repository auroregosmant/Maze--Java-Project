package graphInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import maze.Maze;

public final class SaveAs extends JMenuItem implements ActionListener { // permet d'enregistrer sous dans un fichier texte le labyrinthe
	private Window window;
	
	
	public SaveAs(Window window) {
		super("SaveAs");
		this.window = window;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String fileName = JOptionPane.showInputDialog(null, "Name of the file ", JOptionPane.QUESTION_MESSAGE); //entrer le nom du fichier
		Maze maze = window.getMazeModel();
		
		try {
			maze.saveToTextFile(fileName);
			JOptionPane.showMessageDialog(null, "Maze saved !" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "File not found" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
		}

	}
}
