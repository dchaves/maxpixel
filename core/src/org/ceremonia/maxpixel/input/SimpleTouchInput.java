package org.ceremonia.maxpixel.input;

import org.ceremonia.maxpixel.engine.WorldStatus;

import com.badlogic.gdx.InputProcessor;

public class SimpleTouchInput implements InputProcessor {
	private WorldStatus status;
	
	public SimpleTouchInput() {
		status = WorldStatus.getInstance();
		for (int i = 0; i < 5; i++) {
			status.touches.put(i, new TouchInfo());
		}
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (pointer < 5) {
			status.touches.get(pointer).touchX = screenX;
			status.touches.get(pointer).touchY = screenX;
			status.touches.get(pointer).touched = true;
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (pointer < 5) {
			status.touches.get(pointer).touchX = 0;
			status.touches.get(pointer).touchY = 0;
			status.touches.get(pointer).touched = false;
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
