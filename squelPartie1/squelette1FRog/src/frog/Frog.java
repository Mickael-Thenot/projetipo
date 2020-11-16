package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Direction dir;
	private Case pos;

	public Frog (Game game){
		this.game = game;
		this.dir = Direction.up;
		this.pos = new Case(game.width/2, 0);
	}

	@Override
	public Case getPosition() {
		return this.pos;
	}

	@Override
	public Direction getDirection() {
		return this.dir;
	}

	@Override
	public void move(Direction key) {
		if(key == Direction.up){
			this.pos = new Case(this.pos.absc, this.pos.ord+1);
		}
		if(key == Direction.down){
			this.pos = new Case(this.pos.absc, this.pos.ord-1);
		}
		if(key == Direction.right){
			this.pos = new Case(this.pos.absc+1, this.pos.ord);
		}
		if(key == Direction.left){
			this.pos = new Case(this.pos.absc-1, this.pos.ord);
		}
	}
}
