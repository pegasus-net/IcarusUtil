package a.icarus.utils.permission;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BackgroundPermission {
    private final Context context;
    private static final String MANUFACTURER_HUAWEI = "Huawei";
    private static final String MANUFACTURER_MEIZU = "Meizu";
    private static final String MANUFACTURER_XIAOMI = "Xiaomi";
    private static final String MANUFACTURER_OPPO = "OPPO";
    private static final String MANUFACTURER_VIVO = "vivo";

    public BackgroundPermission(Context context) {
        this.context = context;
    }

    public void start() {
        Intent intent = null;
        switch (Build.MANUFACTURER) {
            case MANUFACTURER_HUAWEI:
                intent = getHuaWei();
                break;
            case MANUFACTURER_MEIZU:
                intent = getMeiZu();
                break;
            case MANUFACTURER_XIAOMI:
                intent = getXiaoMi();
                break;
            case MANUFACTURER_OPPO:
                intent = getOPPO();
                break;
            case MANUFACTURER_VIVO:
                intent = getVIVO();
                break;
            default:
                getDefault();
                break;
        }
        if (intent == null) {
            intent = getDefault();
        }
        context.startActivity(intent);
    }

    private Intent getDefault() {
        return PermissionUtil.getDetailsSettingsIntent(context);

    }

    private Intent getIntent(ComponentName componentName) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        return intent;
    }


    private Intent getHuaWei() {
        Intent intent;
        ComponentName componentName = new ComponentName("com.huawei.systemmanager",
                "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        intent = getIntent(componentName);
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        componentName = new ComponentName("com.huawei.systemmanager",
                "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        intent = getIntent(componentName);
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        componentName = new ComponentName("com.huawei.systemmanager",
                "com.huawei.systemmanager.optimize.process.ProtectActivity");
        intent = getIntent(componentName);
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }

    private Intent getXiaoMi() {
        Intent intent;
        ComponentName componentName = new ComponentName("com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity");
        intent = getIntent(componentName);
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }

    private Intent getOPPO() {
        Intent intent;

        ComponentName componentName = ComponentName.unflattenFromString("com.coloros.safecenter" +
                "/.startupapp.StartupAppListActivity");
        intent = getIntent(componentName);
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        componentName = ComponentName.unflattenFromString("com.oppo.safe" +
                "/.permission.startup.StartupAppListActivity");
        intent = getIntent(componentName);
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }

    private Intent getVIVO() {
        Intent intent;
        ComponentName componentName = ComponentName.unflattenFromString("com.iqoo.secure" +
                "/.safeguard.PurviewTabActivity");
        intent = getIntent(componentName);
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        intent = PermissionPageUtil.getPermissionPageIntent(context);
        return intent;

    }

    private Intent getMeiZu() {
        Intent intent;
        ComponentName componentName = ComponentName.unflattenFromString("com.meizu.safe" +
                "/.permission.PermissionMainActivity");
        intent = getIntent(componentName);
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }
}
