package toolbox;

import org.lwjgl.util.vector.Vector3f;

public class Vector3f_ extends Vector3f
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
    
    public Vector3f_(Vector3f r){
        this.x = r.x;
        this.y = r.y;
        this.z = r.z;
    }
    
   /* @Override
    public float length(){
        return (float) Math.sqrt(x*x + y*y + z*z);
    }*/
    
    public Vector3f normalize() {
        x /= length();
        y /= length();
        z /= length();
        
        return this;
}
    
    public Vector3f add(Vector3f vec) {
        x += vec.getX();
        y += vec.getY();
        z += vec.getZ();
        
        return this;
    }
    
    public Vector3f sub(Vector3f vec) {
        x -= vec.getX();
        y -= vec.getY();
        z -= vec.getZ();
        
        return this;
    }
   
    public Vector3f mul(Vector3f vec) {
        x *= vec.getX();
        y *= vec.getY();
        z *= vec.getZ();
        
        return this;
    }
    
 
    
    public Vector3f div(Vector3f vec) {
        x /= vec.getX();
        y /= vec.getY();
        z /= vec.getZ();
        
        return this;
    }
    
    
    public Vector3f add(float v) {
        x += v;
        y += v;
        z += v;
        
        return this;
    }
    
    public Vector3f sub(float v) {
        x -= v;
        y -= v;
        z -= v;
        
        return this;
    }
    
    public Vector3f mul(float v) {
        x *= v;
        y *= v;
        z *= v;
        
        return this;
    }
    
    public Vector3f div(float v) {
        x /= v;
        y /= v;
        z /= v;
        
        return this;
    }
    
  /*  // ---- X
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }*/
    
    public Vector3f addX(float v) {
        x += v;
        return this;
    }
    public Vector3f subX(float v) {
        x -= v;
        return this;
    }
    
    
    // ----- Y
  /*  public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }*/
    
    public Vector3f addY(float v) {
        y += v;
        return this;
    }
    public Vector3f subY(float v) {
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
    
    public Vector3f addZ(float v) {
        z += v;
        return this;
    }
    public Vector3f subZ(float v) {
        z -= v;
        return this;
    }
    
    public final float distSq(Vector3f v) {
        if (v != null) {
            final float dx = x - v.x;
            final float dy = y - v.y;
            final float dz = z - v.z;
            return dx * dx + dy * dy + dz * dz;
        } else {
            return Float.NaN;
        }
}
}
