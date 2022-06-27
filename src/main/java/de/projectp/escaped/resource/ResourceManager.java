package de.projectp.escaped.resource;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;
import de.projectp.escaped.G;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ResourceManager {

    public static Provider<SpriteImage> image;

    private final HashMap<String, Provider<?>> providers = new HashMap<>();
    private final List<String> repositories = new ArrayList<>();

    public ResourceManager() {
        repositories.add("image");

        image = provide("image", new Provider<SpriteImage>() {
            @Override
            public SpriteImage build(Properties args) {
                return SpriteImage.fromFile(args.get("file").any());
            }
        });
    }

    public <T> Provider<T> provide(String key, Provider<T> provider) {
        providers.put(key, provider);
        return provider;
    }

    public void loadPack(FileOrPath fileOrPath) {
        if (fileOrPath.getFile().isFile()) { G.logger.severe("Pack is not a directory"); return; }
        File packInfo = new File(fileOrPath.getPath() + "/pack.json");
        if (!packInfo.exists()) { G.logger.severe("no pack.json"); return; }
        JSONObject pack = FileUtil.readJSON(FileOrPath.file(packInfo));
        List<Object> excludedDirs = pack.getJSONArray("exclude").toList();
        for (File dir : Objects.requireNonNull(fileOrPath.getFile().listFiles())) {
            if (dir.isDirectory() && !excludedDirs.contains(dir.getName()) && repositories.contains(dir.getName())) {
                for (File file : Objects.requireNonNull(dir.listFiles())) {
                    if (file.isFile() && !file.getName().equalsIgnoreCase(".dir")) {
                        if (providers.containsKey(dir.getName())) {
                            providers.get(dir.getName()).buildResource(FileUtil.getCleanName(file), new Properties().set("file", FileOrPath.file(file)));
                        }
                    }
                }
            }
        }
    }

}
