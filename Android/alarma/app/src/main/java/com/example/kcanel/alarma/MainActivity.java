package com.example.kcanel.alarma;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;

import static com.example.kcanel.alarma.R.menu.main_menu;

public class MainActivity extends AppCompatActivity{

    TextView contenidoNotificacion;
    TextView titleAlert;
    ImageView foto;

    String TAG = "ACA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("news");

        contenidoNotificacion = (TextView) findViewById(R.id.contenido);
        foto = (ImageView) findViewById(R.id.myImage);

        /*Obteniendo extras cuando notificación fué pushada*/
        if(getIntent().getExtras() != null){
                for( String key: getIntent().getExtras().keySet()){
                    String value = getIntent().getExtras().getString(key);

                    if(key.equalsIgnoreCase("texto")){
                        contenidoNotificacion.setText(value);
                        foto.setImageDrawable(getResources().getDrawable(R.drawable.warning));
                    }
                }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_team){
            Intent about = new Intent(getApplicationContext(), Team.class);
            startActivity(about);
        }
        return true;
    }
}
