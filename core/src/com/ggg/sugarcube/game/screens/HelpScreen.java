package com.ggg.sugarcube.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
 * Created by Borja_Admin on 24.8.2014 г..
 */
public class HelpScreen extends AbstractScreen
{
    final Application game;
    //private OrthographicCamera camera;
    private Sprite bg;
    private Sprite back;



    public HelpScreen(Application game, Vector3 touchPosition, Camera camera)
    {
        super(game, touchPosition, camera);
        this.game = game;
        initialize();
    }

    @Override
    protected void initialize()
    {

        bg = new Sprite(Assets.manager.get (Constants.ASSETS_PATH + "bg_help.png", Texture.class));
        back = new Sprite(Assets.manager.get (Constants.ASSETS_PATH + "btn_back.png", Texture.class));
        back.setPosition(Constants.BUTTON_MARGINS * 5, Constants.BUTTON_MARGINS * 5);
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
        back.draw(game.batch);

        game.batch.end();

        //BUTTON BACK
        if(buttonPressed(back))
        {
            game.setScreen(game.titleScreen);
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
