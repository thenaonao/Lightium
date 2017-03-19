package collision;

public class IntersectData
{
    private  boolean doesIntersect;
    private  float distance;
    
    public IntersectData( boolean doesIntersect,  float distance){
        this.doesIntersect = doesIntersect;
        this.distance  = distance;
    }
    
    public  boolean GetDoesIntersect(){
        return doesIntersect;
    }
    public  float GetDistance(){
        return distance;
    }
}
