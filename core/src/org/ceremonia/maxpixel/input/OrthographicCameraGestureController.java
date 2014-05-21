package org.ceremonia.maxpixel.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class OrthographicCameraGestureController implements GestureListener {
	private float xlimit_max;
	private float xlimit_min;
	private float ylimit_max;
	private float ylimit_min;
	private float velX, velY;
	private boolean flinging = false;
	private float initialScale = 1;
	private OrthographicCamera camera;

	private OrthographicCameraGestureController() {
	}

	public OrthographicCameraGestureController(OrthographicCamera cam, float xmax, float xmin, float ymax, float ymin) {
		this();
		this.camera = cam;
		this.xlimit_max = xmax;
		this.xlimit_min = xmin;
		this.ylimit_max = ymax;
		this.ylimit_min = ymin;
	}

	public boolean touchDown(float x, float y, int pointer, int button) {
		flinging = false;
		initialScale = camera.zoom;
		return true;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Gdx.app.log("GestureDetectorTest", "tap at " + x + ", " + y + ", count: " + count);
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		Gdx.app.log("GestureDetectorTest", "long press at " + x + ", " + y);
		return true;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		Gdx.app.log("GestureDetectorTest", "fling " + velocityX + ", " + velocityY);
		flinging = true;
		velX = camera.zoom * velocityX * 0.5f;
		velY = camera.zoom * velocityY * 0.5f;
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// Gdx.app.log("GestureDetectorTest", "pan at " + x + ", " + y);
		camera.position.add(-deltaX * camera.zoom * 0.1f, deltaY * camera.zoom * 0.1f, 0);
		return true;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		Gdx.app.log("GestureDetectorTest", "pan stop at " + x + ", " + y);
		return false;
	}

	@Override
	public boolean zoom(float originalDistance, float currentDistance) {
		float ratio = originalDistance / currentDistance;
		camera.zoom = initialScale * ratio;
		System.out.println(camera.zoom);
		return true;
	}

	@Override
	public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer, Vector2 firstPointer, Vector2 secondPointer) {
		return false;
	}

	public void update() {
		if (flinging) {
			velX *= 0.98f;
			velY *= 0.98f;
			camera.position.add(-velX * Gdx.graphics.getDeltaTime(), velY * Gdx.graphics.getDeltaTime(), 0);
			if (Math.abs(velX) < 0.01f)
				velX = 0;
			if (Math.abs(velY) < 0.01f)
				velY = 0;
		}
	}
}
