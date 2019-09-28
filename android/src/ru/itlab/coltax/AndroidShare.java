package ru.itlab.coltax;

public class AndroidShare implements Share {

    AndroidLauncher al;

    public AndroidShare(AndroidLauncher al){
        this.al = al;
    }

    public void Send() {
        al.ShareLink();
    }
}