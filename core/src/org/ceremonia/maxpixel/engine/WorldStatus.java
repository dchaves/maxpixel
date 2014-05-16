package org.ceremonia.maxpixel.engine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.ceremonia.maxpixel.input.TouchInfo;

public class WorldStatus {
	private static WorldStatus instance;
	public Map<Integer, TouchInfo> touches;

	private WorldStatus() {
		touches = new ConcurrentHashMap<Integer, TouchInfo>();
	}
	
	public static WorldStatus getInstance() {
		if(instance == null) {
			instance = new WorldStatus();
		}
		return instance;
	}
}
