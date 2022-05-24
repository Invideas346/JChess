package wolfg.aig.JCHess.Render.Buffer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL40;

import java.util.List;

public final class VertexBuffer extends Buffer {
    public VertexBuffer() {
        super();
    }

    public void setDataV2(List<Vector2f> vertices) {
        bind();
        float[] floats = new float[vertices.size() * 2];
        for (int i = 0; i < vertices.size(); i += 2) {
            floats[i] = vertices.get(i / 2).x;
            floats[i + 1] = vertices.get(i / 2).y;
        }
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, floats, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 2, GL40.GL_FLOAT, false, 16, 0);
        GL40.glVertexAttribPointer(1, 2, GL40.GL_FLOAT, false, 16, 8);
        size = vertices.size();
        unbind();
    }

    public void setDataV2(Vector2f[] vertices) {
        bind();
        float[] floats = new float[vertices.length * 2];
        for (int i = 0; i < (vertices.length * 2); i += 2) {
            floats[i] = vertices[i / 2].x;
            floats[i + 1] = vertices[i / 2].y;
        }
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, floats, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 2, GL40.GL_FLOAT, false, 16, 0);
        GL40.glVertexAttribPointer(1, 2, GL40.GL_FLOAT, false, 16, 8);
        size = vertices.length;
        unbind();
    }

    public void setDataV2(float[] vertices) {
        bind();
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, vertices, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 2, GL40.GL_FLOAT, false, 16, 0);
        GL40.glVertexAttribPointer(1, 2, GL40.GL_FLOAT, false, 16, 8);
        size = vertices.length;
        unbind();
    }

    public void setDataV3(List<Vector3f> vertices) {
        bind();
        float[] floats = new float[vertices.size() * 3];
        for (int i = 0; i < vertices.size(); i += 3) {
            floats[i] = vertices.get(i / 3).x;
            floats[i + 1] = vertices.get(i / 3).y;
            floats[i + 2] = vertices.get(i / 3).z;
        }
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, floats, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 3, GL40.GL_FLOAT, false, 20, 0);
        GL40.glVertexAttribPointer(1, 2, GL40.GL_FLOAT, false, 20, 12);
        size = vertices.size();
        unbind();
    }

    public void setDataV3(Vector3f[] vertices) {
        bind();
        float[] floats = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i += 2) {
            floats[i] = vertices[i / 2].x;
            floats[i + 1] = vertices[i / 2].y;
        }
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, floats, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 3, GL40.GL_FLOAT, false, 20, 0);
        GL40.glVertexAttribPointer(1, 2, GL40.GL_FLOAT, false, 20, 12);
        size = vertices.length;
        unbind();
    }

    public void setDataV3(float[] vertices) {
        bind();
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, vertices, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 3, GL40.GL_FLOAT, false, 20, 0);
        GL40.glVertexAttribPointer(1, 2, GL40.GL_FLOAT, false, 20, 12);
        size = vertices.length;
        unbind();
    }

    @Override
    public void bind() {
        GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, id);
        GL40.glEnableVertexAttribArray(0);
        GL40.glEnableVertexAttribArray(1);
    }

    @Override
    public void unbind() {
        GL40.glDisableVertexAttribArray(0);
        GL40.glDisableVertexAttribArray(1);
        GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, INVALID_BUFFER_ID);
    }
}