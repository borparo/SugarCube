package com.ggg.sugarcube.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.ggg.sugarcube.game.AbstractScreen;
import com.ggg.sugarcube.game.Application;
import com.ggg.sugarcube.game.utils.Assets;
import com.ggg.sugarcube.game.utils.Constants;


/**
 * Created by Borja_Admin on 6.11.2014 г..
 */
public class PauseScreen extends AbstractScreen
{
    final Application game;
    private Sprite bg;
    private Sprite resume;
    private Sprite backToTitle;


    public PauseScreen(Application game, Vector3 touchPosition, Camera camera)
    {
        super(game, touchPosition, camera);
        this.game = game;
        initialize();
    }

    @Override
    protected void initialize()
    {
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bg_paused.png", Texture.class));
        bg.setPosition(Constants.VIEWPORT_WIDTH / 2 - bg.getWidth() / 2, Constants.VIEWPORT_HEIGHT / 2 - bg.getHeight() / 2);
        resume = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_resume.png", Texture.class));
        resume.setPosition(Constants.VIEWPORT_WIDTH / 2 - resume.getWidth() / 2, Constants.VIEWPORT_HEIGHT / 2 + resume.getHeight()  );
        backToTitle= new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_backToTitle.png", Texture.class));
        backToTitle.setPosition(Constants.VIEWPORT_WIDTH / 2 - backToTitle.getWidth() / 2, Constants.VIEWPORT_HEIGHT / 2 - backToTitle.getHeight() * 3);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.55f, 0.55f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth(), game.viewport.getScreenHeight());

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        bg.draw(game.batch);
        resume.draw(game.batch);
        backToTitle.draw(game.batch);

        game.batch.end();

        if(buttonPressed(resume))
        {
            game.setScreen(game.gameScreen);
            game.setPaused(false);
            game.gameMusic.play();
        }

        if(buttonPressed(backToTitle))
        {
            game.setScreen(game.titleScreen);
            game.setPaused(false);
            game.gameMusic.play();

        }
    }
}
