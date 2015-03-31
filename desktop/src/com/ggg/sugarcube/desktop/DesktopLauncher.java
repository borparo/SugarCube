package com.ggg.sugarcube.desktop;

import com.badlogic.gdx.backends.lwjgl.*;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.ggg.sugarcube.game.Application;


public class DesktopLauncher {



	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "SugarCube_V.1.0";
        config.width = 480;
        config.height = 800;
		new LwjglApplication(new Application(), config);
	}
}
