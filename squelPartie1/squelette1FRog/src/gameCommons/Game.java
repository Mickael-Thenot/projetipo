package gameCommons;
import java.awt.Color;
import java.util.Random;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	//public final int width;
	//public final int height;
	public int width;
	public int height;

	public final int minSpeedInTimerLoops;
	public final double defaultDensity;


	// Lien aux objets utilis�s
	private IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;

    // On ajoute un score :
	public int score;

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
			graphic.endGameScreen("You Lose ! Score : " + getScore());
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

		// Si la position de la grenouille est située dans l'interval (0,19) et (25,19), cad sur la ligne horizontale du haut :
		// alors la partie est gagnée.

		if(frog.getPosition().absc >= 0 && frog.getPosition().absc <= width &&
				frog.getPosition().ord == height/2-1){
			graphic.endGameScreen("You Win !");
			return true;
		}

		return false;
	}

	public boolean testPartieInf(){
		// Si la grenouille percute une voiture, alors la partie est finie et on a gagné.
		if(!environment.isSafe(frog.getPosition()) || (frog.getPosition().ord == height-1)){
			graphic.endGameScreen("You Win ! Score : " + getScore());
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

}
