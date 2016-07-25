package com.example.vale.apptotal;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class BroadcastReciver1 extends BroadcastReceiver {


    private Context context;

    public BroadcastReciver1(Context context) {

        this.context = context;
    }

    private void programarAlarma ()
    {
        Calendar calendar_actual = Calendar.getInstance();

        long tiempo = calendar_actual.getTimeInMillis() + 60000; //en 1 min, 60 mil ms, saltará otar vez la alarma

        Intent intentAlarm = new Intent(context, BroadcastReciver2.class);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 55, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, tiempo, pi);//TIempo, No es el tiempo que falta. Es el tiempo expresado en milisegundos equivalente a la fecha y hora del omento en que se quiere ejecutar


        SimpleDateFormat dateformatter = new SimpleDateFormat("E dd/MM/yyyy 'a las' hh:mm:ss");

        Log.d(getClass().getCanonicalName(), "Alarma programada para "+ dateformatter.format(tiempo));

    }

    private void lanzarNotificacion (String mensaje)
    {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("¡Nuevo mensajillo!")
                        .setContentText(mensaje)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL);

        Intent resultIntent = new Intent(this.context, Main2Activity.class);
        resultIntent.putExtra("mensaje", mensaje);
        PendingIntent resultPendingIntent = PendingIntent.getActivity (context, (int) System.currentTimeMillis(), resultIntent, PendingIntent.FLAG_ONE_SHOT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(537, mBuilder.build());//537 id de la noti: único en la app!

    }



    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getCanonicalName(), "Me han llamado desde un service!");

        String mensaje_recibido = intent.getStringExtra("Mensaje");

        if (!mensaje_recibido.equals(""))
        {
            //Log.d(NotificaPodcastNuevoDisponible.class.getCanonicalName(), "Lanzando notificación");

            Log.d(getClass().getCanonicalName(), "He recibbido cositas..yijaa :)");
            lanzarNotificacion(mensaje_recibido);


        }
        else
        {
            Log.d(getClass().getCanonicalName(), "No he recibido nada. Merde. Volveré a mirar dentro de un rato");
            programarAlarma ();
        }
    }
}
