package graphInterface;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Model.DrawingMazeModel;
import maze.Maze;

public final class Case extends JButton implements MouseListener { // classe des cases de l'interface
	private int x; //abscisse de la case
	private int y; // ordonnée de la case
	private Window window;

	public int getCaseX() {
		return x;
	}

	public void setCaseX(int x) {
		this.x = x;
	}

	public int getCaseY() {
		return y;
	}

	public void setCaseY(int y) {
		this.y = y;
	}
		
	public Case(Window window, int x, int y) {
		this.x =x;
		this.y = y;
		this.window = window;
		this.addMouseListener(this);	
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Maze maze = window.getMazeModel();
		DrawingMazeModel mazeModel = window.getModel();
		if (window.isLaunchClicked()==false) //si on a pas clique sur launch on n'est pas dans une procedure de resolution
		{
			if (arg0.getClickCount() == 2) // le double clique permet de changer une case wall en une case vide et vice versa
			{
				Color color = window.getMazeModel().getMBox(this.getCaseX(), this.getCaseY()).getColor();
				if ( color == Color.BLACK)
				{
					window.getMazeModel().changeMBox(this.getCaseY(), this.getCaseX(), 'E');
					this.setBackground(Color.WHITE);
				}
				else
				{
					window.getMazeModel().changeMBox(this.getCaseY(), this.getCaseX(), 'W');
					this.setBackground(Color.BLACK);
				}
				
			}
		}
		else if (window.isLaunchClicked()==true) //si on a lance la procedure de resolution
		{
			if ((maze.isAboxDefined()==false) && (maze.isDboxDefined()==true) && maze.getMBox(this.getCaseX(), this.getCaseY()).isPossible()) //cas ou on va choisir la case d'arrivee qui ne peut etre un mur
			{
				window.getMazeModel().changeMBox(this.getCaseY(), this.getCaseX(), 'A');
				this.setBackground(Color.RED);
				maze.setAboxDefined(true);
				JOptionPane.showMessageDialog(null,"The maze is going to get resolved" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
				window.getWindowPanel().SolveMaze(mazeModel, window); //resolution
				
			}
			else if ((maze.isDboxDefined()==false) && maze.getMBox(this.getCaseX(), this.getCaseY()).isPossible()) // cas ou on va choisir la case de depart qui ne peut etre un mur
			{			
				window.getMazeModel().changeMBox(this.getCaseY(), this.getCaseX(), 'D'); // définir nouvelle DBox
				this.setBackground(Color.BLUE);
				maze.setDboxDefined(true);
				JOptionPane.showMessageDialog(null,"Choose an arrival case" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
				
			}
		}
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {	
	}

	@Override
	public void mousePressed(MouseEvent e) {	
	}	
		
	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}


}