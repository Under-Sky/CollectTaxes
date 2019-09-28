package ru.itlab.coltax;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundEffects {
    Music music, sound;
    public SoundEffects(){
        music = Gdx.audio.newMusic(Gdx.files.internal(""));
        sound = Gdx.audio.newMusic(Gdx.files.internal(""));//TODO create all sounds
    }

    public void changeVolume(boolean isMusic, float volume){
        if(isMusic)music.setVolume(volume);
        else sound.setVolume(volume);
    }
}
