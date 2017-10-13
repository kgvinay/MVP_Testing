package com.fhi.sampledimvc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Vinay on 10/1/2016.
 */
public class Util {
    public static class UtilConstants {
        public static class Languages {
            public final static String DE = "de";
            public final static String IN = "in";
            public final static String EN = "en";
        }
    }

    public static boolean isInternetConnection(Context context) {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }


   public static void hideSoftKeyboard(Context context, View focusableView) {
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                focusableView.getWindowToken(), 0);
    }
}
