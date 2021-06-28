package a.icarus.utils;

@SuppressWarnings("unused")
public class ConversionTool {
    public static int dp2px(float dp) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2dp(float px) {
        float density = Icarus.getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
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

}
