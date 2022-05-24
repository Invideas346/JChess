package wolfg.aig.JCHess.Render.Shader;

public interface IShader {
    int getProgramID();
    int getVertexShaderID();
    int getFragmentShaderID();
    void bind();
    void unbind();
    void dispose();
    void setUniformI(String uniformName, int value);
}
