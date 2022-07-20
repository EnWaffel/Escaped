package de.projectp.escaped.game.render;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.texture.Texture;
import de.projectp.escaped.util.DecimalPoint;

import java.util.ArrayList;
import java.util.List;

public class Renderer implements GLEventListener {

    private final List<RenderAdapter> adapters = new ArrayList<>();

    public void attach(RenderAdapter adapter) {
        adapters.add(adapter);
    }

    public void detach(RenderAdapter adapter) {
        adapters.remove(adapter);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0, 0, 0, 0);
        gl.glClearDepth(1);
        gl.glHint(GL2.GL_LINE_SMOOTH_HINT, GL2.GL_NICEST);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        // update all the adapters
        // then draw the vertex data from the adapters onto the screen
        for (RenderAdapter adapter : adapters) {
            adapter.getSequences().clear();
            adapter.draw(() -> gl);
            for (GLSequence sequence : adapter.getSequences()) {
                if (sequence instanceof TextureGLSequence) {
                    TextureGLSequence seq = (TextureGLSequence) sequence;
                    Texture texture = seq.getTexture();
                    texture.enable(gl);
                    texture.bind(gl);
                    gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
                    gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

                    gl.glBegin(sequence.getGL());
                    gl.glTexCoord2d(0, 0);
                    gl.glVertex2d(seq.getVertexes().get(0).x, seq.getVertexes().get(0).y);
                    gl.glTexCoord2d(1, 0);
                    gl.glVertex2d(seq.getVertexes().get(1).x, seq.getVertexes().get(1).y);
                    gl.glTexCoord2d(1, 1);
                    gl.glVertex2d(seq.getVertexes().get(2).x, seq.getVertexes().get(2).y);
                    gl.glTexCoord2d(0, 1);
                    gl.glVertex2d(seq.getVertexes().get(3).x, seq.getVertexes().get(3).y);
                } else {
                    gl.glBegin(sequence.getGL());
                    for (DecimalPoint vertex : sequence.getVertexes()) {
                        gl.glVertex2d(vertex.x, vertex.y);
                    }
                }
                gl.glEnd();
                gl.glFlush();
            }
        }
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glViewport(-i/2, -i1/2, i, i1);
    }

}
