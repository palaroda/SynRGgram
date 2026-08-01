package org.telegram.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;

public class LauncherIconController {
    public static void tryFixLauncherIconIfNeeded() {
        for (LauncherIcon icon : LauncherIcon.values()) {
            if (isEnabled(icon)) {
                return;
            }
        }

        setIcon(LauncherIcon.DEFAULT);
    }

    public static boolean isEnabled(LauncherIcon icon) {
        Context ctx = ApplicationLoader.applicationContext;
        int i = ctx.getPackageManager().getComponentEnabledSetting(icon.getComponentName(ctx));
        return i == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                || i == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT && icon == LauncherIcon.DEFAULT;
    }

    public static void setIcon(LauncherIcon icon) {
        Context ctx = ApplicationLoader.applicationContext;
        PackageManager pm = ctx.getPackageManager();
        for (LauncherIcon i : LauncherIcon.values()) {
            pm.setComponentEnabledSetting(
                    i.getComponentName(ctx),
                    i == icon
                            ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                            : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
            );
        }
    }

    public enum LauncherIcon {
        DEFAULT(
                "DefaultIcon",
                android.R.color.white,
                R.mipmap.icon_foreground_synrg_sa,
                R.string.AppIconDefault
        ),
        VINTAGE(
                "VintageIcon",
                android.R.color.white,
                R.mipmap.icon_6_foreground_synrg_sa,
                R.string.AppIconVintage
        ),
        AQUA(
                "AquaIcon",
                android.R.color.white,
                R.mipmap.icon_4_foreground_synrg_sa,
                R.string.AppIconAqua
        ),
        PREMIUM(
                "PremiumIcon",
                android.R.color.white,
                R.mipmap.icon_3_foreground_synrg_sa,
                R.string.AppIconPremium
        ),
        TURBO(
                "TurboIcon",
                android.R.color.white,
                R.mipmap.icon_5_foreground_synrg_sa,
                R.string.AppIconTurbo
        ),
        NOX(
                "NoxIcon",
                android.R.color.white,
                R.mipmap.icon_2_foreground_synrg_sa,
                R.string.AppIconNox
        );

        public final String key;
        public final int background;
        public final int foreground;
        public final int title;

        private ComponentName componentName;

        public ComponentName getComponentName(Context ctx) {
            if (componentName == null) {
                componentName = new ComponentName(ctx.getPackageName(), "org.telegram.messenger." + key);
            }
            return componentName;
        }

        LauncherIcon(String key, int background, int foreground, int title) {
            this.key = key;
            this.background = background;
            this.foreground = foreground;
            this.title = title;
        }
    }
}
