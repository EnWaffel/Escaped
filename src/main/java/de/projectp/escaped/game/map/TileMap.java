package de.projectp.escaped.game.map;

import de.projectp.escaped.G;
import de.projectp.escaped.game.render.GLManager;
import de.projectp.escaped.game.tile.RenderedTile;
import de.projectp.escaped.game.tile.Tile;
import de.projectp.escaped.game.tile.TileType;
import de.projectp.escaped.game.util.Namespace;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TileMap {

    private List<Level> mapLevels = new ArrayList<>();
    private final List<TileType<?>> loadedTypes = new ArrayList<>();
    private final List<RenderedTile> displayedTiles = new ArrayList<>();

    public void loadMap(JSONObject map) {
        loadMapTo(map, loadedTypes, mapLevels);
    }

    public void loadMapTo(JSONObject map, List<TileType<?>> loadedTypes, List<Level> mapLevels) {
        JSONArray levels = map.getJSONArray("levels");
        for (Object o : levels) {
            JSONObject level = new JSONObject(o.toString());
            JSONArray tiles = level.getJSONArray("tiles");
            for (Object _o : tiles) {
                JSONObject tile = new JSONObject(_o.toString());
                if (!typeLoaded(loadedTypes, Namespace.get(tile.getString("id")))) {
                    loadType(loadedTypes, Namespace.get(tile.getString("id")), null);
                }
            }
        }
    }

    public void displayTiles(int i) {
        displayTilesFrom(i, mapLevels);
    }

    public void displayTilesFrom(int i, List<Level> mapLevels) {
        Level level = mapLevels.get(i);
        if (level == null) { G.l.severe(""); return; }

        displayedTiles.clear();
        for (Tile tile : level.getTiles()) {
            RenderedTile renderedTile = RenderedTile.fromTile(tile);
            GLManager.attach(renderedTile);
            displayedTiles.add(renderedTile);
        }
    }

    public boolean typeLoaded(List<TileType<?>> loadedTypes, Namespace namespace) {
        for (TileType<?> tileType : loadedTypes) {
            if (tileType.getNamespace().equals(namespace)) {
                return true;
            }
        }
        return false;
    }

    public void loadType(List<TileType<?>> loadedTypes, Namespace namespace, Class<? extends Tile> tileClass) {
        if (typeLoaded(loadedTypes, namespace)) return;
        if (tileClass == null) {
            tileClass = Tile.class;
            G.l.warning("Adding not loaded tile-type to avoid crashes! (This might cause bugs!)");
        }
        loadedTypes.add(TileType.fromNamespace(namespace, tileClass));
    }

}
