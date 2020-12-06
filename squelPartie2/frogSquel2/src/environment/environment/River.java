package environment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import graphicalElements.Element;
import graphicalElements.FroggerGraphic;
import util.Case;
import gameCommons.Game;

public class River {
    private Game game;
    private int ord;
    private int speed;
    private ArrayList<environment.Log> logs = new ArrayList<>();
    private boolean leftToRight;
    private double density;

    private final Color colorRiver;

    private int timer;

    public ArrayList<River> riverLines;

    // TODO : Constructeur(s)

    public River(Game game, int ord, double density) {
        this.logs = new ArrayList();
        this.game = game;
        this.ord = ord;
        this.speed = game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1;
        this.leftToRight = game.randomGen.nextBoolean();
        this.density = density;

        this.colorRiver = Color.CYAN;

        for(int i = 0; i < 4 * game.width; ++i) {
            this.moveLogs(true);
            this.mayAddLog();
        }
    }

    public River(Game game, int ord) {
        this(game, ord, game.defaultDensity);
    }

    public void update() {

        // TODO

        // Tous les rondins de bois se d�placent d'une case au bout d'un nombre "tic
        // d'horloge" �gal � leur vitesse
        // Notez que cette m�thode est appel�e � chaque tic d'horloge

        // Les rondins de bois doivent etre ajoutes a l interface graphique meme quand
        // elle ne bougent pas

        // A chaque tic d'horloge, un rondin de bois peut �tre ajout�e


        this.timer++;
        if (timer <= this.speed) {
            this.moveLogs(false);
        } else {
            drawRiver();
            this.moveLogs(true);
            this.mayAddLog();
            timer = 0;
        }

    }

    // TODO : ajout de methodes

    private void moveLogs(boolean b) {
        Iterator var3 = this.logs.iterator();

        while(var3.hasNext()) {
            environment.Log log = (environment.Log)var3.next();
            log.move(b);
        }
        this.removeOldLogs();
    }

    private void removeOldLogs() {
        ArrayList<environment.Log> toBeRemoved = new ArrayList();
        Iterator var3 = this.logs.iterator();

        environment.Log c;
        while(var3.hasNext()) {
            c = (environment.Log)var3.next();
            if (!c.appearsInBounds()) {
                toBeRemoved.add(c);
            }
        }

        var3 = toBeRemoved.iterator();

        while(var3.hasNext()) {
            c = (environment.Log)var3.next();
            this.logs.remove(c);
        }

    }

    public boolean isSafe(Case firstCase) {
        Iterator var3 = this.logs.iterator();

        while(var3.hasNext()) {
            environment.Log log = (environment.Log)var3.next();
            if (log.coversCase(firstCase)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Fourni : mayAddLog(), getFirstCase() et getBeforeFirstCase()
     */

    /**
     * Ajoute un rondin de bois au d�but de la voie avec probabilit� �gale � la
     * densit�, si la premi�re case de la voie est vide
     */
    private void mayAddLog() {

        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                logs.add(new environment.Log(game, getBeforeFirstCase(), leftToRight));
            }
        }

    }

    private Case getFirstCase() {
        if (leftToRight) {
            return new Case(0, ord);
        } else
            return new Case(game.width - 1, ord);
    }

    private Case getBeforeFirstCase() {
        if (leftToRight) {
            return new Case(-1, ord);
        } else
            return new Case(game.width, ord);
    }

    public void drawRiver(){
        for(int j = game.height/2; j < game.height; ++j) {
            for(int i = 0; i < game.width; ++i) {
                this.game.getGraphic().add(new Element(i, j, this.colorRiver));
            }
        }
    }

}
