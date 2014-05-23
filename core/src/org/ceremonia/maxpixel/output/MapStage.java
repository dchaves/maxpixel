package org.ceremonia.maxpixel.output;

import org.ceremonia.maxpixel.input.OrthographicCameraGestureController;
import org.ceremonia.maxpixel.input.OrthographicCameraInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapStage extends Stage {
	public final int MAPWIDTH = 32; // Number of tiles
	public final int MAPHEIGHT = 32; // Number of tiles
	public final float SCALE = 1 / 16f; // 1 unit of movement equals 0.5 tiles
	private final float VIEWWIDTH = 8; // Number of visible tiles in one screen (width)
	private final float VIEWHEIGHT = 8; // Number of visible tiles in one screen (height)
	

	private OrthographicCamera camera;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCameraGestureController controller;
	private OrthographicCameraInputProcessor processor;
	private GestureDetector gestureDetector;
	private InputMultiplexer multiplexer;
	
	public MapStage() {
		super();
		map = new TmxMapLoader().load(Gdx.files.internal("data/map/garden.tmx").path());
		renderer = new OrthogonalTiledMapRenderer(map, SCALE);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWWIDTH * MAPWIDTH * SCALE, VIEWHEIGHT * MAPHEIGHT * SCALE);
		camera.position.set(VIEWHEIGHT, VIEWWIDTH, 0);
		renderer.setView(camera);
		
		this.setViewport(new FitViewport(640, 480, camera));
		
		controller = new OrthographicCameraGestureController(camera, VIEWWIDTH, VIEWHEIGHT, MAPWIDTH * (MAPWIDTH * SCALE), MAPHEIGHT * MAPHEIGHT * SCALE);
		processor = new OrthographicCameraInputProcessor(camera, VIEWWIDTH, VIEWHEIGHT, MAPWIDTH * (MAPWIDTH * SCALE), MAPHEIGHT * MAPHEIGHT * SCALE);
		gestureDetector = new GestureDetector(controller);
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(this);
		multiplexer.addProcessor(gestureDetector);
		multiplexer.addProcessor(processor);
		Gdx.input.setInputProcessor(multiplexer);
	}
	
	public void dispose() {
		super.dispose();
		map.dispose();
		renderer.dispose();
	}
	
	public void draw() {
		renderer.render();
		camera.update();
		renderer.setView(camera);
		
		super.draw();
	}
	
	public void addActor(Actor actor) {
		actor.setWidth(actor.getWidth()*SCALE);
		actor.setHeight(actor.getHeight()*SCALE);
		super.addActor(actor);
	}
}
