package skybox;

import java.util.Calendar;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import models.RawModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;

public class SkyboxRenderer {

	private static final float SIZE = 750f;

	private static final float[] VERTICES = { //
			-SIZE, SIZE, -SIZE, //
			-SIZE, -SIZE, -SIZE, //
			SIZE, -SIZE, -SIZE, //
			SIZE, -SIZE, -SIZE, //
			SIZE, SIZE, -SIZE, //
			-SIZE, SIZE, -SIZE, //

			-SIZE, -SIZE, SIZE, //
			-SIZE, -SIZE, -SIZE, //
			-SIZE, SIZE, -SIZE, //
			-SIZE, SIZE, -SIZE, //
			-SIZE, SIZE, SIZE, //
			-SIZE, -SIZE, SIZE, //

			SIZE, -SIZE, -SIZE, //
			SIZE, -SIZE, SIZE, //
			SIZE, SIZE, SIZE, //
			SIZE, SIZE, SIZE, //
			SIZE, SIZE, -SIZE, //
			SIZE, -SIZE, -SIZE, //

			-SIZE, -SIZE, SIZE, //
			-SIZE, SIZE, SIZE, //
			SIZE, SIZE, SIZE, //
			SIZE, SIZE, SIZE, //
			SIZE, -SIZE, SIZE, //
			-SIZE, -SIZE, SIZE, //

			-SIZE, SIZE, -SIZE, //
			SIZE, SIZE, -SIZE, //
			SIZE, SIZE, SIZE, //
			SIZE, SIZE, SIZE, //
			-SIZE, SIZE, SIZE, //
			-SIZE, SIZE, -SIZE, //

			-SIZE, -SIZE, -SIZE, //
			-SIZE, -SIZE, SIZE, //
			SIZE, -SIZE, -SIZE, //
			SIZE, -SIZE, -SIZE, //
			-SIZE, -SIZE, SIZE, //
			SIZE, -SIZE, SIZE //

	};

	private static String[] TEXTURE_FILES = { "textures/right", "textures/left", "textures/top", "textures/bottom", "textures/back", "textures/front" };
	private static String[] NIGHT_TEXTURE_FILES = { "textures/nightRight", "textures/nightLeft", "textures/nightTop", "textures/nightBottom", "textures/nightBack", "textures/nightFront" };

	
	private RawModel cube;
	private int texture;
	private int nightTexture;
	private SkyboxShader shader;
	private float time = 0;

	public SkyboxRenderer(Loader loader, Matrix4f projectionMatrix) {
		this.cube = loader.loadToVAO(VERTICES, 3);
		this.texture = loader.loadCubeMap(TEXTURE_FILES);
		this.nightTexture = loader.loadCubeMap(NIGHT_TEXTURE_FILES);
		this.shader = new SkyboxShader();
		shader.start();
		shader.connectTextureUnits();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void render(Camera camera, float r, float g, float b) {
		shader.start();
		shader.loadViewMatrix(camera);
		shader.loadFogColour(r, g, b);
		GL30.glBindVertexArray(cube.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		bindTextures();
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cube.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}

	private void bindTextures() {
	    /*
	     * Get Time irl
	     */
	    Calendar machineTime = Calendar.getInstance();
        int hour = machineTime.get(Calendar.HOUR_OF_DAY);
        int minute = machineTime.get(Calendar.MINUTE);
        int second = machineTime.get(Calendar.SECOND);
        //System.out.println(hour + ":" + minute+ ":" + second);
        
		/*time += DisplayManager.getFrameTimeSeconds();
		time %= 24000;*/
		int texture1;
		int texture2;
		float blendFactor;
		/*if (time >= 0 && time < 5000) {
			texture1 = nightTexture;
			texture2 = nightTexture;
			blendFactor = (time - 0) / (5000 - 0);
		} else if (time >= 5000 && time < 8000) {
			texture1 = nightTexture;
			texture2 = texture;
			blendFactor = (time - 5000) / (8000 - 5000);
		} else if (time >= 8000 && time < 21000) {
			texture1 = texture;
			texture2 = texture;
			blendFactor = (time - 8000) / (21000 - 8000);
		} else {
			texture1 = texture;
			texture2 = nightTexture;
			blendFactor = (time - 21000) / (24000 - 21000);
		}*/
		if (hour >= 21 && hour < 6) {
            texture1 = nightTexture;
            texture2 = nightTexture;
            blendFactor = (hour - 21) / (6 - 21);
        } else if (hour >= 6 && hour < 9) {
            texture1 = nightTexture;
            texture2 = texture;
            blendFactor = (hour - 6) / (9 - 6);
        } else if (hour >= 9 && hour < 17) {
            texture1 = texture;
            texture2 = texture;
            blendFactor = (hour - 9) / (17 - 9);
        } else {
            texture1 = texture;
            texture2 = nightTexture;
            blendFactor = (hour - 17) / (21 - 17);
        }

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture1);
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture2);
		shader.loadBlendFactor(blendFactor);
		
	}

}
