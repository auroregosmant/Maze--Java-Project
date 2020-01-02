package graphInterface;

import javax.swing.JMenu;

public class File extends JMenu{ // menu
	private SaveAs saveAs;
	private Exit exit;
	private openFile openfile;
	private Save save;
	
	public File (Window window) {
		super("File");
		this.add(openfile = new openFile(window));
		this.add(save = new Save(window));
		this.add(saveAs=new SaveAs(window));
		this.add(exit = new Exit(window));
	}
	
}
