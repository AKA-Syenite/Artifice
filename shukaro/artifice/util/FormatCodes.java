package shukaro.artifice.util;

/**
 * Enum of format codes used by the vanilla Minecraft font renderer
 *
 * @author MachineMuse
 */
public enum FormatCodes
{
    Black("\u00A70"),
    DarkBlue("\u00A71"),
    DarkGreen("\u00A72"),
    DarkAqua("\u00A73"),
    DarkRed("\u00A74"),
    Purple("\u00A75"),
    Gold("\u00A76"),
    Grey("\u00A77"),
    DarkGrey("\u00A78"),
    Indigo("\u00A79"),
    BrightGreen("\u00A7a"),
    Aqua("\u00A7b"),
    Red("\u00A7c"),
    Pink("\u00A7d"),
    Yellow("\u00A7e"),
    White("\u00A7f"),
    RandomChar("\u00A7k"),
    Bold("\u00A7l"),
    Strike("\u00A7m"),
    Underlined("\u00A7n"),
    Italic("\u00A7o"),
    Reset("\u00A7r");
    
    public String format;
    
    private FormatCodes(String s)
    {
    	this.format = s;
    }
}
