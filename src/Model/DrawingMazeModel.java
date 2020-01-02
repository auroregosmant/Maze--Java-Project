package Model;

import java.util.* ;

import maze.ABox;
import maze.Maze;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class DrawingMazeModel extends Observable{
	private Maze maze;
	
	
	public DrawingMazeModel(String fileName) throws IOException{ //intialisation du modèle
		int size = sizeMaze(fileName);
		Maze maze = new Maze(size);
		this.maze = maze;
		maze.initFromTextFile(fileName, size);				
	}

	public final void setMaze(Maze maze) {
		this.maze = maze;
	}

	
	public final Maze getMaze() {
		return maze;
	}
	
	public final static int sizeMaze(String fileName) throws IOException {// taille du fichier texte
		BufferedReader reader = null;
		String line;
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("Erreur d'ouverture");
		}
		line = reader.readLine();
		return (line.length());   
	}
	
	protected void setChanged() {
	}
	
	public void notifyObservers(Observable observable, Object Parameter) {
		
	}
	

}
