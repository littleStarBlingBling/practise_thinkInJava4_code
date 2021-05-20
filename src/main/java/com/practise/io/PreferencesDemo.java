package com.practise.io;

import java.util.prefs.Preferences;

public class PreferencesDemo {

    public static void main(String[] args) throws Exception {
        Preferences preferences = Preferences.userNodeForPackage(PreferencesDemo.class);
        preferences.put("Background image", "xxx.jpg");
        preferences.put("Font color", "black");
        preferences.putInt("Font size", 24);
        preferences.putBoolean("Animate Window", true);

        int usageCount = preferences.getInt("UsageCount", 0);
        usageCount++;
        preferences.putInt("UsageCount", usageCount);

        for (String key : preferences.keys()) {
            // 取数据时需要提供默认值
            System.out.println(key + ": " + preferences.get(key, null));
        }
    }
}
