package de.projectp.escaped.game.tile;

import de.projectp.escaped.game.render.RenderAdapter;
import de.projectp.escaped.resource.ResourceManager;
import de.projectp.escaped.resource.image.SpriteImage;

public class RenderedTile extends RenderAdapter {

    private final Tile tile;
    private final SpriteImage image;

    private RenderedTile(Tile tile, SpriteImage image) {
        this.tile = tile;
        this.image = image;
    }

    @Override
    public void render() {

    }

    public static RenderedTile fromTile(Tile tile) {
        return new RenderedTile(tile, ResourceManager.image.get("tile_type:" + tile.getType()));
    }

}
