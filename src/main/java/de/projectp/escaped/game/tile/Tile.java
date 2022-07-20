package de.projectp.escaped.game.tile;

import de.projectp.escaped.game.collision.Collidable;

public class Tile extends Collidable {

    private final TileType<?> type;

    public Tile(TileType<?> type) {
        this.type = type;
    }

    public TileType<?> getType() {
        return type;
    }

}
