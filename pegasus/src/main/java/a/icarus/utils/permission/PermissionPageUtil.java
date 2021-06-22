package a.icarus.utils.permission;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


public class PermissionPageUtil {

    private static final String sManufacturer = Build.MANUFACTURER;
    private static final String MANUFACTURER_HUAWEI = "Huawei";
    private static final String MANUFACTURER_XIAOMI = "Xiaomi";
    private static final String MANUFACTURER_OPPO = "OPPO";
    private static final String MANUFACTURER_VIVO = "vivo";
    private static final String MANUFACTURER_MEIZU = "Meizu";


    private static Intent getHuaWei(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity"));
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }

    private static Intent getXiaoMi(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        intent.setPackage("com.miui.securitycenter");
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }

    private static Intent getOPPO(Context context) {
        Intent intent = new Intent();
        intent.putExtra("packageName", context.getPackageName());
        intent.setComponent(new ComponentName("com.coloros.securitypermission", "com.coloros.securitypermission.permission.PermissionGroupsActivity"));
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        intent.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.PermissionManagerActivity"));
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }

    private static Intent getVIVO(Context context) {
        Intent intent;
        intent = new Intent();
        intent.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
        intent.putExtra("packagename", context.getPackageName());
        intent.setAction("secure.intent.action.softPermissionDetail");
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        intent = new Intent();
        intent.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity");
        intent.putExtra("packagename", context.getPackageName());
        intent.putExtra("tabId", "1");
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }

    private static Intent getMeiZu(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.putExtra("packageName", context.getPackageName());
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        intent.setComponent(new ComponentName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity"));
        if (PermissionUtil.isIntentLegal(context, intent))
            return intent;
        return null;
    }

    public static void toPermissionPage(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = getPermissionPageIntent(context);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Intent getPermissionPageIntent(Context context) {
        Intent intent;
        if (MANUFACTURER_HUAWEI.equalsIgnoreCase(sManufacturer)) {
            intent = getHuaWei(context);
        } else if (MANUFACTURER_XIAOMI.equalsIgnoreCase(sManufacturer)) {
            intent = getXiaoMi(context);
        } else if (MANUFACTURER_OPPO.equalsIgnoreCase(sManufacturer)) {
            intent = getOPPO(context);
        } else if (MANUFACTURER_VIVO.equalsIgnoreCase(sManufacturer)) {
            intent = getVIVO(context);
        } else if (MANUFACTURER_MEIZU.equalsIgnoreCase(sManufacturer)) {
            intent = getMeiZu(context);
        } else {
            intent = PermissionUtil.getDetailsSettingsIntent(context);
        }
        if (intent == null) {
            intent = PermissionUtil.getDetailsSettingsIntent(context);
        }
        return intent;
    }

}
