package ru.itlab.coltax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {

    boolean renderType = false;
    boolean isOOO = false;
    Stage stage;
    StretchViewport viewport;
    Group choose, gameGroup;
    float govCount, busCount;
    int capital, profit;
    ImageButton taxButton, infoButton;

    @Override
    public void show() {
        viewport = new StretchViewport(360, 640);
        stage = new Stage(viewport);
        choose = new Group();
        gameGroup = new Group();

        govCount = 55;
        busCount = 45;
        capital = 150000;
        profit = -30000;

        gameGroup.addActor(new GameBack());
        taxButton = createGameImageButton(295, 370, 40, 40, "tax");
        taxButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                MainActivity.changeGame = true;
            }
        });
        infoButton = createGameImageButton(295, 370, 40, 40, "tax");
        infoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
//                MainActivity.changeGame = true;
            }
        });
        gameGroup.addActor(infoButton);

        choose.addActor(new ChooseBack());
        createImageButton(80, 100, 200, 200, "ooo", false); //OOO
        createImageButton(80, 340, 200, 200, "ip", true); //IP

        stage.addActor(gameGroup);
        stage.addActor(choose);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (renderType) choose.toFront();
        else gameGroup.toFront();

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

    public void Load() {

    }

    public void createImageButton(float x, float y, float width, float height, String text, final boolean isIP) {
        ImageButton.ImageButtonStyle imageButtonStyle;
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.pressedOffsetY = -1;
        imageButtonStyle.up = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(text + ".png")))); //Set image
        ImageButton imageButton = new ImageButton(imageButtonStyle);
        imageButton.setSize(width, height);
        imageButton.setPosition(x, y);
        //btn.setTransform(true);
        //btn.setScale(0.1f);

        imageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isOOO = !isIP;
                renderType = false;
                Gdx.app.log("Choose", isOOO + "");
            }
        });
        choose.addActor(imageButton);
    }

    public ImageButton createGameImageButton(float x, float y, float width, float height, String text) {
        ImageButton.ImageButtonStyle imageButtonStyle;
        imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.pressedOffsetY = -1;
        imageButtonStyle.up = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal(text + ".png")))); //Set image
        ImageButton imageButton = new ImageButton(imageButtonStyle);
        imageButton.setSize(width, height);
        imageButton.setPosition(x, y);
        //btn.setTransform(true);
        //btn.setScale(0.1f);

        return imageButton;
    }

    public class ChooseBack extends Actor {
        Texture chooseBack;

        public ChooseBack() {
            chooseBack = new Texture(Gdx.files.internal("chooseBack.png"));
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(chooseBack, 0, 0, 360, 640);
        }
    }

    public class GameBack extends Actor {
        Texture gameBack, govLine, busLine;
        BitmapFont font;
        GlyphLayout glyphBus, glyphGov, glyphCap, glyphProf;

        public GameBack() {
            gameBack = new Texture(Gdx.files.internal("gameBack.png"));
            govLine = new Texture(Gdx.files.internal("govLine.png"));
            busLine = new Texture(Gdx.files.internal("busLine.png"));
            font = new BitmapFont(Gdx.files.internal("bold.fnt"));
            font.getData().setScale((float) 1 / 2);
        }

        @Override
        public void act(float delta) {
            font.setColor(Color.BLACK);
            glyphBus = new GlyphLayout(font, govCount + "%");
            glyphGov = new GlyphLayout(font, busCount + "%");
            font.setColor(Color.GREEN);
            glyphCap = new GlyphLayout(font, capital + "руб.");
            if (profit >= 0)
                glyphProf = new GlyphLayout(font, "+" + profit + "руб.");
            else {
                font.setColor(Color.RED);
                glyphProf = new GlyphLayout(font, profit + "руб.");
            }
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            batch.draw(gameBack, 0, 0, 360, 640);
            batch.draw(govLine, 40, 593, govCount * 2.8f, 10);
            batch.draw(busLine, 40, 546, busCount * 2.8f, 10);
            font.draw(batch, glyphGov, 255, 607 + glyphGov.height);
            font.draw(batch, glyphBus, 235, 560 + glyphBus.height);
            font.draw(batch, glyphCap, 180 - glyphCap.width / 2, 480 + glyphCap.height);
            font.draw(batch, glyphProf, 180 - glyphProf.width / 2, 455 + glyphProf.height);
        }
    }
}
