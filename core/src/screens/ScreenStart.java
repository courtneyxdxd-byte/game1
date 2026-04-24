package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.utils.ScreenUtils;

import com.badlogic.gdx.Screen;

public class ScreenStart implements Screen {
    MyGdxGame myGdxGame;
    components.TextButton buttonRestart;
    components.TextButton buttonMenu;
    components.MovingBackground background;
    components.PointCounter pointCounter;

    public ScreenStart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonRestart = new components.TextButton(500, 300, "RESTART");
        buttonMenu = new components.TextButton(500, 150, "MENU");
        background = new components.MovingBackground("background/restart_bg.png");
        pointCounter = new components.PointCounter(MyGdxGame.SCR_WIDTH - 400, MyGdxGame.SCR_HEIGHT - 60);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        com.badlogic.gdx.utils.ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonMenu.draw(myGdxGame.batch);
        buttonRestart.draw(myGdxGame.batch);


        pointCounter.draw(myGdxGame.batch, myGdxGame.screenGame.gamePoints);
        myGdxGame.batch.end();

        if (com.badlogic.gdx.Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (buttonRestart.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (buttonMenu.isHit(touch.x, touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }


    }


    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
