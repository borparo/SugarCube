package com.ggg.sugarcube.game.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Borja_Admin on 5.11.2014 г..
 */
public class Assets
{
    public static final AssetManager manager = new AssetManager();
    public static void load()
    {
        manager.load(Constants.ASSETS_PATH + "bg_black.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bg_gameOver.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bg_help.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bg_paused.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bg_play.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bg_settings.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "bg_titleScreen.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_back.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_backToTitle.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_easy.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_normal.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_hard.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_insane.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_facebook.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_help.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_noOff.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_noOn.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_pause.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_play.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_quit.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_resume.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_settings.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_yesOff.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "btn_yesOn.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "gameFont.fnt", BitmapFont.class);
        manager.load(Constants.ASSETS_PATH + "gameFont.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "life.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "lifeNo.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "sugar_idle.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "title.png", Texture.class);
        manager.load(Constants.ASSETS_PATH + "waterDrop.png", Texture.class);
        manager.load(Constants.SOUND_PATH + "runAmok.mp3", Music.class);
    }
    public static void dispose()
    {
        manager.dispose();
    }
}
