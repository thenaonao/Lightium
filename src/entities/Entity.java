package entities;

import models.TexturedModel;
import toolbox.Vector3f_;

import org.lwjgl.util.vector.Vector3f;

import collision.AABB;
import collision.CollisionLib;

public class Entity
{

    private TexturedModel model;
    private Vector3f position;
    protected AABB box;
    private float rotX, rotY, rotZ;
    private float scale;

    private int textureIndex = 0;

    /**
     * @param model
     * @param position
     * @param rotX
     * @param rotY
     * @param rotZ
     * @param scale
     */
    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, AABB box)
    {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
        this.box = box;
    }
    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale)
    {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
        this.box = new AABB(0,0,0);
    }

    /**
     * @param model
     * @param index
     *            "INDEX OF ATLAS IMAGE"
     * @param position
     * @param rotX
     * @param rotY
     * @param rotZ
     * @param scale
     */
    public Entity(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale, AABB box)
    {
        this.textureIndex = index;
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
        this.box = box;
    }
    public Entity(TexturedModel model, int index, Vector3f position, float rotX, float rotY, float rotZ, float scale)
    {
        this.textureIndex = index;
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
        this.box = new AABB(0,0,0);
    }
    
    public AABB getBox(){
        return box;
    }
    
   /* public void collision(){
        CollisionLib.testAABBAABB(box, null);
    }*/

    public float getTextureXOffset()
    {
        int column = textureIndex % model.getTexture().getNumberOfRows();
        return (float)column / (float)model.getTexture().getNumberOfRows();
    }

    public float getTextureYOffset(){
        int row = textureIndex / model.getTexture().getNumberOfRows();
        return (float)row / (float)model.getTexture().getNumberOfRows();
        }
    
    public void increasePosition(float dx, float dy, float dz)
    {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }
    
    public void increasePosition(Vector3f vec3){
        this.position.x += vec3.x;
        this.position.y += vec3.y;
        this.position.z += vec3.z;
    }
    
    public void increasePosition(Vector3f_ currentSpeed, float mult){
        this.position.x += currentSpeed.x*mult;
        this.position.y += currentSpeed.y*mult;
        this.position.z += currentSpeed.z*mult;
    }

    public void increaseRotation(float dx, float dy, float dz)
    {
        this.rotX += dx;
        
       /* if(dy > 1.0f){
            dy = 1.0f;
        }else if(dy < -1.0f){
            dy = -1.0f; 
        }*/
        this.rotY += dy;
        this.rotZ += dz;
    }

    public void increaseRotation(Vector3f vec3)
    {
        this.rotX += vec3.x;
        this.rotY += vec3.y;
        this.rotZ += vec3.z;
    }
    
    public void increaseRotation(Vector3f_ currentTurnSpeed, float mul)
    {
        this.rotX += currentTurnSpeed.x*mul;
        this.rotY += currentTurnSpeed.y*mul;
        this.rotZ += currentTurnSpeed.z*mul;
    }

    public void setRotation(float dx, float dy, float dz){
        this.rotX = dx;
        this.rotY = dy;
        this.rotZ = dz;
    }
    public TexturedModel getModel()
    {
        return model;
    }

    public void setModel(TexturedModel model)
    {
        this.model = model;
    }

    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public float getRotX()
    {
        return rotX;
    }

    public void setRotX(float rotX)
    {
        this.rotX = rotX;
    }

    public float getRotY()
    {
        return rotY;
    }

    public void setRotY(float rotY)
    {
        this.rotY = rotY;
    }

    public float getRotZ()
    {
        return rotZ;
    }

    public void setRotZ(float rotZ)
    {
        this.rotZ = rotZ;
    }

    public float getScale()
    {
        return scale;
    }

    public void setScale(float scale)
    {
        this.scale = scale;
    }

}