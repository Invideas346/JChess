package wolfg.aig.JCHess.Render.Shader;

import org.lwjgl.opengl.GL40;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import static java.lang.Character.getName;

public class Shader implements IShader {
    protected static final int INVALID_PROGRAM_ID = 0;
    protected static final int INVALID_SHADER_ID = 0;
    protected int vertexShaderID;
    protected int fragmentShaderID;
    protected int programID;

    public Shader(String vertexShader, String fragmentShader) {
        if (vertexShader.isEmpty() || fragmentShader.isEmpty())
            throw new IllegalArgumentException("A path to the shaders has to be specified");

        String vertexShaderContent = parseFile(vertexShader);
        if (vertexShaderContent.isEmpty())
            throw new RuntimeException("VertexShader is empty");
        String fragmentShaderContent = parseFile(fragmentShader);
        if (fragmentShaderContent.isEmpty())
            throw new RuntimeException("FragmentShader is empty");

        vertexShaderID = GL40.glCreateShader(GL40.GL_VERTEX_SHADER);
        if (vertexShaderID == 0)
            throw new RuntimeException("Could not create vertexShader");
        fragmentShaderID = GL40.glCreateShader(GL40.GL_FRAGMENT_SHADER);
        if (fragmentShaderID == 0)
            throw new RuntimeException("Could not create fragmentShader");

        programID = GL40.glCreateProgram();
        GL40.glShaderSource(vertexShaderID, vertexShaderContent);
        GL40.glShaderSource(fragmentShaderID, fragmentShaderContent);

        GL40.glCompileShader(vertexShaderID);
        GL40.glCompileShader(fragmentShaderID);

        this.checkShaderCompileStatus(vertexShaderID, GL40.GL_VERTEX_SHADER);
        this.checkShaderCompileStatus(fragmentShaderID, GL40.GL_FRAGMENT_SHADER);

        GL40.glAttachShader(programID, vertexShaderID);
        GL40.glAttachShader(programID, fragmentShaderID);

        GL40.glLinkProgram(programID);

        GL40.glDetachShader(programID, vertexShaderID);
        GL40.glDetachShader(programID, fragmentShaderID);
        this.deleteShaders();
    }

    @Override
    public void bind() {
        GL40.glUseProgram(programID);
    }

    @Override
    public void unbind() {
        GL40.glUseProgram(INVALID_PROGRAM_ID);
    }

    @Override
    public void dispose() {
        deleteShaders();
        GL40.glDeleteProgram(programID);
        programID = INVALID_PROGRAM_ID;
    }

    public void deleteShaders() {
        GL40.glDeleteShader(vertexShaderID);
        GL40.glDeleteShader(fragmentShaderID);
        vertexShaderID = INVALID_SHADER_ID;
        fragmentShaderID = INVALID_SHADER_ID;
    }

    @Override
    public int getProgramID() {
        return programID;
    }

    @Override
    public int getVertexShaderID() {
        return vertexShaderID;
    }

    @Override
    public int getFragmentShaderID() {
        return fragmentShaderID;
    }

    @Override
    public void setUniformI(String uniformName, int value) {
        int location = GL40.glGetUniformLocation(programID, uniformName);
        GL40.glUniform1i(location, value);
    }

    private String parseFile(String filepath) {
        Path filePath = Path.of(filepath);
        StringBuilder contentBuilder = new StringBuilder();
        String sCurrentLine;
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return contentBuilder.toString();
    }

    private void checkShaderCompileStatus(int shader, int type) {
        //if info/warnings are found, append it to our shader log
        String infoLog = GL40.glGetShaderInfoLog(shader,
                GL40.glGetShaderi(shader, type));
        //if the compiling was unsuccessful, throw an exception
        if (GL40.glGetShaderi(shader, GL40.GL_COMPILE_STATUS) == GL40.GL_FALSE)
            throw new RuntimeException ("Failure in compiling " + getName(type)
                    + ". Error log:\n" + infoLog);
    }
}
