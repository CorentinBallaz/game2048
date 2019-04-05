// BALLAZ Corentin
// RAVIX Anne

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Logger {
	// on d�finit les constantes 
	final static int ALL = 0;
	final static int DEBUG = 100;
	final static int INFO = 500;
	final static int IMPORTANT = 900;
	final static int OFF = Integer.MAX_VALUE;
	
	private int level; // attribut indiquant le niveau d'importance
	private PrintWriter printWriter; // attribut indiquant le flux de sortie
		
	public Logger(int level) {
		// constructeur permettant de choisir son level
		this.level=level;
		this.printWriter= new PrintWriter(System.out);
	}
	
	
	public Logger() {
		// constructeur permettant de mettre les param�tres par d�faut
		this.level=Logger.DEBUG;
		this.printWriter= new PrintWriter(System.err);
	}
	
	
	public Logger(String nomFichier) {
		// constructeur permettant de determiner le level et le printWriter gr�ce au fichier de configuration
		// Le fichier de configuration doit se trouver au m�me emplacement que le package courant
		// Si le printWriter souhait� est un fichier, il faut �crire le chemin absolu du fichier !
		
		File fichier = new File(nomFichier); // on cr�e le fichier avec le nom pass� en param�tre
		
		String texte; // variable permettant de lire le fichier pass� en param�tre
		int compteur =0; // variable permettant de r�cuperer une partie du texte
		char c; // variable qui lit le texte charact�re par charact�re
		String levelConfig; // level indiqu� dans le fichier
		String destination; // emplacement indiqu� dans le fichier
		
		try {
			// on lit dans le fichier la ligne contenant le level et le printWriter
			BufferedReader br = new BufferedReader(new FileReader(fichier));
			texte=br.readLine();
			c=texte.charAt(compteur); // on parcourt charact�re par charact�re le texte
			while(c!= ' ') { 
				// on cherche a r�cuperer uniquement le level indiqu� dans le fichier
				// tant qu'on a pas atteint l'espace dans la phrase, on implemente le compteur
				compteur = compteur+1;
				c=texte.charAt(compteur);
			}
			
			levelConfig=texte.substring(0, compteur); // on r�cup�re le level du fichier 
			
			switch (levelConfig) {
			// on initialise l'attribut level en fonction du level r�cup�r� dans le fichier
			case "ALL":
				this.level=ALL;
				break;
				
			case "DEBUG":
				this.level=DEBUG;
				break;
				
			case "INFO":
				this.level=INFO;
				break;
				
			case "IMPORTANT":
				this.level=IMPORTANT;
				break;
			
			default:
				this.level=OFF;
				break;
			}
			
			// on r�cup�re l'emplacement indiqu� par le fichier (1er caract�re apr�s l'espace jusqu'� la fin du texte)
			destination = texte.substring(compteur+1, texte.length());
			
			switch(destination) {
			// on initialise l'attribut printWriter en fonction de la valeur de destination
			case "System.out":
				printWriter=new PrintWriter(System.out);
				break;
			case "System.err":
				printWriter=new PrintWriter(System.err);
				break;
			default:
				// par d�faut, on suppose que c'est un chemin de fichier
				// Attention : il faut �crire le chemin absolu du fichier
				try {
					printWriter = new PrintWriter(new FileWriter (destination+ "\\traceJeu.txt", true));
				}catch(IOException e) {
					System.err.println("Exception traceJeu");
				}
			}
			
			br.close();
			
		}catch(IOException e) {
			System.err.println("Exception Logger()");
			e.printStackTrace();
		}

	}
	
	
	public void log(int nivImportance, String message) {
		// m�thode permettant d'afficher un message en fonction du niveau d'importance
		
		File fichier = new File("traceJeu2048.txt"); // cr�ation du fichier "traceJeu2048"
		this.printWriter(nivImportance); // on initialise printWriter en fonction de nivImportance
		if (nivImportance==ALL){
			this.traceJeu(message, fichier); // on �crit le message dans le fichier "traceJeu2048"
			printWriter.flush(); // vide le tampon et ecrit les donn�es dans le flux
		}else {
			printWriter.println(message); // on ecrit dans le flux
			printWriter.flush(); // on vide le tampon et affiche les donn�es dans le flux
		}
	}
	
	
	public void log(String message) {
		// m�thode permettant d'afficher un message � partir du fichier de configuration
		File fichier = new File("traceJeu.txt"); // cr�ation du fichier "traceJeu"
		if (level==ALL){
			this.traceJeu(message, fichier); // on �crit le message dans le fichier "traceJeu2048"
			printWriter.flush(); // vide le tampon et ecrit les donn�es dans le flux
		}else {
			printWriter.println(message); // on ecrit dans le flux
			printWriter.flush(); // on vide le tampon et affiche les donn�es dans le flux
		}
	}
	
	
	public void printWriter(int priorite) {
		// m�thode permettant d'initialiser l'attribut printWriter en fonction du level
		
		File fichier = new File("traceJeu2048.txt"); // fichier enregistrant la trace du jeu
		
		switch(priorite) {
			// si level = INFO ou level = DEBUG : on �crit dans le terminal
			case DEBUG:
				printWriter=new PrintWriter(System.out);
				break;
			case INFO:
				printWriter=new PrintWriter(System.out);
				break;
				
			// si level = IMPORTANT : on �crit dans le terminal en erreur
			case IMPORTANT:
				printWriter=new PrintWriter(System.err);
				break;
				
			// si level = ALL : on �crit � la suite, dans le fichier (FileWriter)
			case ALL:
				try {
					printWriter = new PrintWriter(new FileWriter (fichier, true));
				}catch(IOException e) {
					System.err.println("Exception traceJeu");
				}
				break;
		}
	}
	
	
	public void traceJeu(String message, File fichier) {
		// m�thode permettant d'ecrire un message dans le fichier 
		printWriter.write(message); // on ecrit le message pass� en param�tre
		printWriter.write("\r\n");
		printWriter.close();		
	}	

}