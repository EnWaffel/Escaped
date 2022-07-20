package de.projectp.escaped.game;

import de.projectp.escaped.G;
import de.projectp.escaped.game.render.GLManager;
import de.projectp.escaped.game.state.TileMapEditor;

public class Escaped {

    public Escaped() {
        G.g = this;
    }

    public void run() {
        load();
    }

    private void load() {
        GLManager.init();
        GLManager.switchState(new TileMapEditor());
        /*

        final double[] x = {0};
        final double[] y = {0};

        renderer.adapt(new RenderAdapter() {
            @Override
            public void render() {
                //rect2d(x[0], y[0], 300, 300);
                //rect2d(0.5, 0.5, 0.1, 0.1);
                img(GLUtil.missingTexture(300, 300), x[0], y[0], 500, 500);
            }
        });
        window.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == 'w') {
                    y[0] += 10;
                }
                if (keyEvent.getKeyChar() == 's') {
                    y[0] -= 10;
                }
                if (keyEvent.getKeyChar() == 'a') {
                    x[0] -= 10;
                }
                if (keyEvent.getKeyChar() == 'd') {
                    x[0] += 10;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
         */
    }

}
