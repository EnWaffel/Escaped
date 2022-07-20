package de.projectp.escaped.resource.image;

import de.projectp.escaped.util.Updatable;

import java.util.ArrayList;
import java.util.List;

public class Animation implements ImageProvider, Updatable {

    private final List<SpriteImage> frames;
    private final double fps;
    private final boolean looped;
    private int curIndex;
    private SpriteImage curImage;
    private boolean running;

    private Animation(List<SpriteImage> frames, double fps, boolean looped) {
        this.frames = frames;
        this.fps = fps;
        this.looped = looped;
    }

    public void play() {
        curIndex = 0;
        curImage = frames.get(0);
        running = true;
    }

    @Override
    public SpriteImage getImage() {
        return curImage;
    }

    @Override
    public void update(double delta, double updates) {
        if (running) {
            if (updates % (1000 / fps) == 0) {
                if (curIndex >= frames.size()) {
                    curIndex = 0;
                    if (!looped) running = false;
                }
                curImage = frames.get(curIndex);
                curIndex++;
            }
        }
    }

    public static Animation fromList(double fps, boolean looped, SpriteImage... frames) {
        return new Animation(List.of(frames), fps, looped);
    }

}
