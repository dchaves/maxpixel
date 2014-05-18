package org.ceremonia.maxpixel.engine;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.ceremonia.maxpixel.engine.characters.Player;
import org.ceremonia.maxpixel.input.TouchInfo;

public class WorldStatus {
	private static WorldStatus instance;
	public Map<Integer, TouchInfo> touches;
	private HashSet<Player> players;

	private WorldStatus() {
		touches = new ConcurrentHashMap<Integer, TouchInfo>();
		players = new HashSet<Player>();
		
		Player p = new Player("data/sprites/soldier_helmet_walk");
		p.positionx = 50;
		p.positiony = 50;
		players.add(p);
		
		p = new Player("data/sprites/soldier_helmet_walk");
		p.positionx = 100;
		p.positiony = 100;
		players.add(p);
	}
	
	public static WorldStatus getInstance() {
		if(instance == null) {
			instance = new WorldStatus();
		}
		return instance;
	}
	
	public Set<Player> getPlayers() {
		return players;
	}
}
