package wolfg.aig.JCHess;

import org.joml.Vector2i;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import wolfg.aig.JCHess.Logic.Game;
import wolfg.aig.JCHess.Render.MasterRenderer;
import wolfg.aig.JCHess.IO.RGBA;
import wolfg.aig.JCHess.IO.Window;

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

        window = new Window("JChess", 300, 300);
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
        window.setClearColor(new RGBA(1.0f, 0.0f, 0.0f, 0.0f));

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            game.tick();
            masterRenderer.render();
            window.update();
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
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