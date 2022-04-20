package kuripa.narakutp;

import kuripa.narakutp.commands.NarakuTPComamnd;
import kuripa.narakutp.event.NarakuTPEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class NarakuTP extends JavaPlugin {

    private static Plugin plugin;
    private static FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("NarakuTPプラグインが起動しました v1.0.0");

        plugin = this;

        // 設定ファイル
        saveDefaultConfig();
        config = getConfig();

        // コマンド
        getCommand("narakutp").setExecutor(new NarakuTPComamnd());

        Bukkit.getServer().getPluginManager().registerEvents(new NarakuTPEvent(), this);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
