package com.ggg.sugarcube.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.ggg.sugarcube.game.Application;
import com.ggg.sugarcube.game.Facebook;


public class DesktopLauncher {

     static Facebook fb;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "SugarCube_V.1.0";
        config.width = 480;
        config.height = 800;
		new LwjglApplication(new Application(fb), config);
	}
}
