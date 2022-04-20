package kuripa.narakutp.event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class NarakuTPEvent implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.VOID) return;

        // プレイヤーを取得
        Player player = (Player)event.getEntity();
        // 変数 Plugin を取得
        Plugin plugin = Bukkit.getPluginManager().getPlugin("NarakuTP");
        // 設定ファイル読み込み
        FileConfiguration config = plugin.getConfig();
        // TP先のワールドを取得
        String WorldName = config.getString("tp.world");
        World world = Bukkit.getWorld(WorldName);
        // ワールドが存在しない場合
        if (world == null) {
            plugin.getLogger().warning("config.yml で指定されているワールドは存在しませんでした");
            return;
        }

        switch (config.getString("tp.mode")) {
            case "custom":
                String[] TPxyz = {config.getString("tp.x"), config.getString("tp.y"), config.getString("tp.z")};
                Location location = new Location(world, Integer.parseInt(TPxyz[0]), Integer.parseInt(TPxyz[1]), Integer.parseInt(TPxyz[2]));
                player.teleport(location);
            case "spawnpoint":
                Location location1 = world.getSpawnLocation();
                player.teleport(location1);
        }
        event.setCancelled(true);
    }
}
