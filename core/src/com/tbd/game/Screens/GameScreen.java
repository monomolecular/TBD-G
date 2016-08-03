package com.tbd.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.tbd.game.Core;
import com.tbd.game.View.World;
import com.tbd.game.View.WorldRenderer;

/**
 * Created by Max on 7/31/2016.
 */
public class GameScreen implements Screen {

    Core game;
    World world;
    WorldRenderer render;

    private Matrix4 isoTransform = null;
    private Matrix4	invIsotransform = null;
    Matrix4	id;
    SpriteBatch batch;
    int[][]	map = null;
    Texture textureTileset = null;
    TextureRegion[] tileSet = null;
    OrthographicCamera cam;
    private float tileWidth = 1.0f;
    private float tileHeight = .5f;
    private Vector3 touch = null;
    private int pickedTileX = -1, pickedTileY = -1;

    // Called upon creation.
    public GameScreen(Core game) {
        this.game = game;
        world = new World(game);
        render = new WorldRenderer(world);
    }

    // Gets called first.
    @Override
    public void show() {
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        id = new Matrix4();
        id.idt();

        // Create a 10x10 isometric map
        map = new int[][]{
                {0, 0, 0 ,0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1 ,1, 1, 1, 1, 1, 1, 0},
                {0, 1, 2 ,2, 0, 0, 0, 0, 1, 0},
                {0, 1, 2 ,2, 0, 0, 0, 0, 1, 0},
                {0, 1, 0 ,0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0 ,0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0 ,0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0 ,0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1 ,1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0 ,0, 0, 0, 0, 0, 0, 0}
        };


        //load the tileset
        textureTileset = new Texture("tileset.png");
        textureTileset.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        tileSet = new TextureRegion[4];
        for(int x=0;x<4;x++){
            tileSet[x] = new TextureRegion(textureTileset, x*64, 0, 64, 32);
        }

        //create the isometric transform
        isoTransform = new Matrix4();
        isoTransform.idt();
        isoTransform.translate(0.0f, 0.25f, 0.0f);
        isoTransform.scale((float)(Math.sqrt(2.0) / 2.0), (float)(Math.sqrt(2.0) / 4.0), 1.0f);
        isoTransform.rotate(0.0f, 0.0f, 1.0f, -45.0f);

        //... and the invert matrix
        invIsotransform = new Matrix4(isoTransform);
        invIsotransform.inv();
    }


    // Gets called every step.
    @Override
    public void render(float delta) {

        GL20 gl = Gdx.graphics.getGL20();
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL20.GL_BLEND);
        gl.glEnable(GL20.GL_TEXTURE_2D);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.setProjectionMatrix(cam.combined);
        batch.setTransformMatrix(id);

        batch.begin();
        renderMap();
        batch.end();

        batch.setTransformMatrix(isoTransform);
        batch.begin();
        batch.draw(tileSet[3], 0.0f, 0.0f, 1.0f, 1.0f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //the cam will show 10 tiles
        float camWidth = tileWidth * 10.0f;

        //for the height, we just maintain the aspect ratio
        float camHeight = camWidth * ((float)height / (float)width);

        cam = new OrthographicCamera(camWidth, camHeight);
        cam.position.set(camWidth / 2.0f, 0, 0);
        cam.update();
    }


    private void renderMap(){
        for (int x = 0; x < 10; x++){
            for(int y = 10-1; y >= 0; y--){

                float x_pos = (x * tileWidth / 2.0f ) + (y * tileWidth / 2.0f);
                float y_pos = - (x * tileHeight / 2.0f) + (y * tileHeight / 2.0f);

                if(x == pickedTileX && y == pickedTileY)
                    batch.setColor(1.0f, 0.0f, 0.0f, 1.0f);
                else
                    batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                batch.draw(tileSet[map[x][y]], x_pos, y_pos, tileWidth, tileHeight);

            }
        }
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        GL20 gl = Gdx.graphics.getGL20();
        gl.glDisable(GL20.GL_BLEND);
        gl.glDisable(GL20.GL_TEXTURE_2D);

    }
}
