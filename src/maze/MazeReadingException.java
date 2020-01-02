package maze;

public final class MazeReadingException extends Exception { //exception sur la lecture du fichier
	
	
	public MazeReadingException(String fileName, int lineNum) {		
		
		super("L'erreur s'est produite dans le fichier : " + fileName + ", à la ligne : " + lineNum);
		
	}
}
