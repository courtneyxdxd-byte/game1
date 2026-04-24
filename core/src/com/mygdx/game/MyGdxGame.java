package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screens.ScreenGame;
import screens.ScreenMenu;
import screens.ScreenStart;
public class MyGdxGame extends Game {
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	public OrthographicCamera camera;
	public SpriteBatch batch;


	public ScreenGame screenGame;
	public ScreenStart screenStart;
	public ScreenMenu screenMenu;
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

		screenStart = new ScreenStart(this);
		screenGame = new ScreenGame(this);
		screenMenu = new ScreenMenu(this, camera);

		setScreen(screenMenu);

	}
	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
