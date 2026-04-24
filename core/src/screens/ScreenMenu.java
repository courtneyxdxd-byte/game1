
        package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

public class ScreenMenu implements Screen {
  MyGdxGame myGdxGame;
  OrthographicCamera camera;


  components.TextButton buttonStart;
  components.TextButton buttonExit;
  components.MovingBackground background;

  public ScreenMenu(MyGdxGame myGdxGame, OrthographicCamera camera) {
    this.myGdxGame = myGdxGame;
    this.camera = camera;

    buttonStart = new components.TextButton(500, 450, "START");
    buttonExit = new components.TextButton(500, 250, "EXIT");
    background = new components.MovingBackground("background/restart_bg.png");
  }

  @Override
  public void render(float delta) {
    ScreenUtils.clear(0, 0, 0.2f, 1);
    camera.update();
    myGdxGame.batch.setProjectionMatrix(camera.combined);

    myGdxGame.batch.begin();
    background.draw(myGdxGame.batch);
    buttonStart.draw(myGdxGame.batch);
    buttonExit.draw(myGdxGame.batch);
    myGdxGame.batch.end();

    if (Gdx.input.justTouched()) {
      Vector3 touch = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

      if (buttonStart.isHit(touch.x, touch.y)) {
        myGdxGame.setScreen(myGdxGame.screenGame);
      }
      if (buttonExit.isHit(touch.x, touch.y)) {
        Gdx.app.exit();
      }
    }
  }

  @Override public void show() {}
  @Override public void resize(int width, int height) {}
  @Override public void pause() {}
  @Override public void resume() {}
  @Override public void hide() {}
  @Override public void dispose() {}
}