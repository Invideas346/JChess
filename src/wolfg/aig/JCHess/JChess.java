package wolfg.aig.JCHess;

import org.joml.Vector2f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import wolfg.aig.JCHess.Logic.Game;
import wolfg.aig.JCHess.Render.MasterRenderer;
import wolfg.aig.JCHess.IO.RGBA;
import wolfg.aig.JCHess.IO.Window;
import wolfg.aig.JCHess.Render.Shader.Shader;
import wolfg.aig.JCHess.Render.Texture.Texture;
import wolfg.aig.JCHess.Render.VAO;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class JChess {
    private Window window;
    private Game game;
    private MasterRenderer masterRenderer;

    public void run() {
        init();
        loop();
        dispose();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        window = new Window("JChess", 1920, 1080);
        window.init();
        game = new Game();
        masterRenderer = new MasterRenderer();
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();


        // Set the clear color
        window.setClearColor(new RGBA(0.0f, 0.0f, 0.0f, 0.0f));
        Shader shader = new Shader("/home/wolfgang/Documents/FSST/JChess/src/wolfg/aig/JCHess/Shaders/vertexShader.glsl",
                "/home/wolfgang/Documents/FSST/JChess/src/wolfg/aig/JCHess/Shaders/fragmentShader.glsl"
        );
        Vector2f[] vertices = new Vector2f[]{new Vector2f(-0.5f, 0.5f),     // Coords
                                             new Vector2f(0.0f, 1.0f),      // texCoords
                                             new Vector2f(0.5f, 0.5f),      // Coords
                                             new Vector2f(1.0f, 1.0f),      // texCoords
                                             new Vector2f(0.5f, -0.5f),     // Coords
                                             new Vector2f(1.0f, 0.0f),      // texCoords
                                             new Vector2f(-0.5f, -0.5f),    // Coords
                                             new Vector2f(0.0f, 0.0f)};     // texCoords
        int[] indices = new int[]{0, 1, 2, 2, 3, 0};
        VAO vao = new VAO(vertices, indices);
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        Texture texture = new Texture("/home/wolfgang/Documents/FSST/JChess/src/wolfg/aig/JCHess/Textures/board.png", 0);
        texture.bind(0);
        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            shader.bind();
            shader.setUniformI("u_textureSampler", 0);
            vao.render();
            shader.unbind();

            game.tick();
            masterRenderer.render();

            window.update();
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
        shader.dispose();
    }

    public static void main(String[] args) {
        new JChess().run();
    }

    public void dispose() {
        window.dispose();
        masterRenderer.dispose();
        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}