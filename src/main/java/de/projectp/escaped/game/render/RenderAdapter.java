package de.projectp.escaped.game.render;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;
import de.projectp.escaped.resource.image.ImageProvider;
import de.projectp.escaped.resource.image.SpriteImage;
import de.projectp.escaped.util.DecimalPoint;
import de.projectp.escaped.util.GLUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class RenderAdapter implements Renderable {

    private final List<GLSequence> sequences = new ArrayList<>();
    private GLSequence curSequence;
    private GL2 _gl;

    public abstract void render();

    public void draw(RendererImpl impl) {
        _gl = impl.getGL();
        render();
    }

    @Override
    public void begin(int i) {
        curSequence = new GLSequence(i, new ArrayList<>());
    }

    @Override
    public void vertex2d(double i, double i1) {
        if (curSequence != null) curSequence.getVertexes().add(new DecimalPoint(i, i1));
    }

    public void rect2d(double i, double i1, double i2, double i3) {
        begin(GL2.GL_QUADS);
        vertex2d(i, i1);
        vertex2d(i, i1 + i3);
        vertex2d(i + i2, i1 + i3);
        vertex2d(i + i2, i1);
        end();
    }

    public void img(ImageProvider image, double i, double i1, double i2, double i3) {
        Texture texture = AWTTextureIO.newTexture(GLProfile.get("GL2"), image.getImage(), true);
        curSequence = new TextureGLSequence(GL2.GL_QUADS, new ArrayList<>(), texture);
        vertex2d(i, i1);
        vertex2d(i, i1 + i3);
        vertex2d(i + i2, i1 + i3);
        vertex2d(i + i2, i1);
        end();
    }

    @Override
    public void end() {
        if (curSequence != null) sequences.add(curSequence);
        curSequence = null;
    }

    public List<GLSequence> getSequences() {
        return sequences;
    }

}
