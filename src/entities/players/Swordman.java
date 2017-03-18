package entities.players;

import org.lwjgl.util.vector.Vector3f;

import collision.AABB;
import entities.Player;
import models.TexturedModel;

public class Swordman extends Player{
    
    
    /*
     *       health = (((i*i) + (i * 2) )+ 10*(i* 0.3));
     *       endurance = ((i * 1.1) + 10* (2.5* i));
     *       baseAttack = (((i*2)*0.75)+(i*i-(i*1.4))+ i*0.25)+0.5;
     *       xp = ((i+(1*i)*0.8)*17);    
     */
    

    public Swordman(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, int level)
    {
        super(model, position, rotX, rotY, rotZ, scale);
        if(level > this.maxLevel) level = this.maxLevel;
        this.level = level;
        this.maxHealth = calculateMaxHealth(level);
        this.health = this.maxHealth;
        this.maxEndurance = calculateMaxEndurance(level);
        this.endurance = this.maxEndurance;
        super.box = new AABB(1,2,1);
    }
    
    public Swordman update(){
        
        this.level = this.getLevel();
        this.maxHealth = this.getMaxHealth();
        this.health = this.getHealth();
        this.maxEndurance = this.getMaxEndurance();
        this.endurance = this.getEndurance();
        return this;
        
    }
    
    private int calculateMaxHealth(int level){
        int maxHealth;
        this.level = level;
        maxHealth = (int)(((level*level) + (level * 2) )+ 10*(level* 0.5));
        return maxHealth;
    }
    private int calculateMaxEndurance(int level){
        int maxEndurance;
        this.level = level;
        maxEndurance = (int)((level * 1.1) + 10);
        return maxEndurance;
    } 
    
    public int getEndurance(){
        return this.endurance;
    }
    public int getMaxEndurance(){
        return this.maxEndurance;
    }
    
    public int getMass(){
        return this.mass;
    }
    
    public int setMass(int newMass){
        return newMass;
    }
    public int getMaxHealth(){
        return this.maxHealth;
    }
    public int getHealth(){
        return this.health;
    }
    public String getMaxHealthString(){
        return "" + this.maxHealth;
    }
    public String getHealthString(){
        return "" + this.health;
    }
    public int getLevel(){
        return this.level;
    }
    public String getLevelString(){
        return "" + this.level;
    }
    
   
}
