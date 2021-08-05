package a.icarus.utils;

@SuppressWarnings("unused")
public class ConversionTool {
    public static int dp2px(float dp) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        int px = (int) (dp * density + 0.5f);
        return Math.max(px, 1);
    }

    public static float dp2pxF(float dp) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        return dp * density;
    }

    public static int px2dp(float px) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        int dp = (int) (px / density + 0.5f);
        return Math.max(dp, 1);
    }

    public static float px2dpF(float px) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    public static int sp2px(float sp) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().scaledDensity;
        int px = (int) (sp * density + 0.5f);
        return Math.max(px, 1);
    }

    public static float sp2pxF(float sp) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().scaledDensity;
        return sp * density;
    }

    public static int px2sp(float px) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().scaledDensity;
        int sp = (int) (px / density + 0.5f);
        return Math.max(sp, 1);
    }

    public static float px2spF(float px) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().scaledDensity;
        return px / density;
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
