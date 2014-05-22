package org.ceremonia.maxpixel.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class OrthographicCameraGestureController implements GestureListener {
	private float velX, velY;
	private boolean flinging = false;
	private float initialScale = 1;
	private OrthographicCamera camera;

	private float MAPWIDTH;
	private float MAPHEIGHT;
	private float VIEWWIDTH;
	private float VIEWHEIGHT;

	private OrthographicCameraGestureController() {
	}

	public OrthographicCameraGestureController(OrthographicCamera cam, float viewwidth, float viewheight, float xmax, float ymax) {
		this();
		this.camera = cam;
		this.MAPWIDTH = xmax;
		this.MAPHEIGHT = ymax;
		this.VIEWWIDTH = viewwidth;
		this.VIEWHEIGHT = viewheight;
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

		camera.position.x = MathUtils.clamp(camera.position.x, VIEWWIDTH / 2, (MAPWIDTH - VIEWWIDTH) / 2);
		camera.position.y = MathUtils.clamp(camera.position.y, VIEWHEIGHT / 2, (MAPHEIGHT - VIEWHEIGHT) / 2);
		Gdx.app.log("CAMERA", "Camera moved to " + camera.position.x + "," + camera.position.y);
		// camera.update();
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
