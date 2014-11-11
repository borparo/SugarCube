package com.ggg.sugarcube.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.ggg.sugarcube.game.Application;
import com.ggg.sugarcube.game.Facebook;

public class AndroidLauncher extends AndroidApplication implements Facebook
{
    Facebook facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new Application(facebook), config);
    }


    @Override
    public void shareScore()
    {

    }

    @Override
    public void shareLikeApp()
    {

    }
}
