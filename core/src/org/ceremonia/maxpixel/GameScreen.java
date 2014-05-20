package org.ceremonia.maxpixel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
	private final MaxPixel game;
	private OrthographicCamera camera;
	private TiledMap map;
	OrthogonalTiledMapRenderer renderer;
	private Rectangle           glViewport;

	public GameScreen(MaxPixel game) {
		this.game = game;

		map = new TmxMapLoader().load(Gdx.files.internal("data/map/garden.tmx")
				.path());
		float unitScale = 1 / 32f;
		renderer = new OrthogonalTiledMapRenderer(map, unitScale);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 16, 16);
		camera.position.set(0, 0, 0);
		glViewport = new Rectangle(0, 0, 8, 8);
		renderer.setView(camera);
	}

	@Override
	public void render(float delta) {
		handleInput();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		renderer.render();
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.font.draw(game.batch, "Now playing... ", 100, 150);
		
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
	
	private void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //if (camera.position.x > 32 / 2)
            	camera.translate(-3, 0, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //if (camera.position.x < 32 - 32 / 2)
            	camera.translate(3, 0, 0);     }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //if (camera.position.y > 32 / 2)
            	camera.translate(0, -3, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //if (camera.position.y < 1024 - 32 / 2)
            	camera.translate(0, 3, 0);
        }
    }

}
