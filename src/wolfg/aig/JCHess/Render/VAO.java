package wolfg.aig.JCHess.Render;

import org.lwjgl.opengl.GL40;

public class VAO {
    private static final int INVALID_VAO_ID = 0;
    private int id;

    public VAO() {
        id = GL40.glGenVertexArrays();
        if(id == 0) {
            System.out.println("Could not generate VAO");
        }
    }

    public void bind() {
        GL40.glBindVertexArray(id);
    }

    public void unbind() {
        GL40.glBindVertexArray(INVALID_VAO_ID);
    }
}
