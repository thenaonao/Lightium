package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera
{

    private float distanceFromPlayer = 75;
    private float angleAroundPlayer = 0;

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    private Player player;

    public Camera(Player player)
    {
        this.player = player;
    }

    public void move()
    {
        calculateYaw();
        calculateZoom();
        calculatePitch();
        calculateAngleAroundPlayer();
        float horizontalDistance = calculateHorizontalDistance();
        float verticalDistance = calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance, verticalDistance);
        this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
    }

    private void calculateYaw()
    {

    }

    public Vector3f getPosition()
    {
        return position;
    }

    public float getPitch()
    {
        return pitch;
    }

    public float getYaw()
    {
        return yaw;
    }

    public float getRoll()
    {
        return roll;
    }

    private void calculateCameraPosition(float horizDistance, float verticDistance)
    {
        float theta = player.getRotY() + angleAroundPlayer;
        float offsetX = (float)(horizDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float)(horizDistance * Math.cos(Math.toRadians(theta)));
        position.x = player.getPosition().x - offsetX;
        position.z = player.getPosition().z - offsetZ;
        position.y = player.getPosition().y + verticDistance;
    }

    private float calculateHorizontalDistance()
    {
        float hD = (float)(distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
        if(hD < 0)
        {
            hD = 0;
        }
        return hD;
    }

    private float calculateVerticalDistance()
    {
        float vD = (float)(distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
        if(vD < 0)
            vD = 0;
        return vD+9 ;
    }

    private void calculateZoom()
    {
        float zoomLevel;
        zoomLevel = Mouse.getDWheel() * 0.1f;
        if(distanceFromPlayer > 150)
        {
            distanceFromPlayer--;
        }else if(distanceFromPlayer < 0){
            distanceFromPlayer++;
        }
        else
        {
            distanceFromPlayer -= zoomLevel;
        }
    }

    // 1= RIGHT || 0 = LEFT
    private void calculatePitch()
    {
        if(Mouse.isButtonDown(2))
        {
            float pitchChange = Mouse.getDY() * 0.1f;
            pitch -= pitchChange;
            if(pitch < -90)
                pitch = -90;
            else if(pitch > 90)
                pitch = 90;
        }
    }

    private void calculateAngleAroundPlayer()
    { 
      
        float angleChange = Mouse.getDX() * 0.3f;
        if(Mouse.isButtonDown(2))
        {

            angleAroundPlayer -= angleChange;
            if(angleAroundPlayer > 0)
            {

                player.setCurrentTurnSpeed(100);
            }
            else if(angleAroundPlayer < 0)
            {

                player.setCurrentTurnSpeed(-100);
            }

        }
        else
        {
            player.setCurrentTurnSpeed(0);

        }
        if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1))
        {
            angleAroundPlayer = 180;
        }else {
            angleAroundPlayer = 0;
        }
       
    }

    public void invertPitch()
    {
        this.pitch = -pitch;
    }

}