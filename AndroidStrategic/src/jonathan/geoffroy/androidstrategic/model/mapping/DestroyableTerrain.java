package jonathan.geoffroy.androidstrategic.model.mapping;

public abstract class DestroyableTerrain extends Terrain {

  protected int hp;

  public abstract void onDestroyed();

}