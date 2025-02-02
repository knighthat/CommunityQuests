package me.wonka01.ServerQuests.commands;

import me.wonka01.ServerQuests.ServerQuests;
import me.wonka01.ServerQuests.configuration.messages.LanguageConfig;
import me.wonka01.ServerQuests.configuration.messages.Messages;
import me.wonka01.ServerQuests.enums.PermissionNode;
import me.wonka01.ServerQuests.gui.ViewGui;
import me.wonka01.ServerQuests.questcomponents.ActiveQuests;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.apache.commons.lang.NotImplementedException;

public class ViewQuestsCommand implements SubCommand {
    @Override
    public void onCommand(Player player, String[] args) {
        Messages messages = LanguageConfig.getConfig().getMessages();
        if (!player.hasPermission(PermissionNode.VIEW_QUEST)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getNoPermission()));
            return;
        }
        if (ActiveQuests.getActiveQuestsInstance().getActiveQuestsList().size() < 1) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getNoActiveQuests()));
            return;
        }
        ViewGui view = JavaPlugin.getPlugin(ServerQuests.class).getViewGui();
        view.initializeItems(player);
        view.openInventory(player);
    }

    public void onCommand(CommandSender sender, String[] args) {
        throw new NotImplementedException();
    }
}
