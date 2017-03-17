package guis;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {

    private int texture;
    private Vector2f position;
    private Vector2f scale;

    /**
     * 
     * @param texture Load texture
     * @param position Position Vector2f
     * @param scale Scale Vector2f
     */
    public GuiTexture(int texture, Vector2f position, Vector2f scale) {
        this.texture = texture;
        this.position = position;
        this.scale = scale;
    }
   

    public int getTexture() {
        return texture;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }

}