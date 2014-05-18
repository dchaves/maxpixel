package org.ceremonia.maxpixel.engine.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character {
	private int positionx;
	private int positiony;
	private String spritesheet;
	
	private Character(){}
	
	public Character(String sprite) {
		spritesheet = sprite;
		walkSheet = new Texture(Gdx.files.internal("data/sprites/americanset.png"));
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
		walkFrames = new TextureRegion[3 * 1];
		walkFrames[0] = tmp[6][0];
		walkFrames[1] = tmp[6][1];
		walkFrames[2] = tmp[6][2];
		walkAnimation = new Animation(0.1f, walkFrames);
		statetime = 0f;
	}
	
	public TextureRegion getView() {
		statetime += Gdx.graphics.getDeltaTime();
		currentFrame = walkAnimation.getKeyFrame(statetime, true);
		return currentFrame;
	}
}
