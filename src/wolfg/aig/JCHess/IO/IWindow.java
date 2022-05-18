package wolfg.aig.JCHess.IO;

public interface IWindow {
    void init();
    void dispose();
    void update();
    boolean shouldClose();
}
