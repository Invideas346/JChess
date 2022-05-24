package wolfg.aig.JCHess.IO;

import org.lwjgl.glfw.GLFW;

public class Keyboard implements IKeyboard {
    private final IWindow windowRef;

    public Keyboard(final IWindow windowRef) {
        super();
        this.windowRef = windowRef;
    }

    @Override
    public boolean isKeyPressed(int code) {
        return GLFW.glfwGetKey(windowRef.getWindowRef(), code) == GLFW.GLFW_PRESS;
    }
}
