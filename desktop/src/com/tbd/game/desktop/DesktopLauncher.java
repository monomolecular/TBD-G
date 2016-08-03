package com.tbd.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tbd.game.Core;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Tempted by Dragons " + Core.VERSION;
		config.useGL30 = true;
		// config.fullscreen = false;
		config.width = 1080;
		config.height = 720;
		config.vSyncEnabled = true; // Default

		new LwjglApplication(new Core(), config);
	}
}
