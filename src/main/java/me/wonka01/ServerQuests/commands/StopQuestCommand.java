package me.wonka01.ServerQuests.commands;

import me.wonka01.ServerQuests.ServerQuests;
import me.wonka01.ServerQuests.enums.PermissionConstants;
import me.wonka01.ServerQuests.gui.StopGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class StopQuestCommand extends SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {

        if (!player.hasPermission(PermissionConstants.STOP_QUEST)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission to perform this action"));
            return;
        }
        StopGui stopGui = JavaPlugin.getPlugin(ServerQuests.class).getStopGui();
        stopGui.initializeItems();
        stopGui.openInventory(player);
    }
}
