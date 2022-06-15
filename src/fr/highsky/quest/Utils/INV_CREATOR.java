package fr.highsky.quest.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.List;

public class INV_CREATOR {

    public static Inventory newInv(Integer Size, String Name){

        Inventory inv = Bukkit.createInventory(null, Size, Name);
        return inv;

    }

    public static void newItem(Inventory inv, int Slot, Material material, Integer Amount, String Name, List<String> lore){

        ItemStack it = new ItemStack(material, Amount);
        ItemMeta itx = it.getItemMeta();
        itx.setDisplayName(Name);
        itx.setLore(lore);
        itx.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itx.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        it.setItemMeta(itx);
        inv.setItem(Slot, it);

    }

    public static void newNpcQuest(Inventory inv, int Slot, String name, Player p, String urlHead){

        File filePlayer = new File("plugins/HighSky-Quest/PlayerData/"+p.getDisplayName()+"/Quest-Secondaire.yml");
        FileConfiguration configPlayer = YamlConfiguration.loadConfiguration(filePlayer);

        File fileNpc = new File("plugins/HighSky-Quest/Secondaire-Quest/"+name.replace("§6", "")+".yml");
        FileConfiguration configNpc = YamlConfiguration.loadConfiguration(fileNpc);

        if(configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") == 0){
            inv.setItem( Slot, HEAD_CUSTOM.CreationHCustom(name, "("+configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") + "/"+ configNpc.getInt("Number")+")","§40% ", urlHead));
            return;
        }

        int moyenne =  100 * configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") / configNpc.getInt("Number");

        if(moyenne>0 && moyenne<=20){
            inv.setItem( Slot, HEAD_CUSTOM.CreationHCustom(name,"("+configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") + "/"+ configNpc.getInt("Number")+")","§4"+moyenne+"% ", urlHead));
        }
        else if(moyenne>20 && moyenne<=40){
            inv.setItem( Slot, HEAD_CUSTOM.CreationHCustom(name,"("+configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") + "/"+ configNpc.getInt("Number")+")","§c"+moyenne+"% ", urlHead));
        }
        else if(moyenne>40 && moyenne<=60){
            inv.setItem( Slot, HEAD_CUSTOM.CreationHCustom(name,"("+configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") + "/"+ configNpc.getInt("Number")+")","§6"+moyenne+"% ", urlHead));
        }
        else if(moyenne>60 && moyenne<=80){
            inv.setItem( Slot, HEAD_CUSTOM.CreationHCustom(name,"("+configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") + "/"+ configNpc.getInt("Number")+")","§e"+moyenne+"% ", urlHead));
        }
        else if(moyenne>80 && moyenne<=99){
            inv.setItem( Slot, HEAD_CUSTOM.CreationHCustom(name,"("+configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") + "/"+ configNpc.getInt("Number")+")","§a"+moyenne+"% ", urlHead));
        }
        else{
            inv.setItem( Slot, HEAD_CUSTOM.CreationHCustom(name,"("+configPlayer.getInt("Quest-S." + name.replace("§6", "") +".Last") + "/"+ configNpc.getInt("Number")+")","§2"+moyenne+"% ", urlHead));
        }

    }

}
