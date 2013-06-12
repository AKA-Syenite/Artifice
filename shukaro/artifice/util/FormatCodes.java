package shukaro.artifice.util;

/**
 * Enum of format codes used by the vanilla Minecraft font renderer
 *
 * @author MachineMuse
 */
public enum FormatCodes
{
    Black("§0"),
    DarkBlue("§1"),
    DarkGreen("§2"),
    DarkAqua("§3"),
    DarkRed("§4"),
    Purple("§5"),
    Gold("§6"),
    Grey("§7"),
    DarkGrey("§8"),
    Indigo("§9"),
    BrightGreen("§a"),
    Aqua("§b"),
    Red("§c"),
    Pink("§d"),
    Yellow("§e"),
    White("§f"),
    RandomChar("§k"),
    Bold("§l"),
    Strike("§m"),
    Underlined("§n"),
    Italic("§o"),
    Reset("§r");
    
    public String format;
    
    private FormatCodes(String s)
    {
    	this.format = s;
    }
}
