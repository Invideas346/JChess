package wolfg.aig.JCHess.Render;

import org.joml.Vector2f;
import org.lwjgl.opengl.GL40;
import wolfg.aig.JCHess.Render.Buffer.IndexBuffer;
import wolfg.aig.JCHess.Render.Buffer.VertexBuffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VAO {
    private static final int INVALID_VAO_ID = 0;
    private int id;
    private VertexBuffer vertexBuffer;
    private IndexBuffer indexBuffer;

    public VAO() {
        id = GL40.glGenVertexArrays();
        if (id == 0) {
            System.out.println("Could not generate VAO");
        }
        vertexBuffer = new VertexBuffer();
        indexBuffer = new IndexBuffer();
    }

    public VAO(Collection<Vector2f> vertices, Collection<Integer> indices) {
        id = GL40.glGenVertexArrays();
        if (id == 0) {
            System.out.println("Could not generate VAO");
        }
        GL40.glBindVertexArray(id);
        vertexBuffer = new VertexBuffer();
        indexBuffer = new IndexBuffer();
        setData(List.of((Vector2f[]) vertices.toArray()), List.of((Integer[]) indices.toArray()));
        unbind();
    }

    public VAO(Vector2f[] vertices, Collection<Integer> indices) {
        id = GL40.glGenVertexArrays();
        if (id == 0) {
            System.out.println("Could not generate VAO");
        }
        GL40.glBindVertexArray(id);
        vertexBuffer = new VertexBuffer();
        indexBuffer = new IndexBuffer();
        setData(List.of(vertices), List.of((Integer[]) indices.toArray()));
        unbind();
    }

    public VAO(Collection<Vector2f> vertices, int[] indices) {
        id = GL40.glGenVertexArrays();
        if (id == 0) {
            System.out.println("Could not generate VAO");
        }
        List<Integer> data = new ArrayList<>(indices.length);
        for (int i = 0; i < indices.length; i++)
            data.set(i, indices[i]);
        GL40.glBindVertexArray(id);
        vertexBuffer = new VertexBuffer();
        indexBuffer = new IndexBuffer();
        setData(List.of((Vector2f[]) vertices.toArray()), data);
        unbind();
    }

    public VAO(Vector2f[] vertices, int[] indices) {
        id = GL40.glGenVertexArrays();
        if (id == 0) {
            System.out.println("Could not generate VAO");
        }
        GL40.glBindVertexArray(id);
        vertexBuffer = new VertexBuffer();
        indexBuffer = new IndexBuffer();
        setData(vertices, indices);
        unbind();
    }

    public void setData(Vector2f[] vertices, int[] indices) {
        if(vertexBuffer != null && vertexBuffer.getID() != 0)
            vertexBuffer.setDataV2(vertices);
        if(indexBuffer != null && indexBuffer.getID() != 0)
            indexBuffer.setData(indices);
    }

    public void setData(List<Vector2f> vertices, List<Integer> indices) {
        if(vertexBuffer.isValid())
            vertexBuffer.setDataV2(vertices);
        if(indexBuffer.isValid())
            indexBuffer.setData(indices);
    }

    public void bind() {
        GL40.glBindVertexArray(id);
        vertexBuffer.bind();
        indexBuffer.bind();
    }

    public void render() {
        bind();
        GL40.glDrawElements(GL40.GL_TRIANGLES, indexBuffer.getSize(), GL40.GL_UNSIGNED_INT, 0);
        unbind();
    }

    public void unbind() {
        vertexBuffer.unbind();
        indexBuffer.unbind();
        GL40.glBindVertexArray(INVALID_VAO_ID);
    }

    public boolean isValid() {
        return id != INVALID_VAO_ID && vertexBuffer.isValid() && indexBuffer.isValid();
    }
}