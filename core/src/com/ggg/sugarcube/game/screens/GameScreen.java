package com.ggg.sugarcube.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.ggg.sugarcube.game.AbstractScreen;
import com.ggg.sugarcube.game.Application;
import com.ggg.sugarcube.game.utils.Assets;
import com.ggg.sugarcube.game.utils.Constants;
import com.ggg.sugarcube.game.utils.GameStatus;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Borja_Admin on 22.8.2014 г..
 */
public class GameScreen extends AbstractScreen
{
    //FIELDS

    final Application game;

    private int availableLifes;
    private int dropSpeed;
    private int timeToSpawnDrop;

    //private List<Rectangle> playerLifes;
    private List<Rectangle> raindrops;

    private long lastDropTime;
    private long riseDropSpeed;
    private long gameScore;
    private long gameTarget;

    // private OrthographicCamera camera;
    private ParticleEffect waterExplosion;

    private Sprite sugar;
    private Sprite pause;
    private Sprite bg;

    private Texture dropTexture;
    private Texture life1;
    private Texture life2;
    private Texture life3;
    private Texture noLifeTexture;

    private Vector2 sugarPosition;

    //CONSTRUCTOR

    public GameScreen(Application game, Vector3 touchPosition, Camera camera)
    {
        super(game, touchPosition, camera);
        this.game = game;
        initialize();
    }

