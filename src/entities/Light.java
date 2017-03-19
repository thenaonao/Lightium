package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light
{

    private Vector3f position;
    private Vector3f colour;
    private Vector3f attenuation = new Vector3f(1,0,0);

    public Light(Vector3f position, Vector3f colour)
    {
        this.position = position;
        this.colour = colour;
    }
    
    public Light(Vector3f position, Vector3f colour, Vector3f attenuation)
    {
        this.position = position;
        this.colour = colour;
        this.attenuation = attenuation;
    }

    public Vector3f getAttenuation(){
        return attenuation;
    }
    
    public Vector3f getPosition()
    {
        return position;
    }

    public void setPosition(Vector3f position)
    {
        this.position = position;
    }

    public void addPosition(float x,float y , float z){
        this.position.x += x;
        this.position.y += y;
        this.position.z += z;
    }
    
    public void addPosition(Vector3f vec){
        this.position.x += vec.x;
        this.position.y += vec.y;
        this.position.z += vec.z;
    }
    public Vector3f getColour()
    {
        return colour;
    }

    public void setColour(Vector3f colour)
    {
        this.colour = colour;
    }

}