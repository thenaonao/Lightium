package textures;

public class TerrainTexturePack
{
    private TerrainTexture backgroungTexture;
    
    private TerrainTexture rTexture;
    private TerrainTexture gTexture;
    private TerrainTexture bTexture;
    public TerrainTexturePack(TerrainTexture backgroungTexture, TerrainTexture rTexture, TerrainTexture gTexture, TerrainTexture bTexture)
    {
        this.backgroungTexture = backgroungTexture;
        this.rTexture = rTexture;
        this.gTexture = gTexture;
        this.bTexture = bTexture;
    }
    public TerrainTexture getBackgroungTexture()
    {
        return backgroungTexture;
    }
    public TerrainTexture getrTexture()
    {
        return rTexture;
    }
    public TerrainTexture getgTexture()
    {
        return gTexture;
    }
    public TerrainTexture getbTexture()
    {
        return bTexture;
    }

    
    
}
