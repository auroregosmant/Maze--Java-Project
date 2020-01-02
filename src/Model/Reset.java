package Model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import graphInterface.Case;
import graphInterface.Window;
import graphInterface.WindowPanel;
import maze.Maze;

public final class Reset extends JMenuItem implements ActionListener{ //permet de réinitialiser le labyrinthe : enlever tous les chemins tracés, les cases de départ/d'arrivée définies
	private Window window;
	
	public Reset(Window window) {
		super("Reset");
		this.window = window;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int size = window.getSizeMazeUser();
		Maze maze = window.getMazeModel();
		WindowPanel windowPanel = window.getWindowPanel(); 
		Case[][] mazeList = windowPanel.getMazeList(); //liste de toutes les cases du labyrinthe
		for (int i = 0; i<size ; i++) //on parcourt toutes les cases du labyrinthe en discriminant s'il s'agit d'une case vide ou d'un mur
		{
			for (int j = 0; j<size; j++)
			{
				Case caSe = mazeList[i][j];
				Color color = maze.getMBox(i, j).getColor();
				if (color != Color.BLACK)
				{
					caSe.setBackground(Color.WHITE);
				}			
			}	
		}	
	}	
}
