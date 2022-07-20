package de.projectp.escaped.game.map;

import de.projectp.escaped.game.tile.Tile;

import java.util.List;

public class Level {

    private final TileMap map;
    private final List<Tile> tiles;

    public Level(TileMap map, List<Tile> tiles) {
        this.map = map;
        this.tiles = tiles;
    }

    public TileMap getMap() {
        return map;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

}
