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
		// méthode permettant d'informer le joueur sur les cases qui viennent de fusionner
		Logger logger = new Logger(0);
				
		String grilleJeu[][] = jeu.getGrilleString(); // on récupère la grille de jeu sous forme de String
		boolean grilleFusion[][] = jeu.tableauFusions(); // on récupère le tableau indiquant les fusions
		String message;
		
		for(int i=0; i<jeu.getNbLignes();i++) {
			// on parcourt les lignes
			for(int j=0;j<jeu.getNbCols();j++) {
				// on parcourt les colonnes
				if(grilleFusion[i][j]==true) {
					// s'il y a une fusion (case(i,j)=true), alors on prévient le joueur
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
		
		// On décale le jeu en fonction de la touche
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_UP) : // Haut
			if(jeu.decaler(0)) {
				// on garde la trace du décalage
				logger.log(logger.ALL, "Avec les touches : décalage vers le haut"); // fichier
				log.log("Avec les touches : décalage vers le haut"); // suivant l'emplacement donné par le fichier config
				this.caseFusionne(); // on affiche les cases fusionnées
			}else {
				// si impossible de décaler, on prévient le joueur
				logger.log(logger.IMPORTANT, "Impossible de décaler vers le haut!"); // erreur terminal
				logger.log(logger.ALL, "\t"+"décalage impossible"); // fichier
			}
			break;
			
		case (KeyEvent.VK_DOWN): // Bas
			if(jeu.decaler(2)) {
				// on garde la trace du décalage
				logger.log(logger.ALL, "Avec les touches : décalage vers le bas"); // fichier
				log.log("Avec les touches : décalage vers le bas"); // suivant l'emplacement donné par le fichier config
				this.caseFusionne(); // on affiche les cases fusionnées
			}else {
				// si impossible de décaler, on prévient le joueur
				logger.log(logger.IMPORTANT, "Impossible de décaler vers le bas!"); // erreur terminal
				logger.log(logger.ALL, "\t"+"décalage impossible"); // fichier
			}
			break;
			
		case (KeyEvent.VK_RIGHT): // Droite
			if(jeu.decaler(1)) {
				// on garde la trace du décalage
				logger.log(logger.ALL, "Avec les touches : décalage vers la droite"); // fichier				
				log.log("Avec les touches : décalage vers la droite"); // suivant l'emplacement donné par le fichier config
				this.caseFusionne(); // on affiche les cases fusionnées
			}else {
				// si impossible de décaler, on prévient le joueur
				logger.log(logger.IMPORTANT, "Impossible de décaler vers la droite!"); // erreur terminal
				logger.log(logger.ALL, "\t"+"décalage impossible"); // fichier			
			}
			break;
			
		case (KeyEvent.VK_LEFT) : // Gauche
			if(jeu.decaler(3)) {
				// on garde la trace du décalage
				logger.log(logger.ALL, "Avec les touches : décalage vers la gauche"); // fichier
				log.log("Avec les touches : décalage vers la gauche"); // suivant l'emplacement donné par le fichier config
				this.caseFusionne(); // on affiche les cases fusionnées
			}else {
				// si impossible de décaler, on prévient le joueur
				logger.log(logger.IMPORTANT, "Impossible de décaler vers la gauche!"); // erreur terminal
				logger.log(logger.ALL, "\t"+"décalage impossible"); //fichier				
			}			
			break;
		}
		
		// Si le jeu est terminé, on vérifie si le joueur a gagné.
		if (jeu.estTermine()) {
			// on enregistre le meilleur score
			sauvegarde.enregistrerMeilleurScore();
			
			if(jeu.estVainquer()) {
				// Si oui, on affiche un message et on recommence automatiquement une partie
				JOptionPane.showMessageDialog(terrainJeu, "Vous avez gagné!");
				jeu.nouveauJeu();
			
				// on enregistre la trace du jeu 
				logger.log(logger.ALL, "LE JOUEUR A GAGNE!"); // fichier
				log.log("LE JOUEUR A GAGNE ! une nouvelle partie commence"); // suivant l'emplacement donné par le fichier config
			}else {
				// Si non, on affiche un message et on recommence automatiquement une partie
				JOptionPane.showMessageDialog(terrainJeu, "Vous avez perdu!");
				jeu.nouveauJeu();
				// on enregistre la trace du jeu
				logger.log(logger.IMPORTANT, "Le joueur a perdu! un nouveau jeu est lancé"); // erreur terminal
				logger.log(logger.ALL, "LE JOUEUR A PERDU"); // fichier
							
			}
			// on écrit les caractéristiques du nouveau jeu dans le fichier trace
			logger.log(logger.ALL, "Nouveau Jeu");
			logger.log(logger.ALL, "Nb colonnes : "+jeu.getNbCols() + "  Nb lignes : " +jeu.getNbLignes() + "  Score à atteindre : " + jeu.getNbBut());
		}
	}
		
}

