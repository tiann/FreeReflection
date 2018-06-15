package me.weishu.freereflection.app;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.weishu.reflection.Reflection;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "version : " + Build.VERSION.SDK_INT + " fingerprint: " + Build.FINGERPRINT);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Method setHiddenApiEnforcementPolicy = ApplicationInfo.class.getDeclaredMethod("setHiddenApiEnforcementPolicy", int.class);
                    Log.i(TAG, "setHiddenApiEnforcementPolicy:" + setHiddenApiEnforcementPolicy);

                    Class<?> assetManagerClass = AssetManager.class;
                    Method ensureStringBlocks = assetManagerClass.getDeclaredMethod("ensureStringBlocks");
                    ensureStringBlocks.setAccessible(true);
                    ensureStringBlocks.invoke(getAssets());
                    Log.i(TAG, "call success!!");
                } catch (Throwable e) {
                    Log.e(TAG, "error:", e);
                    toast("error: " + e);
                }
            }
        });

        findViewById(R.id.unreal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ret = Reflection.unseal(MainActivity.this);
                toast("unseal result: " + ret);
            }
        });
    }

    private void toast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.i(TAG, msg);
    }

    private void test() throws Throwable {
        Class<?> activityThread = Class.forName("android.app.ActivityThread");
        Class<?> hclass = Class.forName("android.app.ActivityThread$H");
        Field[] declaredFields = hclass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Log.i(TAG, "declareField: " + declaredField);
        }
    }
}
