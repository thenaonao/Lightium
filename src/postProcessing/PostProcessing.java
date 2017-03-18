package postProcessing;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import gaussianBlur.HorizontalBlur;
import gaussianBlur.VerticalBlur;
import models.RawModel;
import renderEngine.Loader;

public class PostProcessing {
	
	private static final float[] POSITIONS = { -1, 1, -1, -1, 1, 1, 1, -1 };	
	private static RawModel quad;
	private static ContrastChanger contrastChanger;
	private static HorizontalBlur hblur;
	private static VerticalBlur vblur;
	private static HorizontalBlur hblur2;
    private static VerticalBlur vblur2;
	
	public static void init(Loader loader){
		quad = loader.loadToVAO(POSITIONS, 2);
		contrastChanger = new ContrastChanger();
		hblur = new HorizontalBlur(Display.getWidth()/8,Display.getHeight()/8);
		vblur = new VerticalBlur(Display.getWidth()/8,Display.getHeight()/8);
		hblur2 = new HorizontalBlur(Display.getWidth()/2,Display.getHeight()/2);
        vblur2 = new VerticalBlur(Display.getWidth()/2,Display.getHeight()/2);
	}
	
	public static void doPostProcessing(int colourTexture){
		start();
		//hblur.render(vblur2.getOutputTexture());
		//vblur.render(hblur.getOutputTexture());
		contrastChanger.render(colourTexture);
		end();
	}
	
	public static void cleanUp(){
	    contrastChanger.cleanUp();
	    hblur.cleanUp();
	    vblur.cleanUp();
	    hblur2.cleanUp();
        vblur2.cleanUp();
	}
	
	private static void start(){
		GL30.glBindVertexArray(quad.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	
	private static void end(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}


}
