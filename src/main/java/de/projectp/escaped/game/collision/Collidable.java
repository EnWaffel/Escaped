package de.projectp.escaped.game.collision;

import de.projectp.escaped.util.DecimalPoint;
import de.projectp.escaped.util.Face;

import java.awt.geom.Rectangle2D;

public class Collidable {

    private DecimalPoint position = new DecimalPoint();
    private DecimalPoint size = new DecimalPoint();

    public boolean collidingWith(Face face, Collidable target) {
        return checkFace(face, target);
    }

    private boolean checkFace(Face face, Collidable target) {
        boolean result = false;
        Rectangle2D targetRect = target.getAdjustedRectangle();
        switch (face) {
            case LEFT -> targetRect.setRect(targetRect.getX() - 1, targetRect.getY(), targetRect.getWidth(), targetRect.getHeight());
            case RIGHT -> targetRect.setRect(targetRect.getX() + 1, targetRect.getY(), targetRect.getWidth(), targetRect.getHeight());
            case TOP -> targetRect.setRect(targetRect.getX(), targetRect.getY() - 1, targetRect.getWidth(), targetRect.getHeight());
            case BOTTOM -> targetRect.setRect(targetRect.getX(), targetRect.getY() + 1, targetRect.getWidth(), targetRect.getHeight());
        }
        if (!face.equals(Face.ANY)) {
            result = getAdjustedRectangle().intersects(targetRect);
        } else {
            for (int i = 1;i < 4;i++) {
                if (checkFace(Face.values()[i], target)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public DecimalPoint getPosition() {
        return position;
    }

    public DecimalPoint getSize() {
        return size;
    }

    public void setPosition(DecimalPoint position) {
        this.position = position;
    }

    public void setSize(DecimalPoint size) {
        this.size = size;
    }

    public Rectangle2D getAdjustedRectangle() {
        return new Rectangle2D.Double(position.x, position.y, size.x, size.y);
    }

}
