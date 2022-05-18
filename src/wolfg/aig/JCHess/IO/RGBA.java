package wolfg.aig.JCHess.IO;

public class RGBA {
    public float r, g, b, a;

    public RGBA(float r, float g, float b, float a) {
        setColor(r, g, b, a);
    }

    public boolean isValidRGBA() {
        return (r >= 0 && r <= 1) && (g >= 0 && g <= 1) && (b >= 0 && b <= 1) && (a >= 0 && a <= 1);
    }

    public void setColor(float r, float g, float b, float a) {
        if (isValidRGBA(r, g, b, a)) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;
        } else
            throw new IllegalArgumentException("RGBA values can only be in the range of 0 to 1");
    }

    public void setColor(RGBA color) {
        this.setColor(color.r, color.g, color.b, color.a);
    }

    private boolean isValidRGBA(float r, float g, float b, float a) {
        return (r >= 0 && r <= 1) && (g >= 0 && g <= 1) && (b >= 0 && b <= 1) && (a >= 0 && a <= 1);
    }

    private boolean isValidRGBA(RGBA color) {
        return (color.r >= 0 && color.r <= 1) && (color.g >= 0 && color.g <= 1) && (color.b >= 0 && color.b <= 1) && (color.a >= 0 && color.a <= 1);
    }
}
