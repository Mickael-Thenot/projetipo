package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;

	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.RED;

	//TODO Constructeur(s)

	public Car(Game game, Case frontPosition, boolean leftToRight) {
		this.game = game;
		this.length = game.randomGen.nextInt(3) + 1;
		this.leftToRight = leftToRight;
		this.leftPosition = new Case(leftToRight ? frontPosition.absc - this.length : frontPosition.absc, frontPosition.ord);
	}

	//TODO : ajout de methodes

	public boolean appearsInBounds() {
		return this.leftPosition.absc + this.length > 0 || this.leftPosition.absc < this.game.width;
	}
	public void move(boolean b) {
		if (b) {
			this.leftPosition = new Case(this.leftPosition.absc + (this.leftToRight ? 1 : -1), this.leftPosition.ord);
		}

		this.addToGraphics();
	}

	public boolean coversCase(Case firstCase) {
		if (firstCase.ord != this.leftPosition.ord) {
			return false;
		} else {
			return firstCase.absc >= this.leftPosition.absc && firstCase.absc < this.leftPosition.absc + this.length;
		}
	}

	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}


	}




}
