package wolfg.aig.JCHess.Render.Buffer;

import org.lwjgl.opengl.GL40;

public abstract class Buffer implements IBuffer {
    protected static final int INVALID_BUFFER_ID = 0;
    protected int id;

    public Buffer() {
        id = GL40.glGenBuffers();
        if(id == 0) {
            System.out.println("Could not create Buffer");
        }
    }

    public int getID() {
        return id;
    }

    @Override
    public void dispose() {
        GL40.glDeleteBuffers(id);
        id = INVALID_BUFFER_ID;
    }
}