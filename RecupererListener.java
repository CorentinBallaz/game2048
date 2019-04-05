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
		// on �crit la trace du jeu dans le terminal et dans le fichier
		Logger logger = new Logger(0);
		logger.log(logger.ALL, "Click sur R�cup�rer");
		
		// avec le fichier de configuration
		Logger log= new Logger("config.txt");
		log.log("Click sur R�cup�rer");
		
		// on r�cup�re le fichier contenant la grille de jeu sauvegard�e
		jeu.chargerFichier();
		
		// on recup�re le score de la partie sauvegard�e
		jeu.recupererScore();
	}
}
