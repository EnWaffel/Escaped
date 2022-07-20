package de.projectp.escaped.resource;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;
import de.projectp.escaped.G;
import de.projectp.escaped.resource.image.SpriteImage;
import de.projectp.escaped.util.GLUtil;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ResourceManager {

    public static Provider<SpriteImage> image;
    public static Provider<SpriteImage> tile;

    private final HashMap<String, Provider<?>> providers = new HashMap<>();
    private final List<String> repositories = new ArrayList<>();

    public ResourceManager() {
        repositories.add("image");
        repositories.add("tile");

        image = provide("image", new Provider<>() {
            @Override
            public SpriteImage build(Properties args) {
                return SpriteImage.fromFile(args.get("file").any());
            }

            @Override
            protected SpriteImage orDefault() {
                return GLUtil.missingTexture(4, 4);
            }
        });
        image = provide("tile", new Provider<>() {
            @Override
            public SpriteImage build(Properties args) {
                return args.get("file").isNull() ? SpriteImage.fromInputStream(args.get("input_stream").any()) : SpriteImage.fromFile(args.get("file").any());
            }

            @Override
            protected SpriteImage orDefault() {
                return GLUtil.missingTexture(4, 4);
            }
        });
        G.rs = this;
    }

    public <T> Provider<T> provide(String key, Provider<T> provider) {
        providers.put(key, provider);
        return provider;
    }

    public void loadPack(FileOrPath fileOrPath) {
        if (fileOrPath.getFile().isFile()) { G.l.severe("Pack is not a directory"); return; }
        File packInfo = new File(fileOrPath.getPath() + "/pack.json");
        if (!packInfo.exists()) { G.l.severe("no pack.json"); return; }
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
