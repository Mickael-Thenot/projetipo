package environment;

import java.util.ArrayList;
import java.util.Iterator;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class EnvInf implements IEnvironment {

    public ArrayList<Lane> roadLines;
    public ArrayList<River> riverLines;
    private Game game;

    public EnvInf(Game game) {

        this.game = game;

        this.roadLines = new ArrayList<>();
        this.roadLines.add(new Lane(game, 0, 0.0D));
        this.roadLines.add(new Lane(game, 1, 0.0D));
        for(int i = 2; i < game.height/2-1; ++i) {
            this.roadLines.add(new Lane(game, i));
        }
        this.roadLines.add(new Lane(game, game.height/2, 0.0D));

        this.riverLines = new ArrayList();

        for(int i = game.height/2; i < 2*game.height; ++i) {
            this.riverLines.add(new River(game, i));
        }
        this.riverLines.add(new River(game, 2*game.height, 0.0D));

    }

    @Override
    public boolean isSafe(Case c) {
        // Si la case est sure, alors la grenouille peut s'y poser sans mourir.
        return ((Lane)this.roadLines.get(c.ord)).isSafe(c) && ((River)this.riverLines.get(c.ord)).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return c.ord == this.game.height - 1;
    }

    @Override
    public void update() {
        Iterator var2 = this.roadLines.iterator();
        Iterator var3 = this.riverLines.iterator();

        while(var2.hasNext()) {
            Lane lane = (Lane)var2.next();
            lane.update();
            River river = (River) var3.next();
            river.update();
        }
    }

}
