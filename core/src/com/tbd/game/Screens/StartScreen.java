package com.tbd.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tbd.game.Core;

/**
 * Created by Max on 7/31/2016.
 */
public class StartScreen implements Screen {

    Core game;
    ScreenViewport viewport;
    Stage stage;                // Holds all the actors (aka objects) and handle input events
    BitmapFont black;           // Creates a font.
    BitmapFont white;           // Creates a font.
    TextureAtlas atlas;         // Holds all of our images (think spritesheet).
    Skin skin;                  // Holds each individual image (think individual sprites).
    SpriteBatch batch;
    TextButton button;          // Creates a button which we will put text on.
    Label label;

    // Called upon creation.
    public StartScreen(Core game) {
        this.game = game;
    }

    // Gets called first.
    @Override
    public void show() {
        batch = new SpriteBatch();
        skin = new Skin();
        // Point atlas at the button.pack (large image made up of smaller images - like a spritesheet).
        atlas = new TextureAtlas("button.pack");
        // Tell skin to look at our atlas, and cut along all the dotted lines to make individual images again.
        skin.addRegions(atlas);
        // Define a pair of fonts.
        black = new BitmapFont(Gdx.files.internal("blackfont.fnt"), false);
        white = new BitmapFont(Gdx.files.internal("whitefont.fnt"), false);

        viewport = new ScreenViewport();
        stage = new Stage(viewport);
    }

    // Gets called every step.
    @Override
    public void render(float delta) {
        // Set the screen to black and clear the screen.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        stage.act(delta);  // Tell the stage to do its thing, passing it time. This will update all individual actors.

        batch.begin();
        stage.draw();      // ...and then of course we want it to draw those changes it made

        // Draw text using the white font
        white.draw(batch, "Tempted by Dragons", Gdx.graphics.getWidth() / 2 - 15, Gdx.graphics.getHeight() / 2 +100);
        batch.end();
    }

    @Override
    // Gets called after show, and then every time the window's resized, if the game is NOT in a paused state.
    public void resize(int width, int height) {
        // Had to add this viewport code, it wasn't there before. width/height/true were just directly in the Stage().
        // http://www.gamefromscratch.com/post/2015/01/06/LibGDX-Video-Tutorial-Scene2D-Actors-and-Actions.aspx

        viewport.update(width, height, true);

        //clear the screen before we continue editing anything.
        stage.clear();

        // Tells the StartScreen that we are going to be capturing all input events on stage.
        Gdx.input.setInputProcessor(stage);

        // Creates a new button style so we can customize what it looks like at different points.
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();

        // When button is up, look inside skin and grab buttonnormal, that's what i want it to look like.
        style.up = skin.getDrawable("buttonnormal");
        // When button is down (pressed) make it look like buttonpressed.
        style.down = skin.getDrawable("buttonpressed");
        // All drawing on this button will be done in our black font. fancy fancy
        style.font = black;

        // Finally create the button that says 'press me', and uses style for its style. Set it's dimensions and location.
        button = new TextButton("Press Me!", style);
        button.setWidth(400);
        button.setHeight(100);
        button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
        button.setY(Gdx.graphics.getHeight() / 2 - button.getHeight() / 2);

        // Set up what happens when we press or release the button.
        button.addListener(new InputListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //
            }
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                return true;
            }
        });

        // Create the label that should appear over the button.
        Label.LabelStyle ls = new Label.LabelStyle(white, Color.WHITE);
        label = new Label("Tempted by Dragons", ls);
        label.setX(0);
        label.setY(Gdx.graphics.getHeight() / 2 + 100);
        label.setWidth(width);
        label.setAlignment(Align.center);

        // Put the button and the label on the stage now please so we can interact with it.
        stage.addActor(button);
        stage.addActor(label);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        // If something has a .dispose() method, it NEEDS to be called here
        batch.dispose();
        skin.dispose();
        atlas.dispose();
        black.dispose();
        white.dispose();
        stage.dispose();
    }
}
