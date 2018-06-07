package me.weishu.reflection;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;

/**
 * @author weishu
 * @date 2018/6/7.
 */

public class Reflection {

    static {
        System.loadLibrary("free-reflection");
    }

    private static native int unsealNative(int targetSdkVersion);

    private static int UNKNOWN = -9999;

    private static int unsealed = UNKNOWN;

    public static int unseal(Context context) {
        if (Build.VERSION.SDK_INT < 28) {
            // Below Android P, ignore
            return 0;
        }

        if (context == null) {
            return -10;
        }

        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int targetSdkVersion = applicationInfo.targetSdkVersion;

        synchronized (Reflection.class) {
            if (unsealed == UNKNOWN) {
                unsealed = unsealNative(targetSdkVersion);
                if (unsealed >= 0) {
                    // TODO: Keep sync with ApplicationInfo.ApiEnforcementPolicy

                }
            }
        }
        return unsealed;
    }
}
