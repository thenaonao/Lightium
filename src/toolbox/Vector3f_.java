package toolbox;

import org.lwjgl.util.vector.Vector3f;

public class Vector3f_
{
    public float x,y,z;
    
    public Vector3f_(){
        this(0,0,0);
    }

    public Vector3f_(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3f_(Vector3f_ r){
        this.x = r.x;
        this.y = r.y;
        this.z = r.z;
    }
    
    public float length(){
        return (float) Math.sqrt(x*x + y*y + z*z);
    }
    
    public Vector3f_ normalize() {
        x /= length();
        y /= length();
        z /= length();
        
        return this;
}
    
    public Vector3f_ add(Vector3f vec) {
        x += vec.getX();
        y += vec.getY();
        z += vec.getZ();
        
        return this;
    }
    
    public Vector3f_ sub(Vector3f vec) {
        x -= vec.getX();
        y -= vec.getY();
        z -= vec.getZ();
        
        return this;
    }
    public Vector3f_ sub(Vector3f_ vec) {
        x -= vec.getX();
        y -= vec.getY();
        z -= vec.getZ();
        
        return this;
    }
    
    public Vector3f_ mul(Vector3f vec) {
        x *= vec.getX();
        y *= vec.getY();
        z *= vec.getZ();
        
        return this;
    }
    
    public Vector3f_ mul(float x, float y, float z){
        this.x *= x;
        this.y *= y;
        this.z *= z;
        
        return this;
    }
    
    public Vector3f_ div(Vector3f_ vec) {
        x /= vec.getX();
        y /= vec.getY();
        z /= vec.getZ();
        
        return this;
    }
    
    
    public Vector3f_ add(float v) {
        x += v;
        y += v;
        z += v;
        
        return this;
    }
    
    public Vector3f_ sub(float v) {
        x -= v;
        y -= v;
        z -= v;
        
        return this;
    }
    
    public Vector3f_ mul(float v) {
        x *= v;
        y *= v;
        z *= v;
        
        return this;
    }
    
    public Vector3f_ div(float v) {
        x /= v;
        y /= v;
        z /= v;
        
        return this;
    }
    
    // ---- X
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }
    
    public Vector3f_ addX(float v) {
        x += v;
        return this;
    }
    public Vector3f_ subX(float v) {
        x -= v;
        return this;
    }
    
    
    // ----- Y
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public Vector3f_ addY(float v) {
        y += v;
        return this;
    }
    public Vector3f_ subY(float v) {
        y -= v;
        return this;
    }
    
    
    // ----- Z
    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    
    public Vector3f_ addZ(float v) {
        z += v;
        return this;
    }
    public Vector3f_ subZ(float v) {
        z -= v;
        return this;
}
}
