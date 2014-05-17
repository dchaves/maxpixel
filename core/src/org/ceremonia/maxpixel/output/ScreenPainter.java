package org.ceremonia.maxpixel.output;

import org.ceremonia.maxpixel.engine.WorldStatus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.GL20;

public class ScreenPainter {
	private SpriteBatch batch;
	private BitmapFont font;
	private int w;
	private int h;
	private Animation walkAnimation;
	private Texture walkSheet; 
	private TextureRegion[] walkFrames;
	private TextureRegion currentFrame;
	private static final int FRAME_COLS = 12;
	private static final int FRAME_ROWS = 8;
	private float statetime;

	public ScreenPainter() {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("data/fonts/arial-15.fnt"),
				Gdx.files.internal("data/fonts/arial-15.png"), false, true);
		font.setColor(Color.WHITE);

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		walkSheet = new Texture(Gdx.files.internal("data/sprites/americanset.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
		walkFrames = new TextureRegion[3 * 1];
		walkFrames[0] = tmp[6][0];
		walkFrames[1] = tmp[6][1];
		walkFrames[2] = tmp[6][2];
		walkAnimation = new Animation(0.1f, walkFrames);
		statetime = 0f;
	}

	public void drawWorld() {
		WorldStatus status = WorldStatus.getInstance();
		String message = "";

		for (int i = 0; i < 5; i++) {
			if (status.touches.get(i).touched) {
				message += "Finger:" + Integer.toString(i) + " touch at:"
						+ Float.toString(status.touches.get(i).touchX) + ","
						+ Float.toString(status.touches.get(i).touchY) + "\n";
			}
		}

		if (message.length() == 0) {
			message = "Nothing";
		}

		statetime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(statetime, true);
		
		
		batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		TextBounds tb = font.getBounds(message);
		float x = w / 2 - tb.width / 2;
		float y = h / 2 + tb.height / 2;
		font.drawMultiLine(batch, message, x, y);
		batch.draw(currentFrame,50,50);
		batch.end();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}
