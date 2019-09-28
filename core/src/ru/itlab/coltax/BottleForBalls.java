package ru.itlab.coltax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BottleForBalls extends Actor {
    int correctType;
    Ball[] last3Objects;
    int correctAnses, wrongAnses, maxAnses, check = 0;
    Texture bottle, water, stand;

    public BottleForBalls(int type) {
        maxAnses = 5 + ((int) Math.random() * MainActivity.months) % 25;
        correctAnses = 0;
        wrongAnses = 0;
        last3Objects = new Ball[3];
        correctType = type;

        bottle = new Texture(Gdx.files.internal("bottle.png"));
        stand = new Texture(Gdx.files.internal("stand.png"));
        water = new Texture(Gdx.files.internal("water.png"));
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float a = correctAnses + wrongAnses + check;
        a /= maxAnses;
        batch.draw(bottle, 7, 5, 30, 303);
        batch.draw(water, 20, 10, 5, a * 303);
        batch.draw(stand, 86, 0, 240, 70);
    }

    public void add(Ball ball) {
        if (last3Objects[0] == null) {
            last3Objects[0] = ball;
            last3Objects[0].circle.x = 126;
            check++;
        } else if (last3Objects[1] == null) {
            last3Objects[1] = ball;
            last3Objects[1].circle.x = 206;
            check++;
        } else if (last3Objects[2] == null) {
            last3Objects[2] = ball;
            last3Objects[2].circle.x = 286;
            check++;
        } else {
            if (last3Objects[0].type == correctType) correctAnses++;
            else wrongAnses++;
//            last3Objects[0].dispose();//TODO dispose
            last3Objects[0] = last3Objects[1];
            last3Objects[1] = last3Objects[2];
            last3Objects[2] = ball;
            for (int i = 0; i < 3; i++)
                last3Objects[i].circle.x = 126 + 80 * i;
        }
//        if(maxAnses == correctAnses+wrongAnses+3)MainActivity.changeGame = true;
    }
}
