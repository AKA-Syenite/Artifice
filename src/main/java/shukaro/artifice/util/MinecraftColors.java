package shukaro.artifice.util;

/**
 * Hex color codes
 * Taken from http://minecraft.gamepedia.com/Wool
 */
public enum MinecraftColors
{
    White("DDDDDD"),
    Orange("DB7D3E"),
    Magenta("B350BC"),
    LightBlue("6B8AC9"),
    Yellow("B1A627"),
    Lime("41AE38"),
    Pink("D08499"),
    Gray("404040"),
    LightGray("9AA1A1"),
    Cyan("2E6E89"),
    Purple("7E3DB5"),
    Blue("2E388D"),
    Brown("4F321F"),
    Green("35461B"),
    Red("963430"),
    Black("191616");

    public String hex;

    private MinecraftColors(String hex) { this.hex = hex; }

    public int toNormal()
    {
        return Integer.parseInt(this.hex, 16);
    }

    public int toAdjusted(int meta)
    {
        int r = Integer.parseInt(hex.substring(0, 2), 16) + (2 * meta);
        if (r > 255)
            r = 255;
        if (r < 0)
            r = 0;
        int g = Integer.parseInt(hex.substring(2, 4), 16) + (2 * meta);
        if (g > 255)
            g = 255;
        if (g < 0)
            g = 0;
        int b = Integer.parseInt(hex.substring(4, 6), 16) + (2 * meta);
        if (b > 255)
            b = 255;
        if (b < 0)
            b = 0;

        String rs = Integer.toHexString(r).length() == 1 ? "0" + Integer.toHexString(r) : Integer.toHexString(r);
        String gs = Integer.toHexString(g).length() == 1 ? "0" + Integer.toHexString(g) : Integer.toHexString(g);
        String bs = Integer.toHexString(b).length() == 1 ? "0" + Integer.toHexString(b) : Integer.toHexString(b);

        return Integer.parseInt(rs + gs + bs, 16);
    }
}
