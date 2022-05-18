package wolfg.aig.JCHess.Render.Buffer;

public interface IBuffer {
    int getID();
    void bind();
    void unbind();
    void dispose();
}