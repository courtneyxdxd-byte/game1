package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenGame implements Screen {
    MyGdxGame myGdxGame;
    Bird bird;
    Tube[] tubes;
    int tubeCount;
    boolean isGameOver = false;

    ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        bird = new Bird(0, 0, 5 );
        tubeCount = 3;
        initTubes();
    }
    @Override
    public void show() {
    isGameOver = false;
        }



    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            System.out.println("Just touched");
            bird.onClick();
        }
        bird.fly();
        for (Tube tube : tubes) tube.move();
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }
    public void initTubes(){
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++){
            tubes[i] = new Tube(tubeCount, i);

        }
    }

    @Override
    public void dispose() {
        bird.dispose();
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
