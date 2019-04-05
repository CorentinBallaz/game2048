import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import package2048.Jeu2048;

public class SauvegarderListener implements ActionListener {
	private Sauvegarder jeu;
	
	public SauvegarderListener(Sauvegarder jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// on garde une trace du jeu (terminal et fichier)
		Logger logger = new Logger(0);
		logger.log(logger.ALL, "Click sur Sauvegarder");
		
		// avec le fichier de configuration
		Logger log= new Logger("config.txt");
		log.log("Click sur Sauvegarder");
		
		// on enregistre la grille de jeu à sauvegarder
		jeu.enregistrerGrille();
		
		// on enregistre le score du jeu à sauvegarder
		jeu.enregistrerScore();
	}
}
