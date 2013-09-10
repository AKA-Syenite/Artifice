package shukaro.artifice.render.connectedtexture;

import shukaro.artifice.ArtificeConfig;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public enum ConnectedTexture
{
    MarblePaver("marble_paver"),
    MarbleAntipaver("marble_paver"),
    BasaltPaver("basalt_paver"),
    BasaltAntipaver("basalt_paver"),
    BasicFrame("frame_basic"),
    ReinforcedFrame("frame_reinforced"),
    IndustrialFrame("frame_industrial"),
    AdvancedFrame("frame_advanced");
    
    public String name;
    public Icon[] textureList;
    
    private ConnectedTexture(String s)
    {
        this.name = s;
        this.textureList = new Icon[47];
    }
}
