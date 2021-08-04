package a.icarus.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

import a.icarus.utils.Logger;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

@SuppressWarnings("unused")
public class PermissionUtil {
    public static boolean checkPermission(Context context, String permission) {
        int result = context.getPackageManager().checkPermission(permission, context.getPackageName());
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkLocation(Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;

    }

    public static boolean checkGetUsage(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), context.getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    public static void toSetLocation(Object object, int code) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        if (object instanceof Activity) {
            Activity activity = (Activity) object;
            activity.startActivityForResult(intent, code);
            return;
        }
        if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            fragment.startActivityForResult(intent, code);
            return;
        }
        throw new RuntimeException("请使用Activity或者Fragment");

    }

    public static void toSetGetUsage(Object object, int code) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        if (object instanceof Activity) {
            Activity activity = (Activity) object;
            activity.startActivityForResult(intent, code);
            return;
        }
        if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            fragment.startActivityForResult(intent, code);
            return;
        }
        throw new RuntimeException("请使用Activity或者Fragment");

    }

    @SuppressLint("QueryPermissionsNeeded")
    public static boolean isIntentLegal(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    public static Intent getDetailsSettingsIntent(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return intent;
    }

    public static void requestAllPermission(Activity activity, int code) {
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;
            List<String> mPermissionList = new ArrayList<>();

            for (String requestedPermission : requestedPermissions) {
                if (ContextCompat.checkSelfPermission(activity, requestedPermission) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(requestedPermission);
                }
            }

            String[] permissionArray = new String[mPermissionList.size()];
            mPermissionList.toArray(permissionArray);

            if (mPermissionList.size() > 0) {
                ActivityCompat.requestPermissions(activity, permissionArray, code);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
