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
import com.ggg.sugarcube.game.utils.GameStatus;

/**
 * Created by Borja_Admin on 6.11.2014 г..
 */
public class DifficultyScreen extends AbstractScreen
{
    private final Application game;
    private Sprite bg;
    private Sprite easy;
    private Sprite normal;
    private Sprite hard;
    private Sprite insane;

    public DifficultyScreen(Application game, Vector3 touchPosition, Camera camera)
    {
        super(game, touchPosition, camera);
        this.game = game;
    }

    @Override
    protected void initialize()
    {
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bg_titleScreen", Texture.class));
        easy = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_easy", Texture.class));
        normal = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_normal", Texture.class));
        hard = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_hard", Texture.class));
        insane = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_insane", Texture.class));

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

        easy.draw(game.batch);
        normal.draw(game.batch);
        hard.draw(game.batch);
        insane.draw(game.batch);

        game.batch.end();

        if(!game.isPaused())
        {
            if(buttonPressed(easy))
            {
                setDifficulty(5,200, 800, 1100);
            }

            if(buttonPressed(normal))
            {
                setDifficulty(3,400, 800, 1100);
            }

            if(buttonPressed(hard))
            {
                setDifficulty(2,600, 400, 600);
            }

            if(buttonPressed(insane))
            {
                setDifficulty(1,850, 250, 500);
            }
        }
    }

    private void setDifficulty(int playerLifes, float dropSpeed, int dropSpawnMin, int dropSpawnMax)
    {
        GameStatus.PLAYER_LIFES = playerLifes;
        GameStatus.DROPS_SPEED = dropSpeed;
        GameStatus.DROPS_SPAWN_TIME_MIN = dropSpawnMin;
        GameStatus.DROPS_SPAWN_TIME_MAX = dropSpawnMax;

    }

}
