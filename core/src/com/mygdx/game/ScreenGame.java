package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class ScreenGame implements Screen {
    MyGdxGame myGdxGame;
    Bird bird;
    Tube[] tubes;
    int tubeCount;
    boolean isGameOver = false;
    int gamePoints;
    PointCounter pointCounter;
    MovingBackground background;

    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;

    ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        bird = new Bird(0, 0, 5);
        tubeCount = 3;
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);        initTubes();
        background = new MovingBackground();
    }

    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;
    }


    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            System.out.println("Just touched");
            bird.onClick();
        }
        if (!isGameOver) {
            background.move();
            bird.fly();
            if (!bird.isInField()) {
                System.out.println("not in field");
                isGameOver = true;
            }
        }
        for (Tube tube : tubes) {
            tube.move();
            if (tube.isHit(bird)) {
                System.out.println("hit");
                isGameOver = true;

            } else if (tube.needAddPoint(bird)) {
                gamePoints++;
                tube.setPointReceived();
                System.out.println(gamePoints);
            }


        }
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);
        myGdxGame.batch.end();

    }

    public void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);

        }
    }

    @Override
    public void dispose() {
        bird.dispose();
        background.texture.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


}
