package toolbox;

import org.lwjgl.util.vector.Vector3f;

public class Vector3f_ 
{
    private float x;
    private float y;
    private float z;

   
    
    public float getX(){
        return x;
    }
    public float getZ(){
        return z;
    }
    public float getY(){
        return y;
    }
    
    public Vector3f_()
    {
        this(0, 0, 0);
    }

    public Vector3f_(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f_(Vector3f_ r)
    {
        this.x = r.x;
        this.y = r.y;
        this.z = r.z;
    }

    /*
     * @Override
     * public float length(){
     * return (float) Math.sqrt(x*x + y*y + z*z);
     * }
     */

    public Vector3f_ normalize()
    {
        x /= length();
        y /= length();
        z /= length();

        return this;
    }
    
    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z);
}

    public Vector3f_ add(Vector3f_ vec)
    {
        x += vec.getX();
        y += vec.getY();
        z += vec.getZ();

        return this;
    }

    public Vector3f_ sub(Vector3f_ vector3f)
    {
        x -= vector3f.getX();
        y -= vector3f.getY();
        z -= vector3f.getZ();

        return this;
    }

    public Vector3f_ mul(Vector3f_ vec)
    {
        x *= vec.getX();
        y *= vec.getY();
        z *= vec.getZ();

        return this;
    }

    public Vector3f_ div(Vector3f_ vec)
    {
        x /= vec.getX();
        y /= vec.getY();
        z /= vec.getZ();

        return this;
    }

    public Vector3f_ add(float v)
    {
        x += v;
        y += v;
        z += v;

        return this;
    }

    public Vector3f_ add(float v, float w)
    {
        x += v;
        y += w;
        z += 0;
        return this;
    }

    public Vector3f_ add(float v, float w, float u)
    {
        x += v;
        y += w;
        z += u;
        return this;
    }

    public Vector3f_ sub(float v)
    {
        x -= v;
        y -= v;
        z -= v;

        return this;
    }

    public Vector3f_ sub(float u, float v, float w)
    {
        x -= u;
        y -= v;
        z -= w;

        return this;
    }

    public Vector3f_ mul(float v)
    {
        x *= v;
        y *= v;
        z *= v;

        return this;
    }

    public Vector3f_ div(float v)
    {
        x /= v;
        y /= v;
        z /= v;

        return this;
    }

    /*
     * // ---- X
     * public float getX() {
     * return x;
     * }
     * public void setX(float x) {
     * this.x = x;
     * }
     */

    public Vector3f_ addX(float v)
    {
        x += v;
        return this;
    }

    public Vector3f_ subX(float v)
    {
        x -= v;
        return this;
    }

    // ----- Y
    /*
     * public float getY() {
     * return y;
     * }
     * public void setY(float y) {
     * this.y = y;
     * }
     */

    public Vector3f_ addY(float v)
    {
        y += v;
        return this;
    }

    public Vector3f_ subY(float v)
    {
        y -= v;
        return this;
    }


    public void setZ(float z)
    {
        this.z = z;
    }

    public Vector3f_ addZ(float v)
    {
        z += v;
        return this;
    }

    public Vector3f_ subZ(float v)
    {
        z -= v;
        return this;
    }

    public final float distSq(Vector3f_ v)
    {
        if(v != null)
        {
            final float dx = x - v.x;
            final float dy = y - v.y;
            final float dz = z - v.z;
            return dx * dx + dy * dy + dz * dz;
        }
        else
        {
            return Float.NaN;
        }
    }

    public Vector3f_ max(Vector3f_ r)
    {
        float x;
        float y;
        float z;
        if(r.getX() > this.getX())
            x = r.getX();
        else
            x = this.getX();
        if(r.getY() > this.getY())
            y = r.getY();
        else
            y = this.getY();
        if(r.getZ() > this.getZ())
            z = r.getZ();
        else
            z = this.getZ();

        return new Vector3f_(x, y, z);
    }
    public float max()
    {
        if(this.x > this.y){
            return x;
        }else if(this.x > this.z){
            return x;
        }else if(this.y > this.x){
            return y;
        }else if(this.y > this.z){
            return y;
        }else if(this.z > this.x){
            return z;
        }else {
            return z;
        }
      

       
    }
  
    
    public Vector3f ConvertToVector3f(Vector3f_ input){
        Vector3f output = new Vector3f();
        output.x = input.x;
        output.y = input.y;
        output.z = input.z;
        return output;
    }
}
