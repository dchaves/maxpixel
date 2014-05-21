package org.ceremonia.maxpixel;

import org.ceremonia.maxpixel.input.OrthographicCameraGestureController;
import org.ceremonia.maxpixel.input.OrthographicCameraInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen {
	public final int MAPWIDTH = 32; // Number of tiles
	public final int MAPHEIGHT = 32; // Number of tiles
	public final float SCALE = 1 / 16f; // 1 unit of movement equals 0.5 tiles
	private final float VIEWWIDTH = 8; // Number of visible tiles in one screen (width)
	private final float VIEWHEIGHT = 8; // Number of visible tiles in one screen (height)
	
	private final MaxPixel game;
	private OrthographicCamera camera;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCameraGestureController controller;
	private OrthographicCameraInputProcessor processor;
	private GestureDetector gestureDetector;
	private InputMultiplexer multiplexer;

	public GameScreen(MaxPixel game) {
		this.game = game;

		map = new TmxMapLoader().load(Gdx.files.internal("data/map/garden.tmx").path());
		renderer = new OrthogonalTiledMapRenderer(map, SCALE);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWWIDTH * MAPWIDTH * SCALE, VIEWHEIGHT * MAPHEIGHT * SCALE);
		camera.position.set(VIEWHEIGHT, VIEWWIDTH, 0);
		renderer.setView(camera);

		controller = new OrthographicCameraGestureController(camera, MAPWIDTH - VIEWWIDTH, VIEWWIDTH, -MAPHEIGHT + VIEWHEIGHT, -VIEWHEIGHT);
		processor = new OrthographicCameraInputProcessor(camera, MAPWIDTH, MAPHEIGHT);
		multiplexer = new InputMultiplexer();
		gestureDetector = new GestureDetector(20, 0.5f, 1, 0.15f, controller);

		multiplexer.addProcessor(gestureDetector);
		multiplexer.addProcessor(processor);

		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		renderer.render();
		camera.update();
		renderer.setView(camera);
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
		map.dispose();
		renderer.dispose();

	}

}
