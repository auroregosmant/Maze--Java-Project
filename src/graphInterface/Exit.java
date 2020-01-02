package graphInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import maze.Maze;

public final class Exit extends JMenuItem implements ActionListener {
	private Window window;

	public Exit(Window window) {
		super("Exit");
		this.window = window;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) { 	
		int option = JOptionPane.showConfirmDialog(null, 
				"Do you want to exit ?",
				"Exit", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE);

		if(option == JOptionPane.YES_OPTION) {
			String fileName = window.getFile();
			Maze maze = window.getMazeModel();
			try {
				maze.saveToTextFile(fileName);
				JOptionPane.showMessageDialog(null, "Maze saved !" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "File not found" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);

			}
			System.exit(0);
		}
	}
}
