package com.example.vale.apptotal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class ChequeoService extends Service {


    private String mensaje_remoto;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //
        try {


            Log.d(getClass().getCanonicalName(), "Servicio iniciado!...voy a lanzar una COnsulta por internet, a ver" +
                    "si tengo mensajitos nuevos...");


            mensaje_remoto = (Math.random() > 0.5) ? "HOLA CARI" : "";

            stopSelf(startId);
        }
        catch (Throwable t)
        {
            Log.e(getClass().getCanonicalName(), "ERRORAZO", t);
        }

        return Service.START_NOT_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(getClass().getCanonicalName(), "Servicio Terminado");

        Intent intent_reciver = new Intent("SERVICIO_TERMINADO");

        intent_reciver.putExtra("Mensaje", mensaje_remoto);

        sendBroadcast(intent_reciver);
    }
}
