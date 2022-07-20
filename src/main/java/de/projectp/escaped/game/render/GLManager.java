package de.projectp.escaped.game.render;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.event.*;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
import de.projectp.escaped.game.state.State;
import de.projectp.escaped.util.Updatable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

public class GLManager {

    public static GLProfile profile = GLProfile.get(GLProfile.GL2);
    public static boolean running;
    private static Thread updateThread;
    public static double updates = 0;
    private static double updates_per_second = 1000;
    private static Timer deltaTimer;
    private static double lastDelta;

    private static GLWindow window;
    private static GLCapabilities windowCapabilities;
    private static FPSAnimator animator;
    private static Renderer renderer;

    private static List<Updatable> updateHooks = new ArrayList<>();

    private static State state;

    public static void init() {
        renderer = new Renderer();
        windowCapabilities = new GLCapabilities(profile);
        window = GLWindow.create(windowCapabilities);
        animator = new FPSAnimator(window, 60, false);

        window.setTitle("Escaped");
        window.setSize(1280, 720);
        window.setPosition((Toolkit.getDefaultToolkit().getScreenSize().width - window.getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        window.setUpdateFPSFrames(100, System.out);
        window.setVisible(true);

        window.addGLEventListener(renderer);
        animator.start();

        window.addWindowListener(new WindowListener() {
            @Override
            public void windowResized(WindowEvent windowEvent) {

            }

            @Override
            public void windowMoved(WindowEvent windowEvent) {

            }

            @Override
            public void windowDestroyNotify(WindowEvent windowEvent) {

            }

            @Override
            public void windowDestroyed(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowGainedFocus(WindowEvent windowEvent) {

            }

            @Override
            public void windowLostFocus(WindowEvent windowEvent) {

            }

            @Override
            public void windowRepaint(WindowUpdateEvent windowUpdateEvent) {

            }
        });
        running = true;
        updateThread = new Thread(GLManager::run);

    }

    public static void attach(RenderAdapter adapter) {
        renderer.attach(adapter);
    }

    public static void detach(RenderAdapter adapter) {
        renderer.detach(adapter);
    }

    public static void addUpdateHook(Updatable hook) {
        updateHooks.add(hook);
    }

    public static void removeUpdateHook(Updatable hook) {
        updateHooks.remove(hook);
    }

    public static void switchState(State state) {
        if (GLManager.state != null) GLManager.state.disable();
        GLManager.state = state;
        state.enable();
    }

    public static void addListener(String type, NEWTEventListener listener) {
        switch (type) {
            case "w" -> window.addWindowListener((WindowListener) listener);
            case "k" -> window.addKeyListener((KeyListener) listener);
            case "m" -> window.addMouseListener((MouseListener) listener);
        }
    }

    public static void removeListener(String type, NEWTEventListener listener) {
        switch (type) {
            case "w" -> window.removeWindowListener((WindowListener) listener);
            case "k" -> window.removeKeyListener((KeyListener) listener);
            case "m" -> window.removeMouseListener((MouseListener) listener);
        }
    }

    private static void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns;
        double delta = 0;
        int frames = 0;

        while (running) {
            ns = 1000000000.0 / 1000;
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                delta--;
                if (deltaTimer != null) deltaTimer.cancel();
                update(lastDelta);
                updates++;
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                updates = frames;
                frames = 0;
            }
        }
    }

    private static void update(double delta) {
        lastDelta = 0;
        deltaTimer = new Timer();
        deltaTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                lastDelta++;
            }
        }, 0, 1);

        for (Updatable hook : updateHooks) {
            hook.update(delta, updates);
        }
        if (state != null) state.update(delta, updates);

    }

}
