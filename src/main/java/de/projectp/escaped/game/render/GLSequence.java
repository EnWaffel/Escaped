package de.projectp.escaped.game.render;

import de.projectp.escaped.util.DecimalPoint;

import java.util.List;

public class GLSequence {

    private final int gl;
    private final List<DecimalPoint> vertexes;

    public GLSequence(int gl, List<DecimalPoint> vertexes) {
        this.gl = gl;
        this.vertexes = vertexes;
    }

    public int getGL() {
        return gl;
    }

    public List<DecimalPoint> getVertexes() {
        return vertexes;
    }

}
