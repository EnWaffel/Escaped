package de.projectp.escaped.game.render;

import com.jogamp.opengl.util.texture.Texture;
import de.projectp.escaped.util.DecimalPoint;

import java.util.List;

public class TextureGLSequence extends GLSequence {

    private final Texture texture;

    public TextureGLSequence(int gl, List<DecimalPoint> vertexes, Texture texture) {
        super(gl, vertexes);
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

}
