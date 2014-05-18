package org.ceremonia.maxpixel.engine.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;

public class Player {
	public int positionx;
	public int positiony;
	private TextureAtlas atlas;
	private float statetime;
	private Array<Sprite> sprites;
	private final float interSpriteTime = 0.1f;
	private int spriteindex;
	
	private Player(){}
	
	public Player(String atlaspath) {
		atlas = new TextureAtlas(Gdx.files.internal(atlaspath));
		sprites = atlas.createSprites("soldier_helmet_right_walk");
		statetime = 0f;
		spriteindex = 0;
	}
	
	public Sprite getView() {
		statetime += Gdx.graphics.getDeltaTime();
		if(statetime >= interSpriteTime) {
			spriteindex = (spriteindex + 1) % 3;
			statetime = 0f;
		}
		return sprites.get(spriteindex);
	}
	
	public void finalize() {
		atlas.dispose();
	}
}
