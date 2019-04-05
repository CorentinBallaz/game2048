// BALLAZ Corentin
// RAVIX Anne

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.io.File;

import package2048.Jeu2048;

public class Frame2048 extends Frame{
	public static final int LARGEUR = 500;
	public static final int HAUTEUR = 500;
	private Jeu2048 jeu;
	
	public Frame2048(Jeu2048 jeu)  {
		this.jeu = jeu;
		Sauvegarder sauvegarde = new Sauvegarder(jeu);
		
		// Si un meilleur score a déjà été enregistré, on le récupère au début de la partie
		File meilleurScore = new File("meilleurScore.txt");
		if (meilleurScore.exists()) {
			sauvegarde.recupererMeilleurScore();
		}

		// Si il y a déjà un fichier contenant la trace du jeu, on l'efface pour enregistrer uniquement la trace du jeu actuelle
		File traceJeu2048 = new File("traceJeu2048.txt");
		if(traceJeu2048.exists()) {
			traceJeu2048.delete();
		}
		
		File traceJeu = new File("traceJeu.txt");
		if(traceJeu.exists()) {
			traceJeu.delete();
		}
		
		// on crée un logger qui permettra de garder une trace du jeu
		Logger logger = new Logger("config.txt");
		logger.log(logger.ALL, "Nouveau Jeu");
		logger.log(logger.ALL, "Nb colonnes : "+jeu.getNbCols() + "  Nb lignes : " +jeu.getNbLignes() + "  Score à atteindre : " + jeu.getNbBut());
		
		// On crée la fenêtre
		this.setSize(LARGEUR,HAUTEUR);
		this.setTitle("Jouez au 2048!");
		this.setLayout(new BorderLayout());
		
		// On ajoute le terrain de Jeu
		CanvasJeu terrainJeu = new CanvasJeu(jeu);
		this.add(terrainJeu,BorderLayout.CENTER);
		
		// On ajoute les écouteurs : on peut jouer soit avec les touches, soit avec la souris
		terrainJeu.addMouseListener(new SourisListener(jeu,terrainJeu,sauvegarde));
		terrainJeu.addKeyListener(new ToucheListener(jeu,terrainJeu,sauvegarde));
		
		Button b1 = new Button("Sauvegarder");
		Button b2 = new Button("Recuperer");
		
		// Panel contenant les boutons sauvegarder et récupérer
		Panel p = new Panel(new FlowLayout());
		p.add(b1);
		p.add(b2);
		this.add(p,BorderLayout.SOUTH);
		b1.addActionListener(new SauvegarderListener(sauvegarde));
		b2.addActionListener(new RecupererListener(sauvegarde));
		
		
		// Pour pouvoir fermer la fenêtre
		this.addWindowListener(new FermerFenetre(this, sauvegarde));	
		
		this.setVisible(true);
		}
	
}
