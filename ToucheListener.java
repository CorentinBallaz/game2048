// BALLAZ Corentin
// RAVIX Anne

import java.awt.event.*;
import java.io.File;

import javax.swing.JOptionPane;
import package2048.Jeu2048;
 
public class ToucheListener extends KeyAdapter {
	private Jeu2048 jeu;
	private CanvasJeu terrainJeu;
	private Sauvegarder sauvegarde;
	
	public ToucheListener(Jeu2048 jeu, CanvasJeu terrainJeu, Sauvegarder sauvegarde) {
		this.jeu = jeu;
		this.terrainJeu = terrainJeu;
		this.sauvegarde=sauvegarde;
	}
	
	public void caseFusionne() {
		// m�thode permettant d'informer le joueur sur les cases qui viennent de fusionner
		Logger logger = new Logger(0);
				
		String grilleJeu[][] = jeu.getGrilleString(); // on r�cup�re la grille de jeu sous forme de String
		boolean grilleFusion[][] = jeu.tableauFusions(); // on r�cup�re le tableau indiquant les fusions
		String message;
		
		for(int i=0; i<jeu.getNbLignes();i++) {
			// on parcourt les lignes
			for(int j=0;j<jeu.getNbCols();j++) {
				// on parcourt les colonnes
				if(grilleFusion[i][j]==true) {
					// s'il y a une fusion (case(i,j)=true), alors on pr�vient le joueur
					message = "\t" + grilleJeu[i][j] + " est le resultat de la fusion dans (" + i + "," + j +")";
					logger.log(logger.INFO, message); // dans le terminal
					logger.log(logger.ALL,message); // dans le fichier

				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// Logger avec le niveau de priorite minimal
		Logger logger = new Logger(0);

		// Logger avec le fichier de configuration
		Logger log=new Logger("config.txt");
		
		// On d�cale le jeu en fonction de la touche
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_UP) : // Haut
			if(jeu.decaler(0)) {
				// on garde la trace du d�calage
				logger.log(logger.ALL, "Avec les touches : d�calage vers le haut"); // fichier
				log.log("Avec les touches : d�calage vers le haut"); // suivant l'emplacement donn� par le fichier config
				this.caseFusionne(); // on affiche les cases fusionn�es
			}else {
				// si impossible de d�caler, on pr�vient le joueur
				logger.log(logger.IMPORTANT, "Impossible de d�caler vers le haut!"); // erreur terminal
				logger.log(logger.ALL, "\t"+"d�calage impossible"); // fichier
			}
			break;
			
		case (KeyEvent.VK_DOWN): // Bas
			if(jeu.decaler(2)) {
				// on garde la trace du d�calage
				logger.log(logger.ALL, "Avec les touches : d�calage vers le bas"); // fichier
				log.log("Avec les touches : d�calage vers le bas"); // suivant l'emplacement donn� par le fichier config
				this.caseFusionne(); // on affiche les cases fusionn�es
			}else {
				// si impossible de d�caler, on pr�vient le joueur
				logger.log(logger.IMPORTANT, "Impossible de d�caler vers le bas!"); // erreur terminal
				logger.log(logger.ALL, "\t"+"d�calage impossible"); // fichier
			}
			break;
			
		case (KeyEvent.VK_RIGHT): // Droite
			if(jeu.decaler(1)) {
				// on garde la trace du d�calage
				logger.log(logger.ALL, "Avec les touches : d�calage vers la droite"); // fichier				
				log.log("Avec les touches : d�calage vers la droite"); // suivant l'emplacement donn� par le fichier config
				this.caseFusionne(); // on affiche les cases fusionn�es
			}else {
				// si impossible de d�caler, on pr�vient le joueur
				logger.log(logger.IMPORTANT, "Impossible de d�caler vers la droite!"); // erreur terminal
				logger.log(logger.ALL, "\t"+"d�calage impossible"); // fichier			
			}
			break;
			
		case (KeyEvent.VK_LEFT) : // Gauche
			if(jeu.decaler(3)) {
				// on garde la trace du d�calage
				logger.log(logger.ALL, "Avec les touches : d�calage vers la gauche"); // fichier
				log.log("Avec les touches : d�calage vers la gauche"); // suivant l'emplacement donn� par le fichier config
				this.caseFusionne(); // on affiche les cases fusionn�es
			}else {
				// si impossible de d�caler, on pr�vient le joueur
				logger.log(logger.IMPORTANT, "Impossible de d�caler vers la gauche!"); // erreur terminal
				logger.log(logger.ALL, "\t"+"d�calage impossible"); //fichier				
			}			
			break;
		}
		
		// Si le jeu est termin�, on v�rifie si le joueur a gagn�.
		if (jeu.estTermine()) {
			// on enregistre le meilleur score
			sauvegarde.enregistrerMeilleurScore();
			
			if(jeu.estVainquer()) {
				// Si oui, on affiche un message et on recommence automatiquement une partie
				JOptionPane.showMessageDialog(terrainJeu, "Vous avez gagn�!");
				jeu.nouveauJeu();
			
				// on enregistre la trace du jeu 
				logger.log(logger.ALL, "LE JOUEUR A GAGNE!"); // fichier
				log.log("LE JOUEUR A GAGNE ! une nouvelle partie commence"); // suivant l'emplacement donn� par le fichier config
			}else {
				// Si non, on affiche un message et on recommence automatiquement une partie
				JOptionPane.showMessageDialog(terrainJeu, "Vous avez perdu!");
				jeu.nouveauJeu();
				// on enregistre la trace du jeu
				logger.log(logger.IMPORTANT, "Le joueur a perdu! un nouveau jeu est lanc�"); // erreur terminal
				logger.log(logger.ALL, "LE JOUEUR A PERDU"); // fichier
							
			}
			// on �crit les caract�ristiques du nouveau jeu dans le fichier trace
			logger.log(logger.ALL, "Nouveau Jeu");
			logger.log(logger.ALL, "Nb colonnes : "+jeu.getNbCols() + "  Nb lignes : " +jeu.getNbLignes() + "  Score � atteindre : " + jeu.getNbBut());
		}
	}
		
}

