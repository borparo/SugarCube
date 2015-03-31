package com.ggg.sugarcube.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ggg.sugarcube.game.Application;
import com.facebook.FacebookSdk;


public class AndroidLauncher extends AndroidApplication
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new Application(), config);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }


}
