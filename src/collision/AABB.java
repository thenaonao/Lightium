package collision;

import toolbox.Vector3f_;

public class AABB
{
    private Vector3f_ minExtends;
    private Vector3f_ maxExtends;

    public AABB(Vector3f_ minExtends, Vector3f_ maxExtends)
    {

        this.minExtends = minExtends;
        this.maxExtends = maxExtends;
    }

    public IntersectData IntersectAABB(AABB other)
    {
        Vector3f_ distances1 = other.GetMinExtends().sub(maxExtends);
        Vector3f_ distances2 = minExtends.sub(other.GetMaxExtends());
      
        Vector3f_ distances = new Vector3f_(distances1.max(distances2));
        
 
        float maxDistance = distances.max();
        
        return new IntersectData(maxDistance < 0, maxDistance);
    }

    public  Vector3f_ GetMinExtends()
    {
        return minExtends;
    }

    public  Vector3f_ GetMaxExtends()
    {
        return maxExtends;
    }
}
