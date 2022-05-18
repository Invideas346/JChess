package wolfg.aig.JCHess.IO;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public final class Window implements IWindow{
    private long window;
    private String title;
    private int height;
    private int width;
    private RGBA clearColor;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.clearColor = new RGBA(0.0f,0.0f,0.0f,0.0f);
    }

    @Override
    public void init() {
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(window);
    }

    @Override
    public void dispose() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
    }

    @Override
    public void update() {
        glfwSwapBuffers(window); // swap the color buffers
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public long getWindowRef() {
        return window;
    }

    public String getTitle() {
        return title;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public RGBA getClearColor() {
        return clearColor;
    }

    public void setClearColor(RGBA clearColor) {
        if(clearColor.isValidRGBA()) {
            this.clearColor.setColor(clearColor);
            glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
        }
        else
            throw new IllegalArgumentException("RGBA values can only be in the range of 0 to 1");
    }
}
