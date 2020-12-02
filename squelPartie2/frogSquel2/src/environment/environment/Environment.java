package environment;

import java.util.ArrayList;
import java.util.Iterator;

//import givenEnvironment.Lane; // Dès que j'enlève cette ligne, les voitures ne s'affiche plus.
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {

    //TODO

    private ArrayList<Lane> roadLines;
    private Game game;

    public Environment(Game game) {
        this.game = game;
        this.roadLines = new ArrayList();
        this.roadLines.add(new Lane(game, 0, 0.0D));

        for(int i = 1; i < game.height - 1; ++i) {
            this.roadLines.add(new Lane(game, i));
        }

        this.roadLines.add(new Lane(game, game.height - 1, 0.0D));
    }


    @Override
    public boolean isSafe(Case c) {
        return ((Lane)this.roadLines.get(c.ord)).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return c.ord == this.game.height - 1;
    }

    @Override
    public void update() {
        Iterator var2 = this.roadLines.iterator();

        while(var2.hasNext()) {
            Lane lane = (Lane)var2.next();
            lane.update();
        }
    }
}
