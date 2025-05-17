package homework.lection19;

public class HelperClass {

    public static String rgbaToHex(String rgba) {
        rgba = rgba.replace("rgba(", "").replace(")", "");
        String[] parts = rgba.split(",");
        int r = Integer.parseInt(parts[0].trim());
        int g = Integer.parseInt(parts[1].trim());
        int b = Integer.parseInt(parts[2].trim());

        return String.format("#%02x%02x%02x", r, g, b);
    }
}
