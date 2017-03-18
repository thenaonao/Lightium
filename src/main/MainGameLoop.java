package main;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import audio.AudioMaster;
import collision.AABB;
import collision.IntersectData;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.players.Swordman;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guis.GuiBarRender;
import guis.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import normalMappingObjConverter.NormalMappedObjLoader;
import objConverter.OBJFileLoader;
import particles.ParticleMaster;
import particles.ParticleSystem;
import particles.ParticleTexture;
import postProcessing.Fbo;
import postProcessing.PostProcessing;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.MousePicker;
import toolbox.Vector3f_;
import water.WaterFrameBuffers;
import water.WaterRenderer;
import water.WaterShader;
import water.WaterTile;
 
public class MainGameLoop {
 
   
    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        AudioMaster.init();
        AudioMaster.setListenerData(0, 0, 0); 
        AL10.alDistanceModel(AL11.AL_LINEAR_DISTANCE_CLAMPED);
        RawModel bunnyModel = OBJFileLoader.loadOBJ("textures/test2", loader);
        TexturedModel stanfordBunny = new TexturedModel(bunnyModel, new ModelTexture(
                loader.loadTexture("textures/kirito")));
        Swordman player = new Swordman(stanfordBunny, new Vector3f(75, 5, -75), 0, 100, 0, 0.6f, 100);
      
        Camera camera = new Camera(player);
        MasterRenderer renderer = new MasterRenderer(loader,camera);
        TextMaster.init(loader);
        ParticleMaster.init(loader, renderer.getProjectionMatrix());
        
        List<Terrain> terrains = new ArrayList<Terrain>();
        List<Entity> entities = new ArrayList<Entity>();
        List<GuiTexture> guiTextures = new ArrayList<GuiTexture>();
        List<Entity> normalMapEntities = new ArrayList<Entity>();
        List<Light> lights = new ArrayList<Light>();
        List<WaterTile> waters = new ArrayList<WaterTile>();
        
      
        entities.add(player);
        
        RawModel tree = OBJFileLoader.loadOBJ("textures/pine",loader);
        TexturedModel standTree = new TexturedModel(tree, new ModelTexture(loader.loadTexture("textures/pine")));
        
        //RENDER OF TEXT
        
        FontType font = new FontType(loader.loadTexture("textures/saoWelcome"), "saoWelcome");
        GUIText text = new GUIText("LV:" + player.getLevelString() , 0.7f, font, new Vector2f(0.088f, 0.066f), 0.25f, true);
        text.setColour(1f, 1f, 1f);
        GUIText health = new GUIText(player.getHealthString(), 0.7f, font , new Vector2f(0.007f, 0.066f), 0.3f, true);
        GUIText maxHealth = new GUIText(player.getMaxHealthString(), 0.7f, font , new Vector2f(0.034f, 0.066f), 0.3f, true);
        
        health.setColour(1f, 1f, 1f);
        maxHealth.setColour(1f, 1f, 1f);
        
        //END OF RENDER TEXT
        
        // GUI
        
        GuiTexture gui = new GuiTexture(loader.loadTexture("textures/NewPlayerViewHealthOverlay"), new Vector2f(-0.76f, 0.65f), new Vector2f(0.26f,0.26f));
        guiTextures.add(gui);
      /*  GuiTexture gui2 = new GuiTexture(loader.loadTexture("textures/NewPlayerViewHealthOverlay"), new Vector2f(0,0), new Vector2f(0.5f,0.5f));
        guiTextures.add(gui2); */
        //END OF GUI
        
        //CAMERA****************
      
        
        // *********TERRAIN TEXTURE STUFF**********
         
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("textures/grassy2"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("textures/mud"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("textures/grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("textures/path"));
 
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture,
                gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("textures/blendMap"));
 
        // *****************************************
 
        
        ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("textures/fern"));
        fernTextureAtlas.setNumberOfRows(2);
 
        TexturedModel fern = new TexturedModel(OBJFileLoader.loadOBJ("textures/fern", loader),
                fernTextureAtlas);
 
        
 
        fern.getTexture().setHasTransparency(true);
 
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "textures/heightmap");
       
        terrains.add(terrain);
 
        TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("textures/lamp", loader),
                new ModelTexture(loader.loadTexture("textures/lamp")));
        lamp.getTexture().setUseFakeLighting(true);
 
        
          
        //******************NORMAL MAP MODELS************************
         
        TexturedModel barrelModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("textures/barrel", loader),
                new ModelTexture(loader.loadTexture("textures/barrel")));
        barrelModel.getTexture().setNormalMap(loader.loadTexture("textures/barrelNormal"));
        barrelModel.getTexture().setShineDamper(10);
        barrelModel.getTexture().setReflectivity(0.5f);
         
        TexturedModel crateModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("textures/crate", loader),
                new ModelTexture(loader.loadTexture("textures/crate")));
        crateModel.getTexture().setNormalMap(loader.loadTexture("textures/crateNormal"));
        crateModel.getTexture().setShineDamper(10);
        crateModel.getTexture().setReflectivity(0.5f);
         
        TexturedModel boulderModel = new TexturedModel(NormalMappedObjLoader.loadOBJ("textures/boulder", loader),
                new ModelTexture(loader.loadTexture("textures/boulder")));
        boulderModel.getTexture().setNormalMap(loader.loadTexture("textures/boulderNormal"));
        boulderModel.getTexture().setShineDamper(10);
        boulderModel.getTexture().setReflectivity(0.5f);
         
         
        //************ENTITIES*******************
         
        Entity entity = new Entity(barrelModel, new Vector3f(75, 10, -75), 0, 0, 0, 1f);
        Entity entity2 = new Entity(boulderModel, new Vector3f(85, 10, -75), 0, 0, 0, 1f);
        Entity entity3 = new Entity(crateModel, new Vector3f(65, 10, -75), 0, 0, 0, 0.04f);
        normalMapEntities.add(entity);
        normalMapEntities.add(entity2);
        normalMapEntities.add(entity3);
         
        Random random = new Random(5666778);
        for (int i = 0; i < 60; i++) {
            if (i % 3 == 0) {
                float x = random.nextFloat() * 150;
                float z = random.nextFloat() * -150;
                if ((x > 50 && x < 100) || (z < -50 && z > -100)) {
                } else {
                    float y = terrain.getHeightOfTerrain(x, z);
 
                    entities.add(new Entity(fern, 3, new Vector3f(x, y, z), 0,
                            random.nextFloat() * 360, 0, 0.9f));
                }
            }
            if (i % 2 == 0) {
 
                float x = random.nextFloat() * 150;
                float z = random.nextFloat() * -150;
                if ((x > 200 && x < 400) || (z < -200 && z > -400)) {
 
                } else {
                    float y = terrain.getHeightOfTerrain(x, z);
                  
                    entities.add(new Entity(standTree,3,new Vector3f(x,y,z),0, random.nextFloat()* 360,0, 2f));
                }
            }
        }
       
         
        //*******************OTHER SETUP***************
 
       
        //Light sun = new Light(new Vector3f(10000, 10000, -10000), new Vector3f(1.3f, 1.3f, 1.3f));
        Light sun = new Light(new Vector3f(0, 0, 0), new Vector3f(1.3f, 1.3f, 1.3f));
        lights.add(sun);
 
        
 
        
 
       
         
        GuiBarRender guiRenderer = new GuiBarRender(loader, new Vector2f(18,3), 215, 85);
        MousePicker picker = new MousePicker(camera, renderer.getProjectionMatrix(), terrain);
     
        //**********Water Renderer Set-up************************
         
        WaterFrameBuffers buffers = new WaterFrameBuffers();
        WaterShader waterShader = new WaterShader();
        WaterRenderer waterRenderer = new WaterRenderer(loader, waterShader, renderer.getProjectionMatrix(), buffers);
        
        WaterTile water = new WaterTile(0, 0, 20);
        waters.add(water);
         
        
        
        ParticleTexture particleTexture = new ParticleTexture(loader.loadTexture("textures/spark"),4);
     
        ParticleSystem particleSystem = new ParticleSystem(particleTexture,300,25,0.3f,4, 1);
        
        Fbo fbo = new Fbo(Display.getWidth(), Display.getHeight(), Fbo.DEPTH_RENDER_BUFFER);
        PostProcessing.init(loader);
        
        //
        AABB aabb1  = new AABB(new Vector3f_(0.0f, 0.0f, 0.0f), new Vector3f_(1.0f, 1.0f, 1.0f));
        AABB aabb2  = new AABB(new Vector3f_(1.0f, 1.0f, 1.0f),new  Vector3f_(2.0f, 2.0f, 2.0f));
        AABB aabb3  = new AABB(new Vector3f_(1.0f, 0.0f, 0.0f),new  Vector3f_(2.0f, 1.0f, 1.0f));
        AABB aabb4  = new AABB(new Vector3f_(0.0f, 0.0f, -2.0f),new Vector3f_(1.0f, 1.0f, -1.0f));
        AABB aabb5  = new AABB(new Vector3f_(0.0f, 0.5f, 0.0f), new Vector3f_(1.0f, 1.5f, 1.0f));
        
        IntersectData aabb1Intersectaabb2 = aabb1.IntersectAABB(aabb2);
        IntersectData aabb1Intersectaabb3 = aabb1.IntersectAABB(aabb3);
        IntersectData aabb1Intersectaabb4 = aabb1.IntersectAABB(aabb4);
        IntersectData aabb1Intersectaabb5 = aabb1.IntersectAABB(aabb5);
        //
        
        
        final float hypo = 10000f;
        float hour,min,sec,houredminute;
      
        //****************Game Loop Below*********************
 
        while (!Display.isCloseRequested()) {
          /*  number++;
             text.setString(number+"");    UpdateStringGUI , IT WORKS!
             text.update(); */
            Calendar time = Calendar.getInstance();
             hour = time.get(Calendar.HOUR_OF_DAY);
             min = time.get(Calendar.MINUTE);
             sec = time.get(Calendar.SECOND);
             houredminute = min +hour*60;
            /* Sun System
             *
             * 1440 = 24H NIGHT
             * 720 = 12H DAY
             */
            if(houredminute > 1440)houredminute = 0;
            int horizontal = (int)(hypo*hypo*2*Math.PI * Math.sin(Math.toRadians((720+houredminute)*0.25f)));
            int vertical = (int)(hypo*hypo*2*Math.PI * Math.cos(Math.toRadians((720+houredminute)*0.25f)));
            sun.setPosition(new Vector3f(-20,vertical,horizontal));
        //    System.out.println(houredminute);
           
            //CollisionLib.testAABBAABB(player.getBox(), entity.getBox());
            
            player.move(terrain);
            player.update();
            picker.update();
            camera.move();
            particleSystem.generateParticles(sun.getPosition());            
            ParticleMaster.update(camera);
            renderer.renderShadowMap(entities, sun);   
           
            GL11.glEnable(GL30.GL_CLIP_DISTANCE0);
             
            //render reflection texture
            buffers.bindReflectionFrameBuffer();
            float distance = 2 * (camera.getPosition().y - water.getHeight());
            camera.getPosition().y -= distance;
            camera.invertPitch();
            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, 1, 0, -water.getHeight()+1));
            camera.getPosition().y += distance;
            camera.invertPitch();
             
            //render refraction texture
            buffers.bindRefractionFrameBuffer();
            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, water.getHeight()));
             
            //render to screen
            GL11.glDisable(GL30.GL_CLIP_DISTANCE0);
            buffers.unbindCurrentFrameBuffer(); 
            
            //fbo.bindFrameBuffer();
            renderer.renderScene(entities, normalMapEntities, terrains, lights, camera, new Vector4f(0, -1, 0, 100000));    
            waterRenderer.render(waters, camera, sun);
            ParticleMaster.renderParticles(camera);
          //  fbo.unbindFrameBuffer();
           // PostProcessing.doPostProcessing(fbo.getColourTexture());
            
            guiRenderer.render(guiTextures);
            TextMaster.render();
         
            DisplayManager.updateDisplay();
            
          
          //  System.out.println(DisplayManager.getFrameTimeSeconds());
        }   
 
        //*********Clean Up Below**************
        
        PostProcessing.cleanUp();
        fbo.cleanUp();
        AudioMaster.cleanUp();
        ParticleMaster.cleanUp();
        TextMaster.cleanUp();
        buffers.cleanUp();
        waterShader.cleanUp();
        guiRenderer.cleanUp();
        guiRenderer.render(guiTextures);
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
 
}