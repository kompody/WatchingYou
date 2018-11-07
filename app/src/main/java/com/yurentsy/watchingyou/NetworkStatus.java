package com.yurentsy.watchingyou;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class NetworkStatus {

    private static Status getStatus() {
        Status currentStatus = Status.OFFLINE;
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return Status.WIFI;
                case ConnectivityManager.TYPE_ETHERNET:
                    return Status.ETHERNET;
                case ConnectivityManager.TYPE_MOBILE:
                    return Status.MOBILE;
                default:
                    return Status.OFFLINE;
            }
        }
        return currentStatus;
    }

    public static boolean isOnline() {
        Status currentStatus = getStatus();
        return currentStatus != Status.OFFLINE;
    }

    private static boolean isAirplane() {
        return Settings.Global.getInt(App.getInstance().getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    public static boolean isWifi() {
        return getStatus() == Status.WIFI;
    }

    public static boolean isEthernet() {
        return getStatus() == Status.ETHERNET;
    }

    public static boolean isMobile() {
        return getStatus() == Status.MOBILE;
    }

    public static boolean isOffline() {
        return getStatus() == Status.OFFLINE;
    }

    public enum Status {
        WIFI,
        MOBILE,
        ETHERNET,
        OFFLINE
    }
}
