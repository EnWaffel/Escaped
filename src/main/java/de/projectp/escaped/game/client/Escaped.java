package de.projectp.escaped.game.client;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import de.projectp.escaped.game.client.render.Renderer;

import java.awt.*;

public class Escaped {

    private Frame window;

    public Escaped() {
    }

    public void run() {
        load();
    }

    private void load() {
        window = new Frame("Test");
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        GLCapabilities capabilities = new GLCapabilities(GLProfile.getDefault());
        GLCanvas canvas = new GLCanvas(capabilities);
        canvas.setRealized(true);
        canvas.setSize(1280, 720);
        canvas.addGLEventListener(new Renderer());
        window.add(canvas);
        window.setVisible(true);
    }

}
