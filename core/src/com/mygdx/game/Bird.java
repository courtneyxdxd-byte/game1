package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    int x, y;
    int speed;
    int width = 200;
    int height = 200;
    Texture[] framesArray;
    int jumpHeight;
    final int maxHeightOfJump = 200;
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
    void onClick() {
        jump = true;
        jumpHeight = maxHeightOfJump + y;
    }
    public void fly() {
        x += speed;
        y += speed;
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
}
