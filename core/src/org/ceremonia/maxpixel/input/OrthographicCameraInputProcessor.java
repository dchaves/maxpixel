package org.ceremonia.maxpixel.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class OrthographicCameraInputProcessor implements InputProcessor {
	private OrthographicCamera camera;
	private BoundingBox xtop;
	private BoundingBox xbottom;
	private BoundingBox ytop;
	private BoundingBox ybottom;

	private OrthographicCameraInputProcessor() {
	}

	public OrthographicCameraInputProcessor(OrthographicCamera cam, float xmax, float ymax) {
		this();
		this.camera = cam;
		this.xtop = new BoundingBox(new Vector3(xmax, 0, 0), new Vector3(xmax, ymax, 0));
		this.xbottom = new BoundingBox(new Vector3(0, 0, 0), new Vector3(0, ymax, 0));
		this.ytop = new BoundingBox(new Vector3(0, 0, 0), new Vector3(xmax, 0, 0));
		this.ybottom = new BoundingBox(new Vector3(0, ymax, 0), new Vector3(xmax, ymax, 0));
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Input.Keys.LEFT:
			camera.translate(-1, 0, 0);
			if (camera.frustum.boundsInFrustum(xbottom)) {
				camera.translate(1, 0, 0);
			}
			break;
		case Input.Keys.RIGHT:
			camera.translate(1, 0, 0);
			if (camera.frustum.boundsInFrustum(xtop)) {
				camera.translate(-1, 0, 0);
			}
			break;
		case Input.Keys.UP:
			camera.translate(0, 1, 0);
			if (camera.frustum.boundsInFrustum(ybottom)) {
				camera.translate(0, -1, 0);
			}
			break;
		case Input.Keys.DOWN:
			camera.translate(0, -1, 0);
			if (camera.frustum.boundsInFrustum(ytop)) {
				camera.translate(0, 1, 0);
			}
			break;
		}
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
		return true;
	}
}
