package de.projectp.escaped.resource;

import de.enwaffel.randomutils.Properties;

import java.util.HashMap;

public abstract class Provider<T> {

    private final HashMap<String, T> resources = new HashMap<>();

    public abstract T build(Properties args);

    protected abstract T orDefault();

    public T buildResource(String key, Properties args) {
        T resource = build(args);
        resources.put(key, resource);
        return resource;
    }

    public T get(String key) {
        return resources.getOrDefault(key, orDefault());
    }

    public HashMap<String, T> getResources() {
        return resources;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "resources=" + resources +
                '}';
    }

}
