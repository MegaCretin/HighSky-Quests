package fr.highsky.quest.Utils;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class QUEST {

    public static void openBookQuest(Player p, List<String> pages, NPC npc){
        ItemStack itBook1 = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta itxBook1 = (BookMeta) itBook1.getItemMeta();
        itxBook1.setPages(pages);
        itxBook1.setAuthor(npc.getName());
        itxBook1.setTitle("Quête");
        itBook1.setItemMeta(itxBook1);
        p.openBook(itBook1);
    }
}
