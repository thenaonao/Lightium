package collision;

import org.lwjgl.util.vector.Vector3f;

public class CollisionLib
{
    /**
     * AABB-AABB
     * @param box1
     * @param box2
     * @return
     */
    public static boolean testAABBAABB(final AABB box1, final AABB box2) {
        if(Math.abs(box1.center.x - box2.center.x) > (box1.r[0] + box2.r[0])) return false;
        if(Math.abs(box1.center.y - box2.center.y) > (box1.r[1] + box2.r[1])) return false;
        if(Math.abs(box1.center.z - box2.center.z) > (box1.r[2] + box2.r[2])) return false;
        return true;
     }
  /**
   *  Sphere-Sphere
   * @param c1
   * @param c2
   * @return
   */
    public static boolean testSphereSphere(final Sphere c1, final Sphere c2){
        final float distSQ = c1.center.distSq(c2.center); 
        final float radiusSum = c1.radius + c2.radius;
        
        return distSQ <= radiusSum*radiusSum;
    }
    
    /**
     * 
     * @param p
     * @param aabb
     * @return
     */
    public static float sqDistPointAABB(final Vector3f p, final AABB aabb){
        float sqDist = 0.0f;
        float v;
        float minX,minY,minZ, maxX, maxY, maxZ;
        
        minX = aabb.center.x - aabb.r[0];
        maxX = aabb.center.x + aabb.r[0];
        
        minY = aabb.center.y - aabb.r[1];
        maxY = aabb.center.y + aabb.r[1];
        
        minZ = aabb.center.z - aabb.r[2];
        maxZ = aabb.center.z + aabb.r[2];
        
        v = p.x;
        if(v < minX) sqDist += (minX - v) *(minX - v);
        if(v > maxX) sqDist += (v - maxX) * (v- maxX);
        
        v = p.y;
        if(v < minY) sqDist += (minY - v) *(minY - v);
        if(v > maxY) sqDist += (v - maxY) * (v- maxY);
        
        v = p.z;
        if(v < minZ) sqDist += (minZ - v) *(minZ - v);
        if(v > maxZ) sqDist += (v - maxZ) * (v- maxZ);
        
        return sqDist;
    }
    /**
     * Sphere-AABB
     * @param s
     * @param box
     * @return
     */
    public static boolean testSphereAABB(final Sphere s, final AABB box){
        float sqDist = sqDistPointAABB(s.center, box);
        float r = s.radius;
        
        return sqDist <=  r*r;
    }
    
}
