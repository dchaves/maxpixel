package org.ceremonia.maxpixel.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

public class OrthographicCameraInputProcessor implements InputProcessor {
	private OrthographicCamera camera;

	private float MAPWIDTH;
	private float MAPHEIGHT;
	private float VIEWWIDTH;
	private float VIEWHEIGHT;

	private OrthographicCameraInputProcessor() {
	}

	public OrthographicCameraInputProcessor(OrthographicCamera cam, float viewwidth, float viewheight, float xmax, float ymax) {
		this();
		this.camera = cam;
		this.MAPWIDTH = xmax;
		this.MAPHEIGHT = ymax;
		this.VIEWWIDTH = viewwidth;
		this.VIEWHEIGHT = viewheight;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
			camera.translate(-1, 0, 0);
			break;
		case Input.Keys.RIGHT:
			camera.translate(1, 0, 0);
			break;
		case Input.Keys.UP:
			camera.translate(0, 1, 0);
			break;
		case Input.Keys.DOWN:
			camera.translate(0, -1, 0);
			break;
		}
		clip();
		camera.update();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		camera.zoom += amount * 0.1f;
		clip();
		camera.update();
		return true;
	}

	private void clip() {
		camera.position.x = MathUtils.clamp(camera.position.x, VIEWWIDTH * camera.zoom, MAPWIDTH - (VIEWWIDTH * camera.zoom));
		camera.position.y = MathUtils.clamp(camera.position.y, VIEWHEIGHT * camera.zoom, MAPHEIGHT - (VIEWHEIGHT * camera.zoom));
	}
}
