package com.example.kcanel.alarma.MessageCloud;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by kcanel on 28/08/2018.
 */

public class MyFirebaseInstanceIdeService extends FirebaseInstanceIdService {

    /*Generacion de código Token perros*/

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String myToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("ACA","Token "+ myToken);
    }
}
