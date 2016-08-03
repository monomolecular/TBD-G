package com.tbd.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.tbd.game.Core;

/**
 * Created by Max on 6/22/2016.
 */
public class WorldRenderer {

    World world;
    SpriteBatch batch;
    OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    float width, height;

    // This is where the NPCs, monsters, projectiles, textures, particles, etc. get defined.

    public WorldRenderer(World world) {
        this.world = world;

        world.setRenderer(this);

        width = Gdx.graphics.getWidth() / 40;
        height = Gdx.graphics.getHeight() / 40;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        // Create shapes, emit particles, etc.
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // Get objects from the world.

        // Deal with the particles.

        // Update camera, based on the position of the character.
        // camera.position.set(?player?.getPosition().x, ?player?.getPosition().y, 0);
        camera.update();

        // Draw the players ship.
        batch.setProjectionMatrix(camera.combined);

        // Start rendering.
        batch.begin();
        /*
        batch.draw( shipTexture,                                            // Draw the ship...
                    ship.getPosition().x, ship.getPosition().y,             // ... get the ships position, but make ir
                    ship.getWidth() / 2, ship.getHeight() / 2,              // ... pivot relative to the ships center,
                    ship.getWidth(), ship.getHeight(), 1, 1,                // ... make the ship size = 1 camera unit
                    ship.getRotation(),                                     // ... rotate the ship sprite as needed
                    0, 0, shipTexture.getWidth(), shipTexture.getHeight(),  // ... draw the entire ship
                    false, false);                                          // ... don't flip vertically or horizontally.
        */

        // Draw the particles.

        // Get an iterator and use it to draw the enemies.

        // Get an iterator and use it to draw the projectiles.

        // Done rendering.
        batch.end();

        // If we're debugging, draw collision boxes.
        if (Core.DEBUG == true) {
            // Draw a rectangle around our character sprite.
            /*
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeType.Line);
            shapeRenderer.setColor(Color.CYAN);
            shapeRenderer.rect(ship.getBounds().x, ship.getBounds().y, ship.getBounds().width, ship.getBounds().height);
            shapeRenderer.setColor(Color.RED);
            */

            // Draw a rectangle around our enemy sprites.


            // Draw a rectangle around our bullet sprites.


            shapeRenderer.end();
        }
    }


    public OrthographicCamera getCamera() {
        return camera;
    }

    public void update() {
        // Update stuff, like the player.
    }

    public void dispose() {
        shapeRenderer.dispose();
        batch.dispose();
    }
}
