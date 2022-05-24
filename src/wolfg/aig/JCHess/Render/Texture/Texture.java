package wolfg.aig.JCHess.Render.Texture;

import org.lwjgl.opencl.IMGCachedAllocations;
import org.lwjgl.opengl.GL40;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;

public class Texture {
    private final int INVALID_TEXTURE_ID = 0;
    private int id = INVALID_TEXTURE_ID;
    private int boundTextureSlot = 0;
    private int width = 0;
    private int height = 0;
    private int channel = 0;

    public Texture() {
        id = GL40.glGenTextures();
        if (id == INVALID_TEXTURE_ID) {
            System.out.println("Could not create Buffer");
        }
    }

    public Texture(String path, int textureSlot) {
        id = GL40.glGenTextures();
        if (id == INVALID_TEXTURE_ID) {
            System.out.println("Could not create Buffer");
        }
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Please specify a texture path");
        }
        loadTexture(path, textureSlot);
    }

    public void loadTexture(String path, int textureSlot) {
        int[] w = new int[1];
        int[] h = new int[1];
        int[] c = new int[1];
        STBImage.stbi_set_flip_vertically_on_load(true);
        ByteBuffer imageData = STBImage.stbi_load(path, w, h, c, 4);
        width = w[0];
        height = h[0];
        channel = c[0];

        // Bind the texture
        bind(textureSlot);

        // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
        GL40.glPixelStorei(GL40.GL_UNPACK_ALIGNMENT, 1);
        GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_WRAP_S, GL40.GL_CLAMP_TO_BORDER);
        GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_WRAP_T, GL40.GL_CLAMP_TO_BORDER);
        GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_MIN_FILTER, GL40.GL_NEAREST);
        GL40.glTexParameteri(GL40.GL_TEXTURE_2D, GL40.GL_TEXTURE_MAG_FILTER, GL40.GL_NEAREST);

        // Upload the texture data
        if(channel == 3)
            GL40.glTexImage2D(GL40.GL_TEXTURE_2D, 0, GL40.GL_RGB, width, height, 0, GL40.GL_RGB, GL40.GL_UNSIGNED_BYTE, imageData);
        else if(channel == 4)
            GL40.glTexImage2D(GL40.GL_TEXTURE_2D, 0, GL40.GL_RGBA, width, height, 0, GL40.GL_RGBA, GL40.GL_UNSIGNED_BYTE, imageData);

        // Generate Mip Map
        GL40.glGenerateMipmap(GL40.GL_TEXTURE_2D);
        unbind();
    }

    public int getId() {
        return id;
    }

    public void bind(int textureSlot) {
        GL40.glActiveTexture(GL40.GL_TEXTURE0 + textureSlot);
        GL40.glBindTexture(GL40.GL_TEXTURE0 + textureSlot, id);
        boundTextureSlot = textureSlot;
    }

    public void unbind() {
        GL40.glBindTexture(GL40.GL_TEXTURE0 + boundTextureSlot, INVALID_TEXTURE_ID);
    }

    public void dispose() {
        GL40.glDeleteTextures(id);
        width = 0;
        height = 0;
        channel = 0;
    }
}
