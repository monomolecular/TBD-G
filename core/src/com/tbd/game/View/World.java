package com.tbd.game.View;

import com.badlogic.gdx.Gdx;

import com.tbd.game.Core;

import java.util.Iterator;

/**
 * Created by Max on 6/22/2016.
 */
public class World {

    Core game;
    WorldRenderer renderer;

    public World(Core game) {
        this.game = game;

        Gdx.input.setInputProcessor(new InputHandler(this));
    }

    public void update() {}

    public void setRenderer(WorldRenderer renderer) {
        this.renderer = renderer;
    }

    public WorldRenderer getRenderer() {
        return renderer;
    }

    public void dispose() {
    }
}
