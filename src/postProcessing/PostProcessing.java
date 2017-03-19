package postProcessing;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import bloom.BrightFilter;
import bloom.CombineFilter;
import gaussianBlur.HorizontalBlur;
import gaussianBlur.VerticalBlur;
import models.RawModel;
import renderEngine.Loader;

public class PostProcessing {
	
	private static final float[] POSITIONS = { -1, 1, -1, -1, 1, 1, 1, -1 };	
	private static RawModel quad;
	private static ContrastChanger contrastChanger;
	private static BrightFilter brightFilter;
	private static HorizontalBlur hblur;
	private static VerticalBlur vblur;
	private static CombineFilter combineFilter;

	
	public static void init(Loader loader){
		quad = loader.loadToVAO(POSITIONS, 2);
		contrastChanger = new ContrastChanger();
		brightFilter = new BrightFilter(Display.getHeight()/2, Display.getHeight()/2);
		hblur = new HorizontalBlur(Display.getWidth()/5,Display.getHeight()/5);
		vblur = new VerticalBlur(Display.getWidth()/5,Display.getHeight()/5);
		combineFilter = new CombineFilter();
	}
	
	public static void doPostProcessing(int colourTexture, int brightTexture){
		start();
		//brightFilter.render(colourTexture);
		hblur.render(brightTexture);
		vblur.render(hblur.getOutputTexture());
		combineFilter.render(colourTexture, vblur.getOutputTexture());
		end();
	}
	
	public static void cleanUp(){
	    contrastChanger.cleanUp();
	    brightFilter.cleanUp();
	    hblur.cleanUp();
	    vblur.cleanUp();
	    combineFilter.cleanUp();
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
