package com.tbd.game.View;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Max on 6/22/2016.
 */
public class InputHandler implements InputProcessor {

    World world;
    //Ship ship;
    Vector3 touch = new Vector3();
    Vector2 vec2Touch = new Vector2();

    public InputHandler(World world) {
        this.world = world;
    }

    @Override
    public boolean keyDown(int keycode) {
        //ship = world.getShip();
        switch (keycode) {
            case Keys.W:       // UP
            case Keys.UP:
            case Keys.NUMPAD_8:
               //ship.getVelocity().y = 1;
                break;
            case Keys.NUMPAD_9:  // UP & RIGHT
            case Keys.PAGE_UP:
               // ship.getVelocity().y = 1;
               // ship.getVelocity().x = 1;
                break;
            case Keys.D:         // RIGHT
            case Keys.RIGHT:
            case Keys.NUMPAD_6:
                //ship.getVelocity().x = 1;
                break;
            case Keys.NUMPAD_3: // DOWN & RIGHT
            case Keys.PAGE_DOWN:
                //ship.getVelocity().y = -1;
                //ship.getVelocity().x = 1;
                break;
            case Keys.S:        // DOWN
            case Keys.DOWN:
            case Keys.NUMPAD_2:
                //ship.getVelocity().y = -1;
                break;
            case Keys.NUMPAD_1: // DOWN & LEFT
            case Keys.END:
               // ship.getVelocity().y = -1;
                //ship.getVelocity().x = -1;
                break;
            case Keys.A:        // LEFT
            case Keys.LEFT:
            case Keys.NUMPAD_4:
                //ship.getVelocity().x = -1;
                break;
            case Keys.NUMPAD_7: // UP & LEFT
            case Keys.HOME:
               // ship.getVelocity().y = 1;
              //  ship.getVelocity().x = -1;
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        //ship = world.getShip();
        switch (keycode) {
            case Keys.W:        // UP
            case Keys.UP:
            case Keys.NUMPAD_8:
               // if(ship.getVelocity().y == 1) ship.getVelocity().y = 0;
                break;
            case Keys.NUMPAD_9: // UP & RIGHT
            case Keys.PAGE_UP:
               // if(ship.getVelocity().y == 1) ship.getVelocity().y = 0;
               // if(ship.getVelocity().x == 1) ship.getVelocity().x = 0;
                break;
            case Keys.D:        // RIGHT
            case Keys.RIGHT:
            case Keys.NUMPAD_6:
               // if(ship.getVelocity().x == 1) ship.getVelocity().x = 0;
                break;
            case Keys.NUMPAD_3: // DOWN & RIGHT
            case Keys.PAGE_DOWN:
               // if(ship.getVelocity().x == 1) ship.getVelocity().x = 0;
               // if(ship.getVelocity().y == -1) ship.getVelocity().y = 0;
                break;
            case Keys.S:        // DOWN
            case Keys.DOWN:
            case Keys.NUMPAD_2:
                //if(ship.getVelocity().y == -1) ship.getVelocity().y = 0;
                break;
            case Keys.NUMPAD_1: // DOWN & LEFT
            case Keys.END:
                //if(ship.getVelocity().y == -1) ship.getVelocity().y = 0;
                //if(ship.getVelocity().x == -1) ship.getVelocity().x = 0;
                break;
            case Keys.A:        // LEFT
            case Keys.LEFT:
            case Keys.NUMPAD_4:
               // if(ship.getVelocity().x == -1) ship.getVelocity().x = 0;
                break;
            case Keys.NUMPAD_7: // UP & LEFT
            case Keys.HOME:
               // if(ship.getVelocity().x == -1) ship.getVelocity().x = 0;
               // if(ship.getVelocity().y == 1) ship.getVelocity().y = 0;
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    // Covers mouse clicks and touchscreen touches on mobile device.
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // The pointer variable is the finger number that touches the screen. Starts at 0 for the first finger.
        // ToDo: Specify in the manifest whatever version of Android is required to handle multi-touch.

        touch.set(screenX, screenY, 0);
        world.getRenderer().getCamera().unproject(touch);
        vec2Touch.set(touch.x, touch.y);
       //ship = world.getShip();
        //world.addBullet(new Bullet(Bullet.SPEED, 0, .1f, 8/20f,
        //        new Vector2(ship.getPosition().x + ship.getWidth() / 2, ship.getPosition().y + ship.getHeight() / 2),
        //        new Vector2(vec2Touch.sub(ship.getPosition()).nor())));
        //Audio.shoot();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
