package org.ceremonia.maxpixel.engine.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class DefaultActor extends Actor {
	private TextureAtlas atlas;
	private float statetime;
	private Array<Sprite> sprites;
	private final float interSpriteTime = 0.1f;
	private int spriteindex;
	
	public DefaultActor (String atlaspath) {
		super();
		atlas = new TextureAtlas(Gdx.files.internal(atlaspath));
		sprites = atlas.createSprites("soldier_helmet_right_walk");
		statetime = 0f;
		spriteindex = 0;
		this.setBounds(0, 0, sprites.first().getWidth(), sprites.first().getHeight());
	}
	
	private Sprite getView() {
		statetime += Gdx.graphics.getDeltaTime();
		if(statetime >= interSpriteTime) {
			spriteindex = (spriteindex + 1) % 3;
			statetime = 0f;
		}
		return sprites.get(spriteindex);
	}
	
	@Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(getView(), getX(), getY(), getOriginX(), getOriginY(),
            getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
	
	public void finalize() {
		atlas.dispose();
	}
	
	@Override
	public void moveBy(float x, float y) {
		Gdx.app.log("DefaultActor", "Moved by "+x+","+y);
	}
}
