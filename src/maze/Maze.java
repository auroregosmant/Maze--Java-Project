package maze;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

public final class Maze implements GraphInterface { //classe du labyrinthe

	private MBox[][] maze;
	
	private MBox abox; //le labyrinthe est défini par sa case de départ et sa case d'arrivée
	private MBox dbox;
	private boolean aboxDefined = false; //booléen indiquant si la case de départ a été définie
	private boolean dboxDefined = false; //booléen indiquant si la case d'arrivée a été définie


	public final MBox getAbox() {
		return abox;
	}

	public final void setAbox(MBox abox) {
		this.abox = abox;
	}

	public final MBox getDbox() {
		return dbox;
	}

	public final void setDbox(MBox dbox) {
		this.dbox = dbox;
	}

	public final boolean isAboxDefined() {
		return aboxDefined;
	}

	public final void setAboxDefined(boolean aboxDefined) {
		this.aboxDefined = aboxDefined;
	}

	public final boolean isDboxDefined() {
		return dboxDefined;
	}

	public final void setDboxDefined(boolean dboxDefined) {
		this.dboxDefined = dboxDefined;
	}

	public Maze(int size) //constructeur du labyrinthe : tableau de MBox
	{
		maze = new MBox[size][size];
	}

	public int getSize() {
		return maze.length;
	}


	public MBox getMBox(int x, int y) {
		return maze[x][y];
	}
	

	public final void initFromTextFile(String fileName, int size) throws IOException { //initialisation du labyrinthe à partir du fichier texte et de la taille rentrée par l'utilisateur

		BufferedReader reader = null;
		String line;

		try
		{
			reader = new BufferedReader(new FileReader(fileName));

			int lineNum = 0;
			while ((line = reader.readLine())!= null) 
			{
				
				if (lineNum>size) //si le fichier texte contient trop de lignes
				{
					throw new MazeReadingException(fileName, lineNum);
				}
				for (int colomnNum=0; colomnNum<size; colomnNum++)
				{
					switch (line.charAt(colomnNum)) 
					{
					case 'A': 
						maze[lineNum][colomnNum]= new ABox(lineNum,colomnNum);
						break;
					case 'D' :
						maze[lineNum][colomnNum]= new DBox(lineNum,colomnNum);
						break;
					case 'E' :
						maze[lineNum][colomnNum]= new EBox(lineNum,colomnNum);
						break;
					case 'W' :
						maze[lineNum][colomnNum]= new WBox(lineNum,colomnNum);
						break;
					default :
						throw new MazeReadingException(fileName, lineNum) ; //exception dans le cas ou l'element n'est pas une box
					}

				}
				lineNum++;
			
			}
			if (lineNum != size) // fichier texte trop court
			{
				throw new MazeReadingException(fileName, lineNum);
			}
		}
		catch(FileNotFoundException exc) // erreur lors de l'ouverture
		{
			JOptionPane.showMessageDialog(null, "erreur d'ouverture" , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (MazeReadingException readExc)
		{
			JOptionPane.showMessageDialog(null, readExc.getMessage() , "Information Maze", JOptionPane.INFORMATION_MESSAGE);
		}
		finally {
			if (reader != null ) { //fermer le fichier dans tous les cas
				reader.close();
			}
		}
	}

	public final void saveToTextFile(String fileName) throws FileNotFoundException { //sauvegarde du labyrinthe dans un fichier texte

		PrintWriter printWriter = new PrintWriter(fileName);
		int size = getSize();
		for (int lineNum = 0; lineNum < size; lineNum ++)
		{
			for (int colomnNum = 0; colomnNum < size; colomnNum++)
			{
				if (maze[lineNum][colomnNum].getColor()==Color.RED) //les attributs couleurs permettent de différencier les types de box
				{
					printWriter.print('A');
				}
				else if (maze[lineNum][colomnNum].getColor()==Color.BLUE)
				{
					printWriter.print('D');
				}
				else if (maze[lineNum][colomnNum].getColor()==Color.WHITE)
				{
					printWriter.print('E');
				}
				else if (maze[lineNum][colomnNum].getColor()==Color.BLACK)
				{
					printWriter.print('W');
				}


			}
			printWriter.println();
		}
		printWriter.close();

	}




	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) //permet de trouver les voisins accessibles d'un vertex donné en argument
	{

		MBox box = (MBox) vertex;

		ArrayList<VertexInterface> neighbors = new ArrayList<VertexInterface>(4); //recupere les voisins accessibles du vertex
		int abscissa = box.getX();
		int ordinate = box.getY();
		int sizeMaze = this.getSize();

		if (sizeMaze==0)				//si labyrinthe vide
			return null;
		else
			if (abscissa>0)  //on examine le voisin de gauche
			{
				MBox neighborLeft = this.getMBox(abscissa-1, ordinate);
				if (neighborLeft.isPossible()==true) //si le voisin n'est pas un mur il est accessible
				{
					neighbors.add(neighborLeft);
				}

			}
		if (abscissa<sizeMaze-1)//on examine le voisin de droite
		{
			MBox neighborRight = this.getMBox(abscissa+1, ordinate);
			if (neighborRight.isPossible()==true) //si le voisin n'est pas un mur il est accessible
			{
				neighbors.add(neighborRight);
			}

		}
		if (ordinate>0)//on examine le voisin du dessus
		{
			MBox neighborUp = this.getMBox(abscissa, ordinate-1);
			if (neighborUp.isPossible()==true) //si le voisin n'est pas un mur il est accessible
			{
				neighbors.add(neighborUp);
			}

		}
		if (ordinate<sizeMaze-1)//on examine le voisin du dessous
		{	
			MBox neighborDown = this.getMBox(abscissa, ordinate+1);
			if (neighborDown.isPossible()==true) //si le voisin n'est pas un mur il est accessible
			{
				neighbors.add(neighborDown);
			}

		}
		return(neighbors);
	}


	@Override
	public int getWeight(VertexInterface pivot, VertexInterface vertex) {
		return 1; //le poids des arrêtes pour appliquer Dijkstra est de 1 si un chemin entre 2 sommets est possible
	}

	@Override
	public ArrayList<VertexInterface> getAllVerteces() { //Renvoie le labyrinthe en tableau de MBox
		ArrayList<VertexInterface> allVerteces = new ArrayList<VertexInterface>(getSize()*getSize());
		int size = getSize();
		for (int i=0; i<size; i++)
		{
			for (int j=0; j<size; j++)
			{
				allVerteces.add((VertexInterface) this.getMBox(i, j));
			}
		}
		return allVerteces;
	}
	
	public void changeMBox(int y, int x, char c) { //permet de changer le type d'une MBox
		switch(c) 
		{
		case 'A' : 
			maze[x][y]= new ABox(x,y); 
			this.abox = maze[x][y];
			break;
		case 'D' : 
			maze[x][y] = new DBox(x,y);
			this.dbox = maze[x][y];
			break;
		case 'E' : 
			maze[x][y] = new EBox(x,y);
			break;
		case 'W' :
			maze[x][y] = new WBox(x,y);
			break;
	}
}


}

