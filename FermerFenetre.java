// BALLAZ Corentin
// RAVIX Anne

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FermerFenetre extends WindowAdapter {
	private Frame fenetre;
	private Sauvegarder sauvegarde;
	
	public FermerFenetre(Frame fenetre, Sauvegarder sauvegarde) {
		this.fenetre = fenetre;
		this.sauvegarde = sauvegarde;
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// on écrit dans la trace du jeu lorsque le joueur ferme la fenêtre
		Logger logger = new Logger(0);
		logger.log(logger.ALL, "Fermer fenêtre"); // dans le fichier		
		
		
		// avec le fichier de configuration
		Logger log= new Logger("config.txt");
		log.log("Fermer fenêtre");
		
		// on enregistre le meilleur score
		sauvegarde.enregistrerMeilleurScore();
		
		// on ferme la fenêtre
		fenetre.dispose();
		}
}