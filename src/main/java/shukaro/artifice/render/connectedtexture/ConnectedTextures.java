package shukaro.artifice.render.connectedtexture;

import net.minecraft.util.IIcon;
import shukaro.artifice.render.connectedtexture.schemes.SolidConnectedTexture;
import shukaro.artifice.render.connectedtexture.schemes.TransparentConnectedTexture;

public enum ConnectedTextures
{
    MarblePaver("marble_paver", true),
    MarbleAntipaver("marble_antipaver", true),
    BasaltPaver("basalt_paver", true),
    BasaltAntipaver("basalt_antipaver", true),
    BasicFrame("frame_basic", true),
    ReinforcedFrame("frame_reinforced", true),
    IndustrialFrame("frame_industrial", true),
    AdvancedFrame("frame_advanced", true),
    BasicScaffold("scaffold_basic", true),
    ReinforcedScaffold("scaffold_reinforced", true),
    IndustrialScaffold("scaffold_industrial", true),
    AdvancedScaffold("scaffold_advanced", true),
    BasicBlastWall("blastwall_basic", true),
    ReinforcedBlastWall("blastwall_reinforced", true),
    IndustrialBlastWall("blastwall_industrial", true),
    AdvancedBlastWall("blastwall_advanced", true),
    BasicGlassWall("glasswall_basic", false),
    ReinforcedGlassWall("glasswall_reinforced", false),
    IndustrialGlassWall("glasswall_industrial", false),
    AdvancedGlassWall("glasswall_advanced", false);

    public String name;
    public IIcon[] textureList;
    public ConnectedTextureBase renderer;

    private ConnectedTextures(String s, Boolean solid)
    {
        this.name = s;
        this.textureList = new IIcon[47];
        if (solid)
            this.renderer = new SolidConnectedTexture(this);
        else
            this.renderer = new TransparentConnectedTexture(this);
    }
}
