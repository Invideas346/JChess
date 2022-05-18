package wolfg.aig.JCHess.Render.Buffer;

import org.lwjgl.opengl.GL40;

import java.util.ArrayList;

public final class IndexBuffer extends Buffer {
    public IndexBuffer() {
        super();
    }

    public void setData(ArrayList<Integer> indices) {
        bind();
        int[] data = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            data[i] = indices.get(i);
        }
        GL40.glBufferData(GL40.GL_ELEMENT_ARRAY_BUFFER, data, GL40.GL_STATIC_DRAW);
        unbind();
    }

    public void setData(int[] indices) {
        bind();
        GL40.glBufferData(GL40.GL_ELEMENT_ARRAY_BUFFER, indices, GL40.GL_STATIC_DRAW);
        unbind();
    }

    @Override
    public void bind() {
        GL40.glBindBuffer(GL40.GL_ELEMENT_ARRAY_BUFFER, id);
    }

    @Override
    public void unbind() {
        GL40.glBindBuffer(GL40.GL_ELEMENT_ARRAY_BUFFER, INVALID_BUFFER_ID);
    }
}