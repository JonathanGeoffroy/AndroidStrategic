package jonathan.geoffroy.androidstrategic.model.mapping;

import java.util.HashMap;

import jonathan.geoffroy.androidstrategic.model.items.Item;
import jonathan.geoffroy.androidstrategic.model.mapping.magic.Magic;
import jonathan.geoffroy.androidstrategic.model.utils.Coord2D;

public class Map {

  public int map;

  public HashMap<Coord2D, Magic> terrainSpecs;
  public HashMap<Coord2D, Item> terrainsItems;

   

  public void load(String scenarioName,  int chapterNum) {
  }

}