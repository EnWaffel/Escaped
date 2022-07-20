package de.projectp.escaped.game.util;

public class Namespace {

    private final String name;
    private final String value;

    private Namespace(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static Namespace get(String namespace) {
        String[] split = namespace.split(":");
        return new Namespace(split[0], split[1]);
    }

    @Override
    public String toString() {
        return name + ":" + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Namespace)) return false;
        Namespace _obj = (Namespace) obj;
        return _obj.getName().equals(name) && _obj.getValue().equals(value);
    }

}
