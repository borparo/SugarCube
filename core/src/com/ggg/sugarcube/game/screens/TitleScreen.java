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
public class TitleScreen extends AbstractScreen
{
    final Application game;


    // private OrthographicCamera camera;
    private Sprite bg;
    private Sprite facebook;
    private Sprite help;
    private Sprite play;
    private Sprite quit;
    private Sprite settings;
    private Sprite title;


    public TitleScreen(Application game, Vector3 touchPosition, Camera camera)
    {
        super(game, touchPosition, camera);
        this.game = game;
        initialize();
    }

    @Override
    protected void initialize()
    {
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bg_titleScreen.png", Texture.class));
        facebook = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_facebook.png", Texture.class));
        help = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_help.png", Texture.class));
        play = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_play.png", Texture.class));
        quit = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_quit.png", Texture.class));
        settings = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_settings.png", Texture.class));
        title = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "title.png", Texture.class));
    }




    @Override
    public void render(float delta)
    {
        //set viewport
        Gdx.gl.glViewport(game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth(), game.viewport.getScreenHeight());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();

        bg.draw(game.batch);
        title.draw(game.batch);
        title.setPosition(Constants.VIEWPORT_WIDTH / 2 - title.getWidth() / 2, Constants.VIEWPORT_HEIGHT - (title.getHeight() + Constants.BUTTON_MARGINS * 2));

        //buttons
        //PLAY

        play.setPosition(Constants.VIEWPORT_WIDTH / 2 - play.getWidth() / 2, Constants.VIEWPORT_HEIGHT / 2 + play.getHeight() / 2);
        play.draw(game.batch);

        //QUIT

        quit.setPosition(Constants.VIEWPORT_WIDTH / 2 - quit.getWidth() / 2, Constants.VIEWPORT_HEIGHT / 2 - quit.getHeight());
        quit.draw(game.batch);

        //facebook
        facebook.setPosition(Constants.VIEWPORT_WIDTH - (facebook.getWidth() + (Constants.BUTTON_MARGINS * 5)), Constants.BUTTON_MARGINS * 5);
        facebook.draw(game.batch);

        //help
        help.setPosition(Constants.VIEWPORT_WIDTH / 2 - help.getWidth() / 2, Constants.BUTTON_MARGINS * 5);
        help.draw(game.batch);

        //settings
        settings.setPosition(Constants.BUTTON_MARGINS * 5, Constants.BUTTON_MARGINS * 5);
        settings.draw(game.batch);

        game.batch.end();

        //buttons
        if (buttonPressed(play))
        {

            game.setScreen(game.gameScreen);

        }

        if(buttonPressed(quit))
        {
            Gdx.app.exit();
        }
        if(buttonPressed(help))
        {
            game.setScreen(game.helpScreen);
        }
        if(buttonPressed(settings))
        {
            game.setScreen(game.settingsScreen);
        }
        if(buttonPressed(facebook))
        {


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
