package graphInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

public final class openFile extends JMenuItem implements ActionListener{ //ouvre un fichier texte dans une nouvelle fenêtre
		
		public openFile(Window window) {
			super("OpenFile");
			this.addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) { // sort le chemin d'un fichier
			JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
			dialog.showOpenDialog(null);
			if (dialog.getSelectedFile() != null) {
				dialog.getSelectedFile().getPath();//getAbsoluteFile();
				try {
					new Window(dialog.getSelectedFile().getPath()); //on ouvre une nouvelle fenêtre
				} catch (IOException e) {
					e.printStackTrace();
				}
				//dialog.getSelectedFile().getAbsolutePath());
			  }	
		}
	


}
