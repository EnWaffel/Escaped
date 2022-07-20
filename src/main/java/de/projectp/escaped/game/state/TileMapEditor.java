package de.projectp.escaped.game.state;

import de.projectp.escaped.game.render.GLButton;
import de.projectp.escaped.game.render.GLManager;
import de.projectp.escaped.game.render.RenderAdapter;
import de.projectp.escaped.util.DecimalPoint;

public class TileMapEditor extends RenderAdapter implements State {

    @Override
    public void render() {

    }

    @Override
    public void enable() {
        GLManager.attach(this);
        GLButton button = new GLButton(new DecimalPoint(1229, 100), new DecimalPoint(50, 500));
        GLManager.attach(button);
    }

    @Override
    public void update(double delta, double totalUpdates) {

    }

    @Override
    public void disable() {
        GLManager.detach(this);
    }

}