    @Override
    protected void initialize()
    {
        availableLifes = 3;
        bg = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "bg_play.png", Texture.class));

        pause = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "btn_pause.png", Texture.class));
        pause.setPosition(Constants.BUTTON_MARGINS, Constants.VIEWPORT_HEIGHT - (pause.getHeight() + Constants.BUTTON_MARGINS));

        sugar = new Sprite(Assets.manager.get(Constants.ASSETS_PATH + "sugar_idle.png", Texture.class));
        sugarPosition = new Vector2(Constants.VIEWPORT_WIDTH / 2 - sugar.getWidth() / 2, GameStatus.SUGAR_Y_POSITION);
        sugar.setPosition(sugarPosition.x, sugarPosition.y);
        sugar.setOriginCenter();
        sugar.setScale(0.75f);

        dropTexture = Assets.manager.get(Constants.ASSETS_PATH + "waterDrop.png");
        dropSpeed = 300;

        life1 = Assets.manager.get(Constants.ASSETS_PATH + "life.png");
        life2 = Assets.manager.get(Constants.ASSETS_PATH + "life.png");
        life3 = Assets.manager.get(Constants.ASSETS_PATH + "life.png");

        noLifeTexture = Assets.manager.get(Constants.ASSETS_PATH + "lifeNo.png");

        raindrops = new ArrayList<Rectangle>();

        timeToSpawnDrop = MathUtils.random(GameStatus.DROPS_SPAWN_TIME_MIN, GameStatus.DROPS_SPAWN_TIME_MAX);
        gameScore = 0;

        //read scores files. if null target is 0, if not, write the highest score in target
        gameTarget = GameStatus.TARGET_SCORE;

        waterExplosion = new ParticleEffect();

        spawnDrop();
        increaseDropSpeed();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glViewport(game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth(), game.viewport.getScreenHeight());

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();

        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        //draw screen
        bg.draw(game.batch);
        sugar.draw(game.batch);

        //draw raindrops
        for (Rectangle drop : raindrops)
        {
            game.batch.draw(dropTexture, drop.x, drop.y);
        }

        //draw lifes
        game.batch.draw(life1, Constants.VIEWPORT_WIDTH - (life1.getWidth() * 4 + Constants.BUTTON_MARGINS * 8), Constants.VIEWPORT_HEIGHT - (life1.getHeight() + Constants.BUTTON_MARGINS * 5));
        game.batch.draw(life2, Constants.VIEWPORT_WIDTH - (life2.getWidth() * 3 + Constants.BUTTON_MARGINS * 6), Constants.VIEWPORT_HEIGHT - (life2.getHeight() + Constants.BUTTON_MARGINS * 5));
        game.batch.draw(life3, Constants.VIEWPORT_WIDTH - (life3.getWidth() * 2 + Constants.BUTTON_MARGINS * 4), Constants.VIEWPORT_HEIGHT - (life3.getHeight() + Constants.BUTTON_MARGINS * 5));

        //draw fx
        waterExplosion.draw(game.batch, delta);
        checkSugarLifes();

        //draw pause menu
        pause.draw(game.batch);

        //draw gameScore and gameTarget;

        game.font.draw(game.batch, "" + gameTarget, 400, 125);
        game.font.draw(game.batch, "" + gameScore, 1050, 125);

        // display new target message if score is higer that target
        if (gameScore > gameTarget && gameTarget != 0)
        {
            //TODO draw for a few secs message new high score.
        }
        game.batch.end();

        //update content if game is not paused
        if (!game.isPaused())
        {
            //move sugar
            if (Gdx.input.isTouched())
            {
                game.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                game.camera.unproject(game.touchPosition);
                sugarPosition.x = game.touchPosition.x - sugar.getWidth() / 2;
                sugar.setX(sugarPosition.x);
            }

            //keep bucket in bounds
            if (sugarPosition.x < 0)
            {
                sugarPosition.x = 0;
                sugar.setX(sugarPosition.x);
            }
            if (sugarPosition.x > Constants.VIEWPORT_WIDTH - sugar.getWidth())
            {
                sugarPosition.x = Constants.VIEWPORT_WIDTH - sugar.getWidth();
                sugar.setX(sugarPosition.x);
            }

            //spawn drops
            if (TimeUtils.millis() - lastDropTime > timeToSpawnDrop)
            {
                spawnDrop();

                //decrease spawning time
                if (timeToSpawnDrop >= 500)
                {
                    timeToSpawnDrop -= 25;
                } else if (timeToSpawnDrop <= 500)
                {
                    timeToSpawnDrop = 500;
                }
            }

            //increase drop speed
            if (TimeUtils.millis() - riseDropSpeed > 2000)
            {
                dropSpeed += GameStatus.DROPS_SPEED;
                increaseDropSpeed();
            }

            //move drops and check collisions
            Iterator<Rectangle> iter = raindrops.iterator();
            while (iter.hasNext())
            {
                Rectangle raindrop = iter.next();
                raindrop.y -= dropSpeed * Gdx.graphics.getDeltaTime();

                if (raindrop.getY() + raindrop.getHeight() < 0)
                {
                    //add points when drops are avoid
                    gameScore++;
                    iter.remove();
                }

                if (raindrop.contains(sugarPosition.x + sugar.getWidth(), sugarPosition.y + sugar.getHeight()) ||
                        raindrop.contains(sugarPosition.x, sugarPosition.y))
                {
                    gameScore -= 5;
                    waterExplosion.setPosition(sugar.getX() + 24, sugar.getHeight());
                    waterExplosion.start();
                    iter.remove();

                    //change player texture and take life away
                    setAvailableLifes(getAvailableLifes() - 1);


                    if (waterExplosion.isComplete())
                    {
                        waterExplosion.dispose();
                    }
                }
            }
        }

        //PAUSE BUTTON
        if (buttonPressed(pause))
        {
            game.setPaused(true);
            game.setScreen(game.pauseScreen);
            game.gameMusic.pause();
        }


    }

    private void spawnDrop()
    {
        Rectangle drop = new Rectangle();

        drop.x = MathUtils.random(0, Constants.VIEWPORT_WIDTH - drop.getWidth());
        drop.y = Constants.VIEWPORT_HEIGHT;
        drop.width = GameStatus.DROP_WIDTH;
        drop.height = GameStatus.DROP_HEIGHT;
        raindrops.add(drop);
        lastDropTime = TimeUtils.millis();

    }

    private void checkSugarLifes()
    {
        switch (getAvailableLifes())
        {
            case 2:
                life1 = new Texture(Gdx.files.internal("images/lifeNo.png"));
                break;
            case 1:
                life2 = new Texture(Gdx.files.internal("images/lifeNo.png"));
                break;
            case 0:
                life3 = new Texture(Gdx.files.internal("images/lifeNo.png"));

                if (gameScore > gameTarget)
                {
                    GameStatus.TARGET_SCORE = gameScore;
                }
                game.setScreen(game.overScreen);
                break;
        }
    }

    private void increaseDropSpeed()
    {
        riseDropSpeed = TimeUtils.millis();
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void show()
    {
        //TODO game init when coming from paused screen
        if (availableLifes == 0)
        {
            initialize();
        }
        waterExplosion.load(Gdx.files.internal("effects/waterExplosion2"),
                Gdx.files.internal("effects"));
        waterExplosion.setDuration(2);
        waterExplosion.scaleEffect(0.5f);
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
        bg.getTexture().dispose();
        sugar.getTexture().dispose();
        dropTexture.dispose();
        life1.dispose();
        life2.dispose();
        life3.dispose();
        pause.getTexture().dispose();
        waterExplosion.dispose();
    }

    //GETTERS & SETTERS
    public int getAvailableLifes()
    {
        return availableLifes;
    }

    public void setAvailableLifes(int availableLifes)
    {
        this.availableLifes = availableLifes;
    }
}
