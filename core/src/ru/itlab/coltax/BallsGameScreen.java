package ru.itlab.coltax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class BallsGameScreen implements Screen {

    Stage stage;
    StretchViewport viewport;
    Array<Ball> balls;
    BottleForBalls bfb;

    public float speed = 50 + MainActivity.months * 3;
    Vector2 touchPos, touchOnce;

    @Override
    public void show() {
        viewport = new StretchViewport(360, 640);
        stage = new Stage(viewport);

        balls = new Array<>();
        balls.add(new Ball(speed));
        bfb = new BottleForBalls((int) Math.random() * 6);
        stage.addActor(bfb);

        touchPos = new Vector2(0, 0);
        touchOnce = new Vector2(-100, -100);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (balls.get(balls.size - 1).circle.y < 550)
            balls.add(new Ball(speed));

        for (Ball ball : balls) {
            if (Gdx.input.isTouched(0) && !touchOnce.equals(new Vector2(Gdx.input.getX(0), 640 - Gdx.input.getY(0)))
                    && ball.circle.contains(Gdx.input.getX(0), 640 - Gdx.input.getY(0))) {
                ball.isTouched = true;
                ball.circle = new Circle(0, 110, 40);
                balls.add(new Ball(speed));
                bfb.add(ball);//TODO add listener for every button instead "isTouched"
                touchOnce = new Vector2(Gdx.input.getX(0), 640 - Gdx.input.getY(0));
            }

            ball.update(stage.getBatch(), delta);
        }

        stage.act(delta);
        stage.draw();
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

    @Override
    public void dispose() {

    }
}
