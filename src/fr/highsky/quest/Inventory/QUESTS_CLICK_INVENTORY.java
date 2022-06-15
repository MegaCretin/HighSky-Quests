package fr.highsky.quest.Inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class QUESTS_CLICK_INVENTORY implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null){
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equalsIgnoreCase("§6Quêtes §8» §7[§8I§7]")) {
            e.setCancelled(true);
        }
    }
}
