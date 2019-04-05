// BALLAZ Corentin
// RAVIX Anne

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Logger {
	// on définit les constantes 
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
		// constructeur permettant de mettre les paramètres par défaut
		this.level=Logger.DEBUG;
		this.printWriter= new PrintWriter(System.err);
	}
	
	
	public Logger(String nomFichier) {
		// constructeur permettant de determiner le level et le printWriter grâce au fichier de configuration
		// Le fichier de configuration doit se trouver au même emplacement que le package courant
		// Si le printWriter souhaité est un fichier, il faut écrire le chemin absolu du fichier !
		
		File fichier = new File(nomFichier); // on crée le fichier avec le nom passé en paramètre
		
		String texte; // variable permettant de lire le fichier passé en paramètre
		int compteur =0; // variable permettant de récuperer une partie du texte
		char c; // variable qui lit le texte charactère par charactère
		String levelConfig; // level indiqué dans le fichier
		String destination; // emplacement indiqué dans le fichier
		
		try {
			// on lit dans le fichier la ligne contenant le level et le printWriter
			BufferedReader br = new BufferedReader(new FileReader(fichier));
			texte=br.readLine();
			c=texte.charAt(compteur); // on parcourt charactère par charactère le texte
			while(c!= ' ') { 
				// on cherche a récuperer uniquement le level indiqué dans le fichier
				// tant qu'on a pas atteint l'espace dans la phrase, on implemente le compteur
				compteur = compteur+1;
				c=texte.charAt(compteur);
			}
			
			levelConfig=texte.substring(0, compteur); // on récupère le level du fichier 
			
			switch (levelConfig) {
			// on initialise l'attribut level en fonction du level récupéré dans le fichier
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
			
			// on récupère l'emplacement indiqué par le fichier (1er caractère après l'espace jusqu'à la fin du texte)
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
				// par défaut, on suppose que c'est un chemin de fichier
				// Attention : il faut écrire le chemin absolu du fichier
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
		// méthode permettant d'afficher un message en fonction du niveau d'importance
		
		File fichier = new File("traceJeu2048.txt"); // création du fichier "traceJeu2048"
		this.printWriter(nivImportance); // on initialise printWriter en fonction de nivImportance
		if (nivImportance==ALL){
			this.traceJeu(message, fichier); // on écrit le message dans le fichier "traceJeu2048"
			printWriter.flush(); // vide le tampon et ecrit les données dans le flux
		}else {
			printWriter.println(message); // on ecrit dans le flux
			printWriter.flush(); // on vide le tampon et affiche les données dans le flux
		}
	}
	
	
	public void log(String message) {
		// méthode permettant d'afficher un message à partir du fichier de configuration
		File fichier = new File("traceJeu.txt"); // création du fichier "traceJeu"
		if (level==ALL){
			this.traceJeu(message, fichier); // on écrit le message dans le fichier "traceJeu2048"
			printWriter.flush(); // vide le tampon et ecrit les données dans le flux
		}else {
			printWriter.println(message); // on ecrit dans le flux
			printWriter.flush(); // on vide le tampon et affiche les données dans le flux
		}
	}
	
	
	public void printWriter(int priorite) {
		// méthode permettant d'initialiser l'attribut printWriter en fonction du level
		
		File fichier = new File("traceJeu2048.txt"); // fichier enregistrant la trace du jeu
		
		switch(priorite) {
			// si level = INFO ou level = DEBUG : on écrit dans le terminal
			case DEBUG:
				printWriter=new PrintWriter(System.out);
				break;
			case INFO:
				printWriter=new PrintWriter(System.out);
				break;
				
			// si level = IMPORTANT : on écrit dans le terminal en erreur
			case IMPORTANT:
				printWriter=new PrintWriter(System.err);
				break;
				
			// si level = ALL : on écrit à la suite, dans le fichier (FileWriter)
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
		// méthode permettant d'ecrire un message dans le fichier 
		printWriter.write(message); // on ecrit le message passé en paramètre
		printWriter.write("\r\n");
		printWriter.close();		
	}	

}