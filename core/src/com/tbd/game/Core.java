package com.tbd.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.tbd.game.Screens.StartScreen;

public class Core extends Game {

    /*
    Project Flow:
    Core calls create() which loads up the StartScreen
    StartScreen sets the screen to GameScreen on a button click
    SpashScreen calls show()
    SplashScreen calls render() every step
    SplashScreen calls tweenCompleted (added in episode 3) which changes it to a new screen, and the process reapeats with create(), show(), render() until something triggers a change
    */

	public static final String VERSION = "0.0.0.1 Pre-Alpha";
    public static final String LOG = "Tempted by Dragons";
    public static final boolean DEBUG = false;
    FPSLogger fpsLogger;

	@Override
	public void create () {
        fpsLogger = new FPSLogger();
        setScreen(new StartScreen(this));	// <-- 'this' is a reference to this (Core) class.
        //Audio.playMusic(true);
    }

    @Override
    // Fired when the application is being destroyed.
    public void dispose () {
        super.dispose();
        //Audio.dispose();
    }

    @Override
    // Fired by the game loop from the application every time the rendering happens. The game update should take place
    // before the actual rendering.
    public void render () {
        super.render();
        fpsLogger.log();
    }

    @Override
    //  Fired every time the game screen is re-sized and the game is NOT in a paused state.
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    // Fired ONLY on Android.
    public void pause() {
        super.pause();
    }

    @Override
    // Fired ONLY on Android
    public void resume() {
        super.resume();
    }
}
