package jonathan.geoffroy.androidstrategic.model.games;

import jonathan.geoffroy.androidstrategic.model.fighters.Team;
import jonathan.geoffroy.androidstrategic.model.items.bags.Bag;
import jonathan.geoffroy.androidstrategic.model.mapping.Map;



public class Game {

  public Team player;

  public Map map;

  public Bag bag;

  public int money;

  public String name;

  public Team ennemy;

  public void load() {
  }

  public void save(String playerName) {
  }

}