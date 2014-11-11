package com.ggg.sugarcube.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ggg.sugarcube.game.screens.*;
import com.ggg.sugarcube.game.utils.Assets;
import com.ggg.sugarcube.game.utils.Constants;



public class Application extends Game implements Facebook
{
    public Viewport viewport;
    public GameScreen gameScreen;
    public TitleScreen titleScreen;
    public GameOverScreen overScreen;
    public HelpScreen helpScreen;
    public SettingsScreen settingsScreen;
    public PauseScreen pauseScreen;
    public DifficultyScreen difficultyScreen;

    public Music gameMusic;
    public SpriteBatch batch;
    public BitmapFont font;

    public Vector3 touchPosition;

    public FileHandle scoresFile;
    public OrthographicCamera camera;

    private boolean paused;

    Facebook facebook;

    public Application(Facebook fb)
    {
        this.facebook = fb;
    }



    @Override
    public void create()
    {
        Assets.load();
        //Assets.manager.finishLoading();
        while(!Assets.manager.update())
        {
            System.out.println(Assets.manager.getProgress() * 100.0 + "%");
        }

        //start music when game starts
        gameMusic = Assets.manager.get(Constants.SOUND_PATH + "runAmok.mp3", Music.class);
        gameMusic.setLooping(true);
        gameMusic.play();

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.update();

        viewport = new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, camera);

        touchPosition = new Vector3();

        font = Assets.manager.get(Constants.ASSETS_PATH + "gameFont.fnt", BitmapFont.class);

        titleScreen = new TitleScreen(this, this.touchPosition, this.camera);
        gameScreen = new GameScreen(this, this.touchPosition, this.camera);
        overScreen = new GameOverScreen(this, this.touchPosition, this.camera);
        helpScreen = new HelpScreen(this, this.touchPosition, this.camera);
        settingsScreen = new SettingsScreen(this, this.touchPosition, this.camera);
        pauseScreen = new PauseScreen(this, this.touchPosition, this.camera);
        difficultyScreen = new DifficultyScreen(this, this.touchPosition, this.camera);

        paused = false;

        this.setScreen(titleScreen);
    }

    @Override
    public void render()
    {
        super.render();
    }

    public void dispose()
    {
        Assets.dispose();
    }



    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    public boolean isPaused()
    {
        return paused;
    }

    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }

    @Override
    public void pause()
    {
        paused = true;
    }

    @Override
    public void resume()
    {
        paused = false;
    }

    @Override
    public void shareScore()
    {

    }

    @Override
    public void shareLikeApp()
    {

    }
}
