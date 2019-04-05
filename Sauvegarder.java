// BALLAZ Corentin
// RAVIX Anne

import java.io.*;
import java.util.*;
import package2048.Jeu2048;
import java.io.IOException;


public class Sauvegarder {
	protected Jeu2048 jeu;
	private File fichier;
	private File meilleurScore;
	private File Score;

	
	public Sauvegarder(Jeu2048 jeu){
		this.jeu = jeu;
		
		// on crée des fichiers pour enregistrer la grille, le meilleur score et le score
		fichier = new File("enregistrerJeu2048.txt");
		meilleurScore = new File("meilleurScore.txt");
		Score = new File("Score.txt");
	}
	
	public void enregistrerGrille() {
		// méthode permettant d'enregistrer la grille de jeu
		
		// on recupère la grille de jeu en String
		String grille[][] = jeu.getGrilleString();
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichier));
			for(int i=0;i<jeu.getNbLignes();i++) {
				// on parcourt les lignes
				for(int j=0;j<jeu.getNbCols();j++) {
					// on parcourt les colonnes
					
					// on ecrit dans le fichier le nombre (en String) dans le fichier
					// un caractère par ligne
					bw.write(grille[i][j]);
					bw.write("\r\n");
				}
			}
			bw.close();
		}catch(IOException e) {
			System.err.println("Exception enregistrerGrille");
		}
	}
		
	
	public void chargerFichier() {
		// permet de récuperer la grille de jeu enregistrée
		
		int[][] grille = jeu.getGrilleInt();
		int j;
		int i=0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichier));
			String ligne; // chaine qui va lire les lignes du fichier 
			ligne=br.readLine(); // lit la ligne du fichier
			
			while (ligne!= null && i<jeu.getNbLignes()) {
				// tant que la ligne n'est pas vide et que i est inférieur au nombre de lignes du jeu
				
				j=0; // on parcourt les colonnes
				while (j<jeu.getNbCols() && i<jeu.getNbLignes()) {
					// on remplit la nouvelle grille de jeu par les nombres enregistrés
					grille[i][j] = Integer.parseInt(ligne);
					ligne = br.readLine();
					j=j+1;
				}
				i=i+1;
			}
			jeu.setGrilleInt(grille); // on remplace la grille de jeu par la grille qu'on vient de créer
			br.close();
		}
		catch (IOException e) {
			System.err.println("Exception FileNotFound jeu2048");
			e.printStackTrace();
			} 
	}


	public void enregistrerMeilleurScore() {
		// méthode permettant d'enregistrer le meilleur score du joueur
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(meilleurScore));
			bw.write(Integer.toString(jeu.getBestScore())); // on ecrit le meilleur score dans le fichier
			bw.close();
		}catch(IOException e) {
			System.err.println("Exception meilleurScore");
		}
	}
	
	public void recupererMeilleurScore() {
		// méthode permettant de récuperer le meilleur score
		try {
			BufferedReader br = new BufferedReader(new FileReader(meilleurScore));
			String ligne;
			ligne=br.readLine(); // on lit la ligne du fichier
			jeu.setBestScore(Integer.parseInt(ligne)); // on initialise le meilleur score avec le score lu dans le fichier
			br.close();
		
		} catch (IOException e) {
			System.err.println("Exception FileNotFound jeu2048");
			e.printStackTrace();
		} 
	}
	
	
	public void enregistrerScore() {
		// methode permettant d'enregistrer le score du joueur
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(Score));
			bw.write(Integer.toString(jeu.getScore())); // on écrit le score dans un fichier
			bw.close();
		}catch(IOException e) {
			System.err.println("Exception Score");
		}
	}
	
	public void recupererScore() {
		// méthide permettant de récuperer le score du joueur
		try {
			BufferedReader br = new BufferedReader(new FileReader(Score));
			String ligne;
			ligne=br.readLine(); // on lit la ligne du fichier
			jeu.setScore(Integer.parseInt(ligne)); // on initialise le score du joueur avec le score lu dans le fichier
			br.close();
		
		} catch (IOException e) {
			System.err.println("Exception FileNotFound jeu2048");
			e.printStackTrace();
		} 
	}
}
