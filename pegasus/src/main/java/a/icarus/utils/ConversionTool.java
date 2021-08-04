package a.icarus.utils;

@SuppressWarnings("unused")
public class ConversionTool {
    public static int dp2px(float dp) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static float px2dp(int px) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    public static int sp2px(float sp) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * density + 0.5f);
    }

    public static float px2sp(int px) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().scaledDensity;
        return px / density;
    }

    public static int float2int(float f) {
        return (int) (f + 0.5f);
    }

    public static String bytes2HexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            String s = Integer.toHexString(b & 0xFF);
            if (s.length() < 2) {
                builder.append("0");
            }
            builder.append(s);
        }
        return builder.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }

}
