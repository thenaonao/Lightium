package renderEngine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS_CAP = 60;

    private static final String TEXTURE = "/res/textures/gameIcon.png";
            
    private static final String TITLE = "[AProject] LIGHTIUM MMORPG";

    private static long lastFrameTime;
    private static float delta;
    
   

    public static void createDisplay() {

        ContextAttribs attribs = new ContextAttribs(3, 3).withForwardCompatible(true).withProfileCore(true);
        
        BufferedImage image = null;
        ByteBuffer[] list = new ByteBuffer[2]; 
        try
        {
         //   InputStreamReader isr = new InputStreamReader(Class.class.getResourceAsStream(TEXTURE));
            image = ImageIO.read(Class.class.getResourceAsStream(TEXTURE));
        }
        catch(IOException e)
        {
            e.printStackTrace();
         }
        list[0] = loadInstance(image,16);
        list[1] = loadInstance(image,32);
        
        
        try {
            Display.setDisplayMode(new  DisplayMode(WIDTH, HEIGHT));
            Display.create(new PixelFormat().withDepthBits(24), attribs);
            GL11.glEnable(GL13.GL_MULTISAMPLE);
            Display.setResizable(true);
           
          
            Display.setTitle(TITLE);
            Display.setIcon(list);
            Display.setVSyncEnabled(true);
           
           
            
        } catch (LWJGLException e){
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        lastFrameTime = getCurrentTime();
    }

    public static void updateDisplay() {
        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;
       
    }

    
    public static float getFrameTimeSeconds() {
        return delta;
    }

    public static void closeDisplay() {
        Display.destroy();
    }

    private static long getCurrentTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }
    
    private static ByteBuffer loadInstance(BufferedImage image, int dimension)
    {
        BufferedImage scaledIcon = new BufferedImage(dimension, dimension,
                BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics2D g = scaledIcon.createGraphics();
        double ratio = getIconRatio(image, scaledIcon);
        double width = image.getWidth()* ratio;
        double height = image.getHeight()* ratio;
        g.drawImage(image, (int) ((scaledIcon.getWidth() - width) / 2),
                (int) ((scaledIcon.getHeight() - height) / 2), (int) (width),
                (int) (height), null);
        g.dispose();
 
        return convertToByteBuffer(scaledIcon);
    }
    public static ByteBuffer convertToByteBuffer(BufferedImage image)
    {
        byte[] buffer = new byte[image.getWidth() * image.getHeight() * 4];
        int counter = 0;
        for (int i = 0; i < image.getHeight(); i++)
            for (int j = 0; j < image.getWidth(); j++)
            {
                int colorSpace = image.getRGB(j, i);
                buffer[counter + 0] = (byte) ((colorSpace << 8) >> 24);
                buffer[counter + 1] = (byte) ((colorSpace << 16) >> 24);
                buffer[counter + 2] = (byte) ((colorSpace << 24) >> 24);
                buffer[counter + 3] = (byte) (colorSpace >> 24);
                counter += 4;
            }
        return ByteBuffer.wrap(buffer);
    }
    private static double getIconRatio(BufferedImage src, BufferedImage icon)
    {
        double ratio = 1;
        if (src.getWidth() > icon.getWidth())
            ratio = (double) (icon.getWidth()) / src.getWidth();
        else
            ratio = (int) (icon.getWidth() / src.getWidth());
        if (src.getHeight() > icon.getHeight())
        {
            double r2 = (double) (icon.getHeight()) / src.getHeight();
            if (r2 < ratio)
                ratio = r2;
        }
        else
        {
            double r2 = (int) (icon.getHeight() / src.getHeight());
            if (r2 < ratio)
                ratio = r2;
        }
        return ratio;
    }
}