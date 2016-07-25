package com.example.vale.apptotal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class BroadcastReciver2 extends BroadcastReceiver {
    public BroadcastReciver2() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        try
        {


        Log.d(getClass().getCanonicalName(), "Alarma ejecutándose");
        Log.d(getClass().getCanonicalName(), "Lanzando el servicio");

        /*IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SERVICIO_TERMINADO");

        context.registerReceiver(new BroadcastReciver1(context), intentFilter);
*/
        Intent intent_serv = new Intent(context, ChequeoService.class);

        context.startService(intent_serv);
        }
        catch (Throwable t)
        {
            Log.e(getClass().getCanonicalName(), "ERRORAZO", t);
        }
    }
}
