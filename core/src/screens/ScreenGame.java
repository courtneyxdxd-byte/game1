package screens;

import characters.Bird;
import characters.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import components.MovingBackground;
import components.PointCounter;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class ScreenGame implements Screen {
    MyGdxGame myGdxGame;
    float speed = 5.0f;
    Bird bird;
    Tube[] tubes;
    int tubeCount;
    boolean isGameOver = false;
    public int gamePoints;
    PointCounter pointCounter;
    MovingBackground background;

    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        bird = new Bird(0, 0, 5);
        tubeCount = 3;
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);        initTubes();
        background = new MovingBackground("game_bg.png");

    }

    @Override
    public void show() {
        bird.x = 0;
        bird.y = 0;
        bird.speed = 5;
        speed = 5.0f;

        initTubes();
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
                bird.width += 20;
                bird.height += 20;
                speed += 0.5f;
                tube.setPointReceived();
                System.out.println(gamePoints);
            }
        }
        if (isGameOver){
            myGdxGame.setScreen(myGdxGame.screenStart);
        };
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
        background.dispose();
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
