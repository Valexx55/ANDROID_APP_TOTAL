package com.example.vale.apptotal;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SERVICIO_TERMINADO");

        BroadcastReciver1 br = new BroadcastReciver1(this);
        registerReceiver(br, intentFilter);

        Log.d(getClass().getCanonicalName(), "ReCIVER Registrado :)");

        Intent intent_service = new Intent(this, ChequeoService.class);
        startService(intent_service);

        Log.d(getClass().getCanonicalName(), "SERVICE LANZADO");


    }
}
