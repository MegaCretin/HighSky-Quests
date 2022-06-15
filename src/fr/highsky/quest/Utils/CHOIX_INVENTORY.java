package fr.highsky.quest.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CHOIX_INVENTORY {



    public static Inventory createInventoryChoix(String name, String question ,List<String> reponses){
        Inventory inv = Bukkit.createInventory(null, 36, "§6Réponse §8» ");

        ItemStack itBook = new ItemStack(Material.PAPER, 1);
        ItemMeta itxBook = itBook.getItemMeta();
        itxBook.setDisplayName("§6§l" + name + ": ");
        itxBook.setLore(cutString(question));
        itxBook.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        itxBook.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itxBook.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itBook.setItemMeta(itxBook);

        ItemStack itBook1 = new ItemStack(Material.BOOK, 1);
        ItemMeta itxBook1 = itBook1.getItemMeta();
        itxBook1.setDisplayName("§r§2Choix: 1");
        itxBook1.setLore(cutString(reponses.get(0)));
        itxBook1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itxBook1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itBook1.setItemMeta(itxBook1);

        ItemStack itBook2 = new ItemStack(Material.BOOK, 1);
        ItemMeta itxBook2 = itBook2.getItemMeta();
        itxBook2.setDisplayName("§r§cChoix: 2");
        itxBook2.setLore(cutString(reponses.get(1)));
        itxBook2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itxBook2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itBook2.setItemMeta(itxBook2);


        //Line 1
        INV_CREATOR.newItem(inv,0, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,1, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,2, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,3, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,4, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,5, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,6, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,7, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,8, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

        //Line 2
        INV_CREATOR.newItem(inv,9, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,10, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,11, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,12, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        inv.setItem(13, itBook);
        INV_CREATOR.newItem(inv,14, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,15, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,16, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,17, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

        //Line 3
        INV_CREATOR.newItem(inv,18, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,19, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        inv.setItem(20, itBook1);
        INV_CREATOR.newItem(inv,21, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,22, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,23, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        inv.setItem(24, itBook2);
        INV_CREATOR.newItem(inv,25, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,26, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

        //Line 4
        INV_CREATOR.newItem(inv,27, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,28, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,29, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,30, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,31, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,32, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,33, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,34, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
        INV_CREATOR.newItem(inv,35, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

        return inv;
    }

    private static List<String> cutString(String txt){
        List<String> result = new ArrayList<>();
        StringBuilder mots = new StringBuilder(" ");
        List<String> phrases = List.of(txt.split(" "));
        result.add(" ");
        phrases.forEach(mot -> {
            mots.append(mot + " ");
            if(mots.length() >= 30 || mot.equals(phrases.get(phrases.size() - 1))) {
                result.add("§r§l§f" + mots);
                mots.setLength(0);
            }
        });
        result.add(" ");
        return result;
    }
}
