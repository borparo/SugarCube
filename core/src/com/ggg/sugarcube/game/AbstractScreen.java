package com.ggg.sugarcube.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;


/**
 * Created by Borja_Admin on 6.11.2014 г..
 */
public abstract class AbstractScreen implements Screen
{
    private final Application game;
    private final Vector3 touchPosition;
    private final Camera camera;

    public AbstractScreen( final Application game, Vector3 touchPosition, Camera camera)
    {
        this.game = game;
        this.touchPosition = touchPosition;
        this.camera = camera;
    }

    protected abstract void initialize();

    @Override
    public void render(float delta)
    {

    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }

    public boolean buttonPressed(Sprite button)
    {
        if(Gdx.input.justTouched())
        {
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);

            if (touchPosition.x > button.getX() &&
                    touchPosition.x < button.getWidth() + button.getX() &&
                    touchPosition.y > button.getY() &&
                    touchPosition.y < button.getY() + button.getHeight())
            {
                return true;
            }
        }

        return false;
    }


}
