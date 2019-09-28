package ru.itlab.coltax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class AboutUsScreen implements Screen {

    Stage stage;
    StretchViewport viewport;

    public class Logo extends Actor {
        Texture logo;
        public Logo() {
            logo = new Texture(Gdx.files.internal("AboutGame.png"));
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(logo, 0,0,360,640);
        }
    }

    @Override
    public void show() {
        viewport = new StretchViewport(360, 640);
        stage = new Stage(viewport);

        stage.addActor(new Logo());
        createImageButton(55,52,250,50,"exit");

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        stage.dispose();
    }

    public void createImageButton(float x, float y, float width, float height, String text) {
        ImageButton.ImageButtonStyle imageButtonStyle;
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.pressedOffsetY = -1;
//        imageButtonStyle.up = skin.getDrawable(text); //Set image
//        imageButtonStyle.up = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(text + ".png")))); //Set image
        imageButtonStyle.up = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(text + ".png")))); //Set image
        ImageButton imageButton = new ImageButton(imageButtonStyle);
        imageButton.setSize(width, height);
        imageButton.setPosition(x, y);
        //btn.setTransform(true);
        //btn.setScale(0.1f);

        imageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MainActivity.isExit = true;
            }
        });
        stage.addActor(imageButton);
    }
}
