package ru.itlab.coltax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Ball extends Actor {

    int type;
    Color color;
    BitmapFont font;
    String text;
    GlyphLayout glyph;
    Circle circle;
    float size, speed;
    ShapeRenderer renderer;
    boolean isTouched = false;

    public Ball(float speed) {
        this.speed = speed;
        circle = new Circle(0, 0, 0);
        font = new BitmapFont(Gdx.files.internal("bold.fnt"));
        chooseTax();
        font.getData().setScale(size / 3f);
        glyph = new GlyphLayout(font, text);
        renderer = new ShapeRenderer();
    }

    public void update(Batch batch, float delta) {
        if (!isTouched) circle.y -= delta * size * speed;

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.circle(circle.x, circle.y, circle.radius);
        renderer.end();

        batch.begin();
        font.draw(batch, glyph, circle.x - glyph.width / 2, circle.y + glyph.height / 2);
        batch.end();
    }

    public void chooseTax() {
        switch ((int) (Math.random() * 4)) {
            case 1:
                text = "НДС\n20%";
                color = Color.YELLOW;
                size = 1;
                type = 1;
                break;
            case 2:
                text = "Налог на\nимущество\n2,2%";
                color = Color.GREEN;
                size = 1.4f;
                type = 1;
                break;
            case 3:
                text = "НДФЛ\nс доходов\n13%";
                color = Color.RED;
                size = 1.7f;
                type = 1;
                break;
            default:
                text = "Налог на\nприбыль\n20%";
                color = Color.BLUE;
                size = 2;
                type = 1;
                break;
        }

        circle.x = size * 40 + (float) Math.random() * (360f - size * 80);
        circle.y = 700;
        circle.radius = size * 40;
    }
}
