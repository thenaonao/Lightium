package collision;

import org.lwjgl.util.vector.Vector3f;

import toolbox.Vector3f_;

public class Sphere {
    public Vector3f_ center;
    public float radius;
    
    public Sphere(final float radius) {
       center = (Vector3f_)new Vector3f();
       this.radius = radius;
    }
    
    public void update(final Vector3f position) {
       center.x = position.x;
       center.y = position.y;
       center.z = position.z;
    }
 }