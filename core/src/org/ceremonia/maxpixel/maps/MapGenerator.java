package org.ceremonia.maxpixel.maps;

public class MapGenerator {
	private byte[][] map;
	private final int MAPWIDTH = 32; // Width in number of tiles
	private final int MAPHEIGHT = 32; // Height in number of tiles
	private final int MINROOMS = 4; // Minimum number of rooms
	private final int MAXROOMS = 8; // Maximum number of rooms
	private final int MINROOMDIM = 4; // Minimum room dimension (width or height), in number of tiles
	private final int MAXROOMDIM = 8; // Maximum room dimension (width or height), in number of tiles
	
	public MapGenerator() {
		map = new byte[MAPWIDTH][MAPHEIGHT];
	}
}
