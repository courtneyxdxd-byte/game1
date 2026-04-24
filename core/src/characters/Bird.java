package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;

public class Bird {
    public int x;
    public int y;
    public int vy;
    public int speed;
    public int width = 200;
    public int height = 200;
    Texture[] framesArray;
    int jumpHeight;
    final int maxHeightOfJump = 100;
    boolean jump;
    int frameCounter;

    public Bird(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        framesArray = new Texture[]{
                new Texture("bird/bird0.png"),
                new Texture("bird/bird1.png"),
                new Texture("bird/bird2.png"),
                new Texture("bird/bird1.png"),
        };

    }
    public void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }

    public void fly() {
        if (jump) {
            y += speed * 4;
            if (y >= jumpHeight) {
                jump = false;
            }
        } else {
            y -= speed;
        }
    }

    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }


    public void dispose() {
        for (int i = 0; i < framesArray.length; i++) {
            framesArray[i].dispose();
        }
    }
    public boolean isInField() {
        if (y + height < 0) return false; //
        if (y > MyGdxGame.SCR_HEIGHT) return false; //
        return true;
    }
}



