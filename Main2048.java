import package2048.Jeu2048;

// BALLAZ Corentin
// RAVIX Anne


public class Main2048 {
	public static void main(String[] args) {
		Jeu2048 jeu = new Jeu2048(); // cr�ation du jeu
		jeu.nouveauJeu(); // initialisation du jeu 
		new Frame2048(jeu); // cr�ation de la fen�tre
	}
}
