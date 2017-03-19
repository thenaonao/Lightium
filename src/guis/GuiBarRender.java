package guis;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import models.RawModel;
import renderEngine.Loader;
import toolbox.Maths;

public class GuiBarRender {

    private final RawModel quad;
    private GuiShader shader;

    public GuiBarRender(Loader loader,Vector2f vec1,int w, int h) { 
        float[] positions = {
                //-1, 1, -1, -1, 0.3f, 1, 0.3f, -1 
  //new Vector2f(18,3), new Vector2f(232,43), 215, 41);
                //Vector2f(18,101), 21,20);
              (vec1.x/256)-1, (256-vec1.y)/256,
              (vec1.x/256)-1, (256-(vec1.y+h))/256,
              (vec1.x+w)/256, (256-vec1.y)/256,
              (vec1.x+w)/256, (256-(vec1.y+h))/256
                
        };
        quad = loader.loadToVAO(positions,2);  
        shader = new GuiShader();
    }

    public void render(List<GuiTexture> guis) {
        shader.start();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        for (GuiTexture gui : guis) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
            Matrix4f matrix = Maths.createTransformationMatrix(gui.getPosition(), gui.getScale());
            shader.loadTransformation(matrix);
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    public void cleanUp() {
        shader.cleanUp();
    }

}