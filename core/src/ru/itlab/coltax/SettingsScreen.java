package ru.itlab.coltax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class SettingsScreen implements Screen {

    Stage stage;
    StretchViewport viewport;
    Texture back;
    Slider slider, slider2;
    Preferences prefs;

    @Override
    public void show() {
        viewport = new StretchViewport(360, 640);
        stage = new Stage(viewport);

        back = new Texture(Gdx.files.internal("backSet.png"));

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle(
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("line1.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("dot1.png")))));
        slider = new Slider(0, 100, 1, false, sliderStyle);
        slider.setBounds(31, 390, 300, 12);
        stage.addActor(slider);

        sliderStyle = new Slider.SliderStyle(
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("line2.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("dot2.png")))));
        slider2 = new Slider(0, 100, 1, false, sliderStyle);
        slider2.setBounds(31, 241, 300, 12);
        stage.addActor(slider2);

        createImageButton(55, 52, 250, 50, "exit");

        prefs = Gdx.app.getPreferences("Volume");
        if (prefs.contains("Music")) load();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(back, 0, 0, 360, 640);
        stage.getBatch().end();
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

    public void createImageButton(float x, float y, float width, float height, String text) {
        ImageButton.ImageButtonStyle imageButtonStyle;
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.pressedOffsetY = -1;
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

    public void save(){
        prefs.putFloat("Music", slider.getValue());
        prefs.putFloat("Sound", slider2.getValue());
        prefs.flush();
    }

    public void load() {
        slider.setValue(prefs.getFloat("Music"));
        slider2.setValue(prefs.getFloat( "Sound"));

//        soundEffects.changeVolume(true, prefs.getFloat("Music"));
//        soundEffects.changeVolume(false, prefs.getFloat("Sound"));
    }
}
