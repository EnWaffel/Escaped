package de.projectp.escaped.game.render;

import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import de.projectp.escaped.resource.ResourceManager;
import de.projectp.escaped.util.DecimalPoint;

import java.util.ArrayList;
import java.util.List;

public class GLButton extends RenderAdapter implements MouseListener {

    private static List<GLButton> buttons = new ArrayList<>();


    private DecimalPoint position;
    private DecimalPoint size;
    private int state = 0;

    public GLButton(DecimalPoint position, DecimalPoint size) {
        this.position = position;
        this.size = size;
        buttons.add(this);
        GLManager.addListener("m", this);
    }

    @Override
    public void render() {
        switch (state) {
            case 0 -> {
                img(ResourceManager.image.get("test"), position.x, position.y, size.x, size.y);
            }
        }
    }

    public void disable() {
        GLManager.removeListener("m", this);
    }

    public DecimalPoint getPosition() {
        return position;
    }

    public DecimalPoint getSize() {
        return size;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        position = new DecimalPoint(mouseEvent.getX(), mouseEvent.getY());
    }

    @Override
    public void mouseWheelMoved(MouseEvent mouseEvent) {

    }

}
