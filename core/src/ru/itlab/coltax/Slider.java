package ru.itlab.coltax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Slider extends Actor {

    Texture dot, line, dot2, line2;
    BitmapFont value;
    GlyphLayout valueGlyph, value2Glyph;
    Preferences prefs;
    Stage stage;
    Vector2 touchPos, dotPos, dot2Pos;
    Rectangle linePos, line2Pos;
//    SoundEffects soundEffects;

    public Slider(Stage stage) {
        this.stage = stage;
//        soundEffects = new SoundEffects();

        line = new Texture(Gdx.files.internal("line1.png"));
        linePos = new Rectangle(31, 390, 300, 12);

        line2 = new Texture(Gdx.files.internal("line2.png"));
        line2Pos = new Rectangle(31, 241, 300, 12);

        dot = new Texture(Gdx.files.internal("dot1.png"));
        dotPos = new Vector2(linePos.x, linePos.y);

        dot2 = new Texture(Gdx.files.internal("dot2.png"));
        dot2Pos = new Vector2(line2Pos.x, line2Pos.y);

        prefs = Gdx.app.getPreferences("Volume");
        if (prefs.contains("Music")) load();

        value = new BitmapFont(Gdx.files.internal("regular.fnt"));
        valueGlyph = new GlyphLayout(value, (int)((dotPos.x - 16) / 2.99) + "%");
        value2Glyph = new GlyphLayout(value, (int)((dot2Pos.x - 16) / 2.99) + "%");
    }

    @Override
    public void act(float delta) {
        touchPos = new Vector2(0, 0);
        if (Gdx.input.isTouched(0)) {
            touchPos.set(Gdx.input.getX(0), Gdx.input.getY(0));
            touchPos.y = 640 - touchPos.y;
            if (linePos.contains(touchPos)) {
                dotPos.x = touchPos.x - 15;
//                soundEffects.changeVolume(true, (dotPos.x-16)/299);
                valueGlyph = new GlyphLayout(value, (int) ((dotPos.x - 16) / 2.99) + "%");
                prefs.putFloat("Music", (dotPos.x - 16) / 299);
                prefs.flush();
            }
            if (line2Pos.contains(touchPos)) {
                dot2Pos.x = touchPos.x - 15;
//                soundEffects.changeVolume(false, (dot2Pos.x-16)/299);
                value2Glyph = new GlyphLayout(value, (int) ((dot2Pos.x - 16) / 2.99) + "%");
                prefs.putFloat("Sound", (dot2Pos.x - 16) / 299);
                prefs.flush();
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(line, linePos.x, linePos.y, linePos.width, linePos.height);
        batch.draw(dot, dotPos.x, dotPos.y - 9, 30, 30);

        batch.draw(line2, line2Pos.x, line2Pos.y, line2Pos.width, line2Pos.height);
        batch.draw(dot2, dot2Pos.x, dot2Pos.y - 9, 30, 30);

        value.draw(batch, valueGlyph, 350 - valueGlyph.width, 440);
        value.draw(batch, value2Glyph, 350 - value2Glyph.width, 300);
    }

    public void load() {
        dotPos.x = (int) (299 * prefs.getFloat("Music")) + 16;
        dot2Pos.x = (int) (299 * prefs.getFloat("Sound")) + 16;

//        soundEffects.changeVolume(true, prefs.getFloat("Music"));
//        soundEffects.changeVolume(false, prefs.getFloat("Sound"));
    }
}
