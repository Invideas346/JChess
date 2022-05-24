package wolfg.aig.JCHess.IO;

import static org.lwjgl.opengl.GL11.glClearColor;

public interface IWindow {
    void init();
    void dispose();
    void update();
    boolean shouldClose();
    long getWindowRef();
    String getTitle();
    int getHeight();
    int getWidth();
    RGBA getClearColor();
    void setClearColor(RGBA clearColor);
}
