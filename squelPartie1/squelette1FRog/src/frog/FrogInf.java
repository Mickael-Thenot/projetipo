package frog;

import gameCommons.Game;
import gameCommons.IFrog;

import graphicalElements.Element;
import util.Case;
import util.Direction;

import java.awt.*;

public class FrogInf implements IFrog{
    private Game game;
    private Direction dir;
    public Case pos;

    public FrogInf (Game game){
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

        /*if(key == Direction.up && this.pos.ord <= 1){
            this.pos = new Case(this.pos.absc, this.pos.ord+1);
            this.game.score++;
            System.out.println("Position de la grenouille par rapport à l'écran : " + getPosition().absc + ", " + getPosition().ord);
            System.out.println("Score : " + game.getScore());
        }*/

        if(key == Direction.up){
            this.pos = new Case(this.pos.absc, this.pos.ord+1);
            this.game.score++;
            System.out.println("Position de la grenouille par rapport à l'écran : " + getPosition().absc + ", " + getPosition().ord);
            System.out.println("Score : " + game.getScore());
        }

        /*if(key == Direction.down && this.pos.ord <= 1){
            this.pos = new Case(this.pos.absc, this.pos.ord-1);
            this.game.score--;
            System.out.println("Position de la grenouille par rapport à l'écran : " + getPosition().absc + ", " + getPosition().ord);
            System.out.println("Score : " + game.getScore());
        }*/

        if(key == Direction.down){
            this.pos = new Case(this.pos.absc, this.pos.ord-1);
            this.game.score--;
            System.out.println("Position de la grenouille par rapport à l'écran : " + getPosition().absc + ", " + getPosition().ord);
            System.out.println("Score : " + game.getScore());
        }

        if(key == Direction.right){
            this.pos = new Case(this.pos.absc+1, this.pos.ord);
            System.out.println("Position de la grenouille par rapport à l'écran : " + getPosition().absc + ", " + getPosition().ord);
            System.out.println("Score : " + game.getScore());
        }
        if(key == Direction.left){
            this.pos = new Case(this.pos.absc-1, this.pos.ord);
            System.out.println("Position de la grenouille par rapport à l'écran : " + getPosition().absc + ", " + getPosition().ord);
            System.out.println("Score : " + game.getScore());
        }
    }





}
