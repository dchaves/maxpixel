package org.ceremonia.maxpixel;

import org.ceremonia.maxpixel.engine.BasicEngine;
import org.ceremonia.maxpixel.input.SimpleTouchInput;
import org.ceremonia.maxpixel.output.ScreenPainter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class MaxPixel implements ApplicationListener {
	private InputProcessor inputprocessor;
	private BasicEngine engine;
	private ScreenPainter painter;

	@Override
	public void create() {
		inputprocessor = new SimpleTouchInput();
		engine = new BasicEngine();
		painter = new ScreenPainter();
		
		Gdx.input.setInputProcessor(inputprocessor);
	}

	@Override
	public void render() {		
		engine.update();
		painter.drawWorld();
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
		painter.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		painter = new ScreenPainter();
	}
}
