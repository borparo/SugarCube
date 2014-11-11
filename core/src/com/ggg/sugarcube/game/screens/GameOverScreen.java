package com.ggg.sugarcube.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.ggg.sugarcube.game.AbstractScreen;
import com.ggg.sugarcube.game.Application;
import com.ggg.sugarcube.game.utils.Assets;
import com.ggg.sugarcube.game.utils.Constants;

/**
 * Created by Borja_Admin on 22.8.2014 г..
 */
public class GameOverScreen extends AbstractScreen
{
    final Application game;

    private Sprite bg;
    private Sprite yes;
    private Sprite no;

    public GameOverScreen(Application game, Vector3 touchPosition, Camera camera)
    {
        super(game, touchPosition, camera);
        this.game = game;
        initialize();
    }

    @Override
    protected void initialize()
    {
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bg_gameOver.png", Texture.class));
        bg.setPosition(Constants.VIEWPORT_WIDTH / 2 - bg.getWidth() / 2, Constants.VIEWPORT_HEIGHT / 2 - bg.getHeight() / 2);

        yes = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_yesOn.png", Texture.class));
        yes.setPosition(Constants.VIEWPORT_WIDTH / 2 - yes.getWidth(), Constants.VIEWPORT_HEIGHT / 2 - yes.getHeight() * 4);
        no = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_noOn.png", Texture.class));
        no.setPosition(Constants.VIEWPORT_WIDTH / 2 + no.getWidth(), Constants.VIEWPORT_HEIGHT / 2 - yes.getHeight() * 4);
    }


    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glViewport(game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth(), game.viewport.getScreenHeight());

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        bg.draw(game.batch);
        yes.draw(game.batch);
        no.draw(game.batch);

        game.batch.end();

        //YES BUTTON
        if (Gdx.input.justTouched())
        {
            game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touchPosition);

            if (game.touchPosition.x > yes.getX() &&
                    game.touchPosition.x < yes.getX() + yes.getWidth() &&
                    game.touchPosition.y > yes.getY() &&
                    game.touchPosition.y < yes.getY() + yes.getHeight())
            {
                game.setScreen(game.gameScreen);
            }
        }

        //NO BUTTON
        if (Gdx.input.justTouched())
        {
            game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(game.touchPosition);

            if (game.touchPosition.x > no.getX() &&
                    game.touchPosition.x < no.getX() + no.getWidth() &&
                    game.touchPosition.y > no.getY() &&
                    game.touchPosition.y < no.getY() + no.getHeight())
            {
                game.setScreen(game.titleScreen);
            }
        }
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
}
