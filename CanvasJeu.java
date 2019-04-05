// BALLAZ Corentin
// RAVIX Anne 


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;


import package2048.Jeu2048;

public class CanvasJeu extends Canvas implements Observer {
	public Jeu2048 jeu;
	
	public CanvasJeu(Jeu2048 jeu) {
		this.jeu = jeu;	
		jeu.addObserver(this);
	}
	
	public void paint (Graphics g) {
		
		// On affiche l'objectif du jeu :
		g.setColor(new Color(240,193,45));
		g.fillRect(this.getWidth()/20, this.getHeight()/20, 7*this.getWidth()/32, 33*this.getHeight()/200);
		g.setColor(Color.WHITE);
		
		// permet de modifier le style d'écriture
		Font f = new Font("Courier", Font.BOLD, 15);
		g.setFont(f);
		g.drawString("Objectif :",this.getWidth()/20+5, 50);
		
		f = new Font("Courier", Font.BOLD, 30);
		g.setFont(f);
		g.drawString("2048",this.getWidth()/20+7*this.getWidth()/64-40, this.getHeight()/20+33*this.getHeight()/400+20);
		
		
		// On affiche le score du joueur
		f = new Font("Courier", Font.BOLD, 15);
		g.setFont(f);
		
		g.setColor(new Color(204,190,185));
		g.fillRect(this.getWidth()/20+2*(7*this.getWidth()/32+this.getWidth()/120), this.getHeight()/20, 7*this.getWidth()/32, 33*this.getHeight()/200);
		g.setColor(Color.WHITE);
		g.drawString("Score : ", this.getWidth()/20+2*(7*this.getWidth()/32+this.getWidth()/120)+5, 50);
		g.drawString("  "+jeu.getScore(), this.getWidth()/20+2*(7*this.getWidth()/32+this.getWidth()/120)+7*this.getWidth()/64-5, this.getHeight()/20+33*this.getHeight()/400+20);		
		

		// On affiche le meilleur score
		g.setColor(new Color(204,190,185));
		g.fillRect(this.getWidth()/20+3*(7*this.getWidth()/32+this.getWidth()/120), this.getHeight()/20, 7*this.getWidth()/32, 33*this.getHeight()/200);
		g.setColor(Color.WHITE);
		g.drawString("Meilleur : ", this.getWidth()/20+3*(7*this.getWidth()/32+this.getWidth()/120)+5,50);
		g.drawString("	"+jeu.getBestScore(), this.getWidth()/20+3*(7*this.getWidth()/32+this.getWidth()/120)+7*this.getWidth()/64-5, this.getHeight()/20+33*this.getHeight()/400+20);
		
		
		// Terrain de jeu
		f = new Font("Courier", Font.BOLD, 30);
		g.setFont(f);
		
		// on dessine un rectangle noir : contour du jeu
		g.setColor(Color.BLACK);
		g.drawRect(this.getWidth()/20-2, this.getHeight()/4-2, 28*this.getWidth()/32+this.getWidth()/40+2, 132*this.getHeight()/200+this.getHeight()/40+2);
		
		// on initialise la grille de jeu 
		int[][] grilleJeu = jeu.getGrilleInt(); // tableau d'entiers
		String[][] grilleJeuString = jeu.getGrilleString(); // tableau de string
		
		// on parcours le tableau d'entiers correspondant à la grille de jeu
		for (int i=0;i<grilleJeu.length;i++) {
			for(int j=0;j<grilleJeu.length;j++) {
				// suivant la valeur de la grille, on dessine un rectangle coloré
				switch (grilleJeu[i][j]) {
				//on répète cette action pour chaque case différente, selon la valeur qui lui est attribuée
				case 0 : 
					g.setColor(new Color(204,190,185)); //on établie la couleur définie pour chaque type de case
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					//on dessine un rectangle à l'endroit correspondant à la valeur de la grille
					break;
				case 2 : 
					g.setColor(new Color(238,228,218));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 4 : 
					g.setColor(new Color(235,220,200));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 8 :
					g.setColor(new Color(245,170,120));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 16 :
					g.setColor(new Color(245,150,100));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 32 :
					g.setColor(new Color(246,125,95));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 64 :
					g.setColor(new Color(246,95,60));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 128 :
					g.setColor(new Color(240,200,115));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 256 :
					g.setColor(new Color(240,195,100));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 512 :
					g.setColor(new Color(240,200,80));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 1024 :
					g.setColor(new Color(240,195,60));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				case 2048 :
					g.setColor(new Color(240,193,45));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				default :
					//par défault on dessine une autre case au cas où on a quelque chose d'autre
					g.setColor(new Color(185,170,160));
					g.fillRect(this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120), i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4, 7*this.getWidth()/32, 33*this.getHeight()/200);
					break;
				}
				
				// on écrit le chiffre dans la case correspondante
				g.setColor(Color.BLACK);
				if (grilleJeu[i][j] != 0){
					// suivant la taille du string, on adapte sa position pour que le chiffre soit toujours au milieu de la case quelque soit la taille de la fenêtre
					
					if (grilleJeuString[i][j].length()==1) {
						g.drawString(grilleJeuString[i][j],this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120)+7*this.getWidth()/64-5, i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4+33*this.getHeight()/400+10);
					}
					if (grilleJeuString[i][j].length()==2) {
						g.drawString(grilleJeuString[i][j],this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120)+7*this.getWidth()/64-15, i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4+33*this.getHeight()/400+10);
					}
					if (grilleJeuString[i][j].length()==3) {
						g.drawString(grilleJeuString[i][j],this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120)+7*this.getWidth()/64-25, i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4+33*this.getHeight()/400+10);
						}
					if (grilleJeuString[i][j].length()==4) {
						g.drawString(grilleJeuString[i][j],this.getWidth()/20+j*(7*this.getWidth()/32+this.getWidth()/120)+7*this.getWidth()/64-40, i*(33*this.getHeight()/200+this.getHeight()/120)+this.getHeight()/4+33*this.getHeight()/400+10);
					}
				}
			}

		}
		
	}
	@Override
	public void update(Observable jeu, Object arg0) {
		this.repaint(); 
	}
}