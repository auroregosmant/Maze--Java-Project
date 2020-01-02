package graphInterface;

import java.awt.Dimension;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import Model.DrawingMazeModel;
import maze.Maze;

public final class Window extends JFrame /*implements Observer*/{
	private JMenuBarLabyrinthe bar;
	private DrawingMazeModel mazeModel;
	private WindowPanel windowPanel ;
	private boolean isLaunchClicked = false; //booleen declarant si on a clique sur launch, pour resoudre le labyrinthe
	private int sizeMazeUser; //taille entree par l'utilisateur
	private boolean sizeIsDefined; //l'utilisateur a defini la taille
	private String file; //fichier initial

	public final int getSizeMazeUser() {
		return sizeMazeUser;
	}

	public final void setSizeMazeUser(int sizeMazeUser) {
		this.sizeMazeUser = sizeMazeUser;
	}

	public final boolean isLaunchClicked() {
		return isLaunchClicked;
	}

	public final void setLaunchClicked(boolean isLaunchClicked) {
		this.isLaunchClicked = isLaunchClicked;
	}
	

	
	public Window(String file) throws IOException {
		super ("Labyrinthe - " + file); // affiche sur la fenêtre Labyrinthe et nom du fichier
		Scanner reader = new Scanner(System.in);  // Reading from System.in : choisir taille labyrinthe
		this.mazeModel = new DrawingMazeModel(file);
		this.file = file;
		int fileSize = DrawingMazeModel.sizeMaze(file);
		
		while (!sizeIsDefined) // permet à l'utilisateur de choisir la taille du labyrinthe
		{
			String sizeEntered = JOptionPane.showInputDialog(null, "Enter a number between 1 and " + fileSize, JOptionPane.QUESTION_MESSAGE);
			try
			{
				this.sizeMazeUser = Integer.valueOf(sizeEntered);
				if (this.sizeMazeUser>0 && this.sizeMazeUser<fileSize+1) //taille comprise entre 1 et taille du fichier texte
				{
					sizeIsDefined = true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Enter a number between 1 and " + fileSize, "Information Maze", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (IllegalArgumentException e1)
			{
				JOptionPane.showMessageDialog(null,"Enter a number between 1 and " + fileSize + " in order to use the maze", "Information Maze", JOptionPane.INFORMATION_MESSAGE);
				reader.close();
				System.exit(0);
			}
			
		}
		reader.close();
		
		
		this.setPreferredSize(new Dimension (500,500));
		
		setJMenuBar(bar=new JMenuBarLabyrinthe(this));
		WindowPanel labPanel = new WindowPanel(this, sizeMazeUser, mazeModel);
		this.windowPanel = labPanel;
        this.setContentPane(labPanel);
        
		pack();
		setVisible(true);
		JOptionPane.showMessageDialog(null,"INSTRUCTIONS :\n" + "\n" + "To resolve the maze, click on the Launch button.\n" +"You can also modify walls into empty cases by double-clicking on them.\n" + "\n" + "You can "
				+ "even generate a new maze by opening a text file. The text must only be written with "
				+ "E and W, and must be a square. Default text files are available in the depository.\n" + "\n"+ "Enjoy ! :)", "Instructions" , JOptionPane.INFORMATION_MESSAGE);


	}
	

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public final Maze getMazeModel() { // obtenir maze à partir du model
		return this.mazeModel.getMaze();
	}
	
	public final DrawingMazeModel getModel() { // obtenir le model du maze
		return this.mazeModel;
	}

	public final void setMazeModel(DrawingMazeModel mazeModel) {
		this.mazeModel = mazeModel;
	}


	public final WindowPanel getWindowPanel() {
		return windowPanel;
	}


	public final void setWindowPanel(WindowPanel windowPanel) {
		this.windowPanel = windowPanel;
	}



		
}

