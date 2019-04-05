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
		// on �crit dans la trace du jeu lorsque le joueur ferme la fen�tre
		Logger logger = new Logger(0);
		logger.log(logger.ALL, "Fermer fen�tre"); // dans le fichier		
		
		
		// avec le fichier de configuration
		Logger log= new Logger("config.txt");
		log.log("Fermer fen�tre");
		
		// on enregistre le meilleur score
		sauvegarde.enregistrerMeilleurScore();
		
		// on ferme la fen�tre
		fenetre.dispose();
		}
}