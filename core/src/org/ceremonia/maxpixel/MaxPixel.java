package org.ceremonia.maxpixel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MaxPixel extends Game {
	// GAME GENERAL PROPERTIES
	static final int WIDTH  = 640;
    static final int HEIGHT = 480;
	
    // Class variables
	protected SpriteBatch batch;
	protected BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("data/fonts/arial-15.fnt"), Gdx.files.internal("data/fonts/arial-15.png"), false, true);
		font.setColor(Color.WHITE);
		
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void resize(int width, int height) {

	}
}
