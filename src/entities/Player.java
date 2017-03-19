package entities;

import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import audio.AudioMaster;
import audio.Source;
import main.MainGameLoop;
import renderEngine.DisplayManager;
import terrains.Terrain;

public class Player extends Entity {

    private static final float RUN_SPEED = 60;
    private static final float TURN_SPEED = 160;
    private static final float GRAVITY = -60;
    private static final float JUMP_POWER = 30;
    private static final float STRAGE_SPEED = 18;

    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float currentTurnSpeed2 = 0;
    private float upwardsSpeed = 0;

    protected int classNumber;
    protected int level = 0;
    static protected int maxLevel = 1000;
    protected int health;
    protected int maxHealth;
    protected int armor;
    protected int mass;
    protected int endurance;
    protected int maxEndurance;
    protected int attack;
    protected int xp;
    
    
    
    private boolean isInAir = false;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);       
    }
    
    
    public int damagePlayer(int damage){
        this.health = this.health - damage;
        return this.health;
        
    }
    
    public float getCurrentTurnSpeed()
    {
        float totalTurnSpeed = currentTurnSpeed + currentTurnSpeed2;
        return totalTurnSpeed;
    }

    public void setCurrentTurnSpeed(float currentTurnSpeedx)
    {
        this.currentTurnSpeed2 = currentTurnSpeedx;
    }

    public void move(Terrain terrain) {
        float totalTurnSpeed = (currentTurnSpeed + currentTurnSpeed2);
        super.increaseRotation(0,(totalTurnSpeed)* DisplayManager.getFrameTimeSeconds() , 0);
        checkInputs();
              
        float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition(dx, 0, dz);
        
        upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
        super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
    
        
        float terrainHeight = terrain.getHeightOfTerrain(super.getPosition().x, super.getPosition().z);
        if (super.getPosition().y < terrainHeight) {
            upwardsSpeed = 0;
            isInAir = false;
            super.getPosition().y = terrainHeight;
        }
    }

    private void jump() {
      
        if (!isInAir) {
            this.upwardsSpeed = JUMP_POWER;
            isInAir = true;
            
           
        }
      
       
    }

    private void checkInputs() {
        
        float sx = (float) (Math.sin(Math.toRadians(super.getRotY()+90))* DisplayManager.getFrameTimeSeconds()* STRAGE_SPEED);
        float sz = (float) (Math.cos(Math.toRadians(super.getRotY()+90))* DisplayManager.getFrameTimeSeconds()* STRAGE_SPEED);
        
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            super.increasePosition(-sx, 0 ,-sz);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            super.increasePosition(sx, 0 ,sz);
        }
      
        
        if (Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            this.currentSpeed = RUN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            this.currentSpeed = -RUN_SPEED;
        } else {
            this.currentSpeed = 0;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_E) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            this.currentTurnSpeed = -TURN_SPEED;
        } else if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            this.currentTurnSpeed = TURN_SPEED;
        } else {
            this.currentTurnSpeed = 0;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_F)){
            this.upwardsSpeed = JUMP_POWER*2;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            jump();
        }
    }

    

}