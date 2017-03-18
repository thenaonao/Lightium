package collision;

import org.lwjgl.util.vector.Vector3f;

public class AABB {
   public Vector3f center;
   public float r[];
   
   public AABB(final float width, final float height, final float depth) {
      center = new Vector3f();
      r = new float[3];
      r[0] = width * 0.5f;
      r[1] = height * 0.5f;
      r[2] = depth * 0.5f;  
   }
   
   public void update(final Vector3f position) {
      center.x = position.x;
      center.y = position.y;
      center.z = position.z;
   }
}