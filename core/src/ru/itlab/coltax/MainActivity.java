package ru.itlab.coltax;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class MainActivity extends Game implements ApplicationListener {
    private Share share;

    public MainActivity(Share share) {
        this.share = share;
    }

    MenuScreen ms;
    GameScreen gs;
    AboutUsScreen aus;
    SettingsScreen ss;
    BallsGameScreen bgs;
    public static boolean isExit = false;
    public static int months = 1, changeGame = 0;

    @Override
    public void create() {
        ms = new MenuScreen();
        gs = new GameScreen();
        aus = new AboutUsScreen();
        ss = new SettingsScreen();
        bgs = new BallsGameScreen();
        setScreen(ms);
    }

    @Override
    public void render() {
        super.render();

        if (getScreen().equals(ms))
            switch (MenuScreen.screen) {
                case 1:
                    gs.renderType = true;
                    setScreen(gs);
                    MenuScreen.screen = 0;
                    break;
                case 2:
                    gs.Load();
                    gs.renderType = false;
                    setScreen(gs);
                    MenuScreen.screen = 0;
                    break;
                case 3:
                    setScreen(aus);
                    MenuScreen.screen = 0;
                    break;
                case 4:
                    share.Send();
                    MenuScreen.screen = 0;
                    break;
                case 5:
                    setScreen(ss);
                    MenuScreen.screen = 0;
                    break;
            }

        switch (changeGame){
            case 1:
                setScreen(gs);
                bgs.dispose();
            case 2:
            case 3:
        }

        if(isExit){
            setScreen(ms);
            isExit = false;
        }
    }

    public boolean isEscape() {
        return false;
    }

}
