package wolfg.aig.JCHess.Logic.Util;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
