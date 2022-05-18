package wolfg.aig.JCHess.Render.Buffer;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL40;

import java.util.ArrayList;

public final class VertexBuffer extends Buffer {
    public VertexBuffer() {
        super();
    }

    public void setDataV2(ArrayList<Vector2f> vertices) {
        bind();
        float[] floats = new float[vertices.size() * 2];
        for (int i = 0; i < vertices.size(); i += 2) {
            floats[i] = vertices.get(i).x;
            floats[i + 1] = vertices.get(i).y;
        }
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, floats, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 2, GL40.GL_FLOAT, false, 2 * 4, 0);
        unbind();
    }

    public void setDataV2(Vector2f[] vertices) {
        bind();
        float[] floats = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i += 2) {
            floats[i] = vertices[i].x;
            floats[i + 1] = vertices[i].y;
        }
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, floats, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 2, GL40.GL_FLOAT, false, 2 * 4, 0);
        unbind();
    }

    public void setDataV2(float[] vertices) {
        bind();
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, vertices, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 2, GL40.GL_FLOAT, false, 2 * 4, 0);
        unbind();
    }

    public void setDataV3(ArrayList<Vector3f> vertices) {
        bind();
        float[] floats = new float[vertices.size() * 3];
        for (int i = 0; i < vertices.size(); i += 3) {
            floats[i] = vertices.get(i).x;
            floats[i + 1] = vertices.get(i).y;
            floats[i + 2] = vertices.get(i).z;
        }
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, floats, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 3, GL40.GL_FLOAT, false, 3 * 4, 0);
        unbind();
    }

    public void setDataV3(Vector3f[] vertices) {
        bind();
        float[] floats = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i += 2) {
            floats[i] = vertices[i].x;
            floats[i + 1] = vertices[i].y;
        }
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, floats, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 3, GL40.GL_FLOAT, false, 3 * 4, 0);
        unbind();
    }

    public void setDataV3(float[] vertices) {
        bind();
        GL40.glBufferData(GL40.GL_ARRAY_BUFFER, vertices, GL40.GL_STATIC_DRAW);
        GL40.glVertexAttribPointer(0, 3, GL40.GL_FLOAT, false, 3 * 4, 0);
        unbind();
    }

    @Override
    public void bind() {
        GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, id);
    }

    @Override
    public void unbind() {
        GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, INVALID_BUFFER_ID);
    }
}