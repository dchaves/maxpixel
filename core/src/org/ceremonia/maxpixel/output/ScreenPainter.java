//package org.ceremonia.maxpixel.output;
//
//import org.ceremonia.maxpixel.engine.WorldStatus;
//import org.ceremonia.maxpixel.engine.characters.Player;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.GL20;
//
//public class ScreenPainter {
//	private SpriteBatch batch;
//	private BitmapFont font;
//	private int w;
//	private int h;
//
//	public ScreenPainter() {
//		batch = new SpriteBatch();
//		font = new BitmapFont(Gdx.files.internal("data/fonts/arial-15.fnt"),
//				Gdx.files.internal("data/fonts/arial-15.png"), false, true);
//		font.setColor(Color.WHITE);
//
//		w = Gdx.graphics.getWidth();
//		h = Gdx.graphics.getHeight();
//		
//		
//	}
//
//	public void drawWorld() {
//		WorldStatus world = WorldStatus.getInstance();
//		String message = "";
//
//		for (int i = 0; i < 5; i++) {
//			if (world.touches.get(i).touched) {
//				message += "Finger:" + Integer.toString(i) + " touch at:"
//						+ Float.toString(world.touches.get(i).touchX) + ","
//						+ Float.toString(world.touches.get(i).touchY) + "\n";
//			}
//		}
//
//		if (message.length() == 0) {
//			message = "Nothing";
//		}		
//		
//		batch.begin();
//		Gdx.gl.glClearColor(0, 0, 0, 0);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		TextBounds tb = font.getBounds(message);
//		float x = w / 2 - tb.width / 2;
//		float y = h / 2 + tb.height / 2;
//		font.drawMultiLine(batch, message, x, y);
//		for(Player player : world.getPlayers()) {
//			batch.draw(player.getView(),player.positionx,player.positiony);
//		}
//		batch.end();
//	}
//
//	public void dispose() {
//		batch.dispose();
//		font.dispose();
//	}
//}
