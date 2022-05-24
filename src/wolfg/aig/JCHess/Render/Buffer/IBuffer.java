package wolfg.aig.JCHess.Render.Buffer;

public interface IBuffer {
    int getID();
    int getSize();
    void bind();
    void unbind();
    void dispose();
    boolean isValid();
}