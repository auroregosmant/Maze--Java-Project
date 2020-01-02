package graphInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.DrawingMazeModel;
import dijkstra.Dijkstra;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.MBox;
import maze.Maze;

public final class WindowPanel extends JPanel{
	private Case[][] mazeList;

	public Case[][] getMazeList() {
		return mazeList;
	}

	public WindowPanel(Window window, int size, DrawingMazeModel model) throws IOException {
		super();
		this.setLayout(new GridLayout(size, size));		
		this.setPreferredSize(new Dimension(150,150));
		Case[][] mazeList = listCase(window, size);
		this.mazeList = mazeList;
		
		
	}
	
	public Case[][] listCase(Window window, int size) // représente maze sous forme matrice de cases
	{
		Case[][] mazeList = new Case[size][size];  // initialisation de la matrice
		for (int i = 0; i<size ; i++)
		{
			for (int j = 0; j<size ; j++)
			{
				Case mazeCase = new Case(window, i, j);
				mazeCase.setBackground(window.getMazeModel().getMBox(i, j).getColor()); // application de la couleur en fonction du type de box
				mazeCase.setBorderPainted(false);
				mazeCase.setOpaque(true); 
				this.add(mazeCase);		
				mazeList[i][j] = mazeCase ;
			}
		}
		return mazeList;
	}

	public void SolveMaze(DrawingMazeModel model, Window window) { // Résolution du labyrinthe

		Maze maze = model.getMaze();
		if ((maze.isDboxDefined() == true) && (maze.isAboxDefined() == true)) // Si case d'arrivée et de départ sont définies
		{
			PreviousInterface previous = (PreviousInterface) Dijkstra.dijkstra(maze, (VertexInterface) maze.getDbox()); // Résolution de dijkstra à partir de la case de départ
			ArrayList<VertexInterface> shortPath = previous.getShortestPathTo((VertexInterface) maze.getAbox()); // Trouve le plus court chemin jusqu'à case d'arrivée
			int sizePath = shortPath.size();


			try
			{
				if (((maze.getDbox().isDifferentFrom(maze.getAbox())) && (sizePath!=1)) || (!maze.getDbox().isDifferentFrom(maze.getAbox()))) // S'il y a un chemin possible
				{
					for (VertexInterface vertex : shortPath) // Parcours des sommets du plus court chemin
					{ 

						MBox box = (MBox) vertex;
						int abscissa = box.getX();
						int ordinate = box.getY();
						Case caSe = mazeList[abscissa][ordinate];
						caSe.setBackground(Color.MAGENTA); // Modification de la couleur du plus court chemin
					}
				}
			else
			{
				JOptionPane.showMessageDialog(null,"No solution" , "Information Maze", JOptionPane.INFORMATION_MESSAGE); // Si pas de chemin existant
				Case Acase = mazeList[maze.getAbox().getX()][maze.getAbox().getY()];
				Acase.setBackground(Color.MAGENTA);
				Case Dcase = mazeList[maze.getDbox().getX()][maze.getDbox().getY()];
				Dcase.setBackground(Color.MAGENTA);
			}
		} catch  (java.lang.ArrayIndexOutOfBoundsException e) //cette exception arrive dans le cas où l'utilisateur a défini un labyrinthe plus petit que la taille du fichier texte
		{
			JOptionPane.showMessageDialog(null,"No solution" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
			Case Acase = mazeList[maze.getAbox().getX()][maze.getAbox().getY()];
			Acase.setBackground(Color.MAGENTA);
			Case Dcase = mazeList[maze.getDbox().getX()][maze.getDbox().getY()];
			Dcase.setBackground(Color.MAGENTA);
		}

		finally
		{
			maze.setAboxDefined(false);
			maze.setDboxDefined(false);
			window.setLaunchClicked(false);
		}
		

	} 

 }
}
	




