package ru.itlab.coltax;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		MainActivity ma = new MainActivity(new AndroidShare(this));
		initialize(ma, config);
	}

	public void ShareLink(){
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, "*Download link*");
		sendIntent.setType("text/plain");

		Intent shareIntent = Intent.createChooser(sendIntent, "");
		startActivity(shareIntent);
	}
}
