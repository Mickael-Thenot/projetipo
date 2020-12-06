package gameCommons;
import java.awt.*;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;
import util.Case;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;


	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;
	private int pos;

    // On ajoute un score :
	public int score;

	private CompteARebours compteARebours;
	private Chrono chrono;

	/**
	 * @param graphic             l'interface graphique
	 * @param width               largeur en cases
	 * @param height              hauteur en cases
	 * @param minSpeedInTimerLoop Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity      densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.score = 0;

		compteARebours = new CompteARebours();
		chrono = new Chrono();

	}

	/**
	 * Lie l'objet frog � la partie
	 *
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 *
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 *
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
		// TODO

		// Si à cette case la grenouille n'est pas en sécurité, cad qu'il percute une voiture,
		// alors la partie est perdue.

		if(!environment.isSafe(frog.getPosition())){
			graphic.endGameScreen("You Lose ! ");
			return true;
		}

		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 *
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		// TODO

		// Si la position de la grenouille est située à la ligne horizontale du haut, cad height-1:
		// alors la partie est gagnée.

		if(frog.getPosition().absc >= 0 && frog.getPosition().absc <= width &&
				frog.getPosition().ord == height-1){
			graphic.endGameScreen("You Win !");
			return true;
		}

		return false;
	}

	public boolean testPartieInf(){
		// Si la grenouille percute une voiture et si le temps est écoulé, alors la partie est finie et on a gagné.
		if(!environment.isSafe(frog.getPosition()) || this.compteARebours.getCompteurTemps() == 0 ){
			graphic.endGameScreen("You Win ! Score : " + getScore() + "    " + this.chrono.getStr());
			return true;
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		//testLose();
		//testWin();

		// Ce que j'ai rajouté :
		testPartieInf();
	}

	public int getScore(){
		return score;
	}

	// Ici on ajoute un bruitage de voitures qui passent.
	public void playSoundCars(){
		//if (frog.getPosition().ord >= 0 && frog.getPosition().ord <= height/2-1){
			Audio.playSound("/audio/voituresPassages.wav");
		//}
	}

	// Ici on ajoute un bruitage de rivière qui coule.
	public void playSoundRiver(){
		//if (frog.getPosition().ord >= height-1){
			//Audio.playSound("/audio/sonRuisseau.wav");
		//}
	}

}
