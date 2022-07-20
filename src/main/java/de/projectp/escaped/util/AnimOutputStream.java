package de.projectp.escaped.util;

import de.enwaffel.randomutils.io.OutByteBuffer;
import de.projectp.escaped.resource.image.AnimData;
import org.json.JSONObject;

import java.io.OutputStream;

public class AnimOutputStream extends OutByteBuffer {

    private final AnimData animData;

    public AnimOutputStream(AnimData animData, OutputStream os) {
        super(os);
        this.animData = animData;
    }

    public void write() {
        JSONObject data = new JSONObject();
        data.put("image", JSONObject.NULL);
    }

}
