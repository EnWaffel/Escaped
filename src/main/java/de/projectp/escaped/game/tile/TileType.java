package de.projectp.escaped.game.tile;

import de.projectp.escaped.game.util.Namespace;

public class TileType<T extends Tile> {

    private final Namespace namespace;
    private final Class<T> associatedTileClass;

    public TileType(Namespace namespace, Class<T> associatedTileClass) {
        this.namespace = namespace;
        this.associatedTileClass = associatedTileClass;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public T newTile(Class<?>[] constructorClasses, Object[] constructorArguments) {
        try {
            return associatedTileClass.getDeclaredConstructor(constructorClasses).newInstance(constructorArguments);
        } catch (Exception e) {
            return null;
        }
    }

    public static <TT extends Tile> TileType<TT> fromNamespace(Namespace namespace, Class<TT> tileClass) {
        return new TileType<>(namespace, tileClass);
    }

}
