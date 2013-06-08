package jonathan.geoffroy.androidstrategic.model.mapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;

import jonathan.geoffroy.androidstrategic.model.items.Item;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;

public class Map {

  public int[][] map;

  public HashMap<Coord2D, Magic> terrainSpecs;
  public HashMap<Coord2D, Item> terrainsItems;

   

  public static Map load(String scenarioName,  int chapterNum) {
	  Map loadedMap = new Map();

	  return loadedMap;
  }

}