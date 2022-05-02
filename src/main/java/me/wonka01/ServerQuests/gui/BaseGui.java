package me.wonka01.ServerQuests.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public abstract class BaseGui {

    public abstract void initializeItems();

    protected ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        ArrayList<String> metaLore = new ArrayList<>();

        for (String loreComments : lore) {
            metaLore.add(ChatColor.translateAlternateColorCodes('&', loreComments));
        }

        meta.setLore(metaLore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    protected boolean clickEventCheck(InventoryClickEvent e, BaseGui holder) {
        if (e.getInventory().getHolder() != holder) {
            return false;
        }
        e.setCancelled(true);

        ItemStack clickedItem = e.getCurrentItem();

        return clickedItem != null && clickedItem.getType() != Material.AIR;
    }
}
