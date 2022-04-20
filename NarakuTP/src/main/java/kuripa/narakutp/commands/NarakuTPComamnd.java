package kuripa.narakutp.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class NarakuTPComamnd implements CommandExecutor {

    public static void noPermission(CommandSender sender) {
        sender.sendMessage(ChatColor.RED+"あなたにはこのコマンドを実行する権限がありません");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("NarakuTP.reload")) {
            if (args[0].equalsIgnoreCase("reload")) {
                Plugin plugin = Bukkit.getPluginManager().getPlugin("NarakuTP");
                try {
                    plugin.saveDefaultConfig();
                    plugin.reloadConfig();
                }catch (Exception e) {
                    sender.sendMessage(ChatColor.RED+"設定ファイルの再読み込み中にエラーが発生しました");
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN+"設定ファイルの再読み込みに成功しました");
                return true;
            }else {
                noPermission(sender);
                return true;
            }
        }
        return true;
    }
}
