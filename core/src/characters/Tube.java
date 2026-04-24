package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

public class Tube {

    Texture textureUpperTube;
    Texture textureDownTube;
    int width = 200;
    int height = 700;
    int gapHeight = 400;
    int padding = 50;
    int x;
    int gapY;
    int speed = 5;
    boolean isPointReceived = false;
    int distanceBetweenTubes;
    Random random;

    public Tube(int tubeCount, int tubeIdx) {
        random = new Random();

        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;


        textureUpperTube = new Texture("tube/tube_flipped.png");
        textureDownTube = new Texture("tube/tube.png");

    }

    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);

    }

    public void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }

    public void move() {
        x -= speed;
        if (x < -width) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

    public boolean isHit(Bird bird) {
        if (bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;
        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;

        return false;
    }
    public boolean needAddPoint(Bird bird) {
        return !isPointReceived && bird.x >= x + width;

    }
    public void setPointReceived() {
        isPointReceived = true;
    }
}



