import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecupererListener implements ActionListener {
	private Sauvegarder jeu;
	
	public RecupererListener(Sauvegarder jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// on écrit la trace du jeu dans le terminal et dans le fichier
		Logger logger = new Logger(0);
		logger.log(logger.ALL, "Click sur Récupérer");
		
		// avec le fichier de configuration
		Logger log= new Logger("config.txt");
		log.log("Click sur Récupérer");
		
		// on récupère le fichier contenant la grille de jeu sauvegardée
		jeu.chargerFichier();
		
		// on recupère le score de la partie sauvegardée
		jeu.recupererScore();
	}
}
