package com.janad.covid_19;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityReceiver extends BroadcastReceiver {

    public static ConnectivityRecieverListener connectivityRecieverListener;

    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(connectivityRecieverListener != null){

            connectivityRecieverListener.onNetworkConnectionChanged(isConnected);
        }

    }

    //public static boolean isConnected(){

        //ConnectivityManager cm = MainActivity.getInstance().
    //}

    public interface ConnectivityRecieverListener{

        void onNetworkConnectionChanged(boolean isConnected);
    }
}
