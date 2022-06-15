package fr.highsky.quest.Inventory;

import fr.highsky.quest.Utils.INV_CREATOR;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.Arrays;

public class QUESTS_INVENTORY implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if(sender instanceof Player){
            Player p = (Player)sender;

            if (args.length == 0) {

                int NumberQuest = 0;
                int NumberQuestFinish = 0;

                File filePlayerQuestS = new File("plugins/HighSky-Quest/PlayerData/"+p.getDisplayName()+"/Quest-Secondaire.yml");
                FileConfiguration configPlayer = YamlConfiguration.loadConfiguration(filePlayerQuestS);

                File fileQO = new File("plugins/HighSky-Quest/Secondaire-Quest/NPC.yml");
                FileConfiguration configQO = YamlConfiguration.loadConfiguration(fileQO);

                for(String s : configQO.getStringList("NPC")){
                    File fileNPC = new File("plugins/HighSky-Quest/Secondaire-Quest/"+s+".yml");
                    FileConfiguration configNPC = YamlConfiguration.loadConfiguration(fileNPC);
                    NumberQuest += configNPC.getInt("Number");
                    NumberQuestFinish += configPlayer.getInt("Quest-S."+s+".Last");

                }

                int moyenne = NumberQuestFinish * 100 / NumberQuest;

                Inventory inv = INV_CREATOR.newInv( 54, "§6Quêtes §8» §7[§8I§7]");

                INV_CREATOR.newItem(inv,0, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,1, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,2, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta headx = (SkullMeta) head.getItemMeta();
                headx.setOwningPlayer(p);
                headx.setDisplayName("§8§l» §eStats du joueur §8§l«");

                if(moyenne>0 && moyenne<=20){
                    headx.setLore(Arrays.asList("","§4"+moyenne+"% §7("+NumberQuestFinish+"/"+NumberQuest+")"));
                }
                else if(moyenne>20 && moyenne<=40){
                    headx.setLore(Arrays.asList("","§c"+moyenne+"% §7("+NumberQuestFinish+"/"+NumberQuest+")"));
                }
                else if(moyenne>40 && moyenne<=60){
                    headx.setLore(Arrays.asList("","§6"+moyenne+"% §7("+NumberQuestFinish+"/"+NumberQuest+")"));
                }
                else if(moyenne>60 && moyenne<=80){
                    headx.setLore(Arrays.asList("","§e"+moyenne+"% §7("+NumberQuestFinish+"/"+NumberQuest+")"));
                }
                else if(moyenne>80 && moyenne<=99){
                    headx.setLore(Arrays.asList("","§a"+moyenne+"% §7("+NumberQuestFinish+"/"+NumberQuest+")"));
                }
                else{
                    headx.setLore(Arrays.asList("","§2"+moyenne+"% §7("+NumberQuestFinish+"/"+NumberQuest+")"));
                }

                head.setItemMeta(headx);
                inv.setItem(3, head);

                INV_CREATOR.newItem(inv,4, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

                if (configPlayer.getBoolean("Quest.Current")){
                    ItemStack it = new ItemStack(Material.LIME_DYE);
                    ItemMeta itx = it.getItemMeta();
                    itx.setDisplayName("§aQuête en cours");
                    itx.setLore(Arrays.asList("", configPlayer.getString("Quest.Started-Message").replace("&", "§"), ""));
                    it.setItemMeta(itx);
                    inv.setItem(5, it);
                }
                else {
                    ItemStack it = new ItemStack(Material.RED_DYE);
                    ItemMeta itx = it.getItemMeta();
                    itx.setDisplayName("§cAucune quête en cours");
                    itx.setLore(Arrays.asList(""));
                    it.setItemMeta(itx);
                    inv.setItem(5, it);
                }

                INV_CREATOR.newItem(inv,6, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,7, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,8, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));


                INV_CREATOR.newItem(inv,9, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,10, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,11, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,12, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,13, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,14, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,15, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,16, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,17, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));


                INV_CREATOR.newItem(inv,18, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,19, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

                INV_CREATOR.newNpcQuest(inv, 20, "§6Fermier", p, "a19d0813ece175b9a9e29b0b44bce7e112b6506339df7328b122b7d6f7589b37");
                INV_CREATOR.newNpcQuest(inv, 21, "§6Mineur", p, "8112f87cee578894e2d07253abb1466247cee48f1727bb9d1eac53f8e0571012");
                INV_CREATOR.newNpcQuest(inv, 22, "§6Bucheron", p, "20cd13223a8d9c173cedce6c4bebeba06a24a1ba275dd3eb5c7933f9a74ba011");
                INV_CREATOR.newNpcQuest(inv, 23, "§6Pêcheur", p, "49d0e833d9bda32f2d736d8c3c3be8b9b964addd59357c12263ffccb8b8dae");
                INV_CREATOR.newNpcQuest(inv, 24, "§6Forgeron", p, "9fa6fd4034c7d690c187fc0660fce4ec90c0dba9a67a14e14c328d6f34f52fae");


                INV_CREATOR.newItem(inv,25, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,26, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));


                INV_CREATOR.newItem(inv,27, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,28, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

                INV_CREATOR.newNpcQuest(inv, 29, "§6Boulangère", p, "51997da64043b284822115643a654fdc4e8a7226664b48a4e1dbb557b5c0fe14");
                INV_CREATOR.newNpcQuest(inv, 30, "§6Boucher", p, "d3e20a26cbb5740a158da991ef94dcd32d447e9ac03aa0e8f829a49830619a10");
                INV_CREATOR.newNpcQuest(inv, 31, "§6Chasseur", p, "24a0347436434eb13d537b9eb6b45b6ef4c5a78f86e91863ef61d2b8a53b82");
                INV_CREATOR.newNpcQuest(inv, 32, "§6Batisseur", p, "49c1832e4ef5c4ad9c519d194b1985030d257914334aaf2745c9dfd611d6d61d");
                INV_CREATOR.newNpcQuest(inv, 33, "§6Aventurière", p, "6d821092ce5e7557451c723a0341e90b93e0564e2b01481de81eea271f04c5b6");


                INV_CREATOR.newItem(inv,34, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,35, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));


                INV_CREATOR.newItem(inv,36, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,37, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,38, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,39, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,40, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,41, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,42, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,43, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,44, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));


                INV_CREATOR.newItem(inv,45, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,46, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,47, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,48, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,49, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,50, Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,51, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,52, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));
                INV_CREATOR.newItem(inv,53, Material.ORANGE_STAINED_GLASS_PANE, 1, "§e", Arrays.asList("§e"));

                p.openInventory(inv);
            }
            else {
                p.sendMessage("§a§lQUEST §8§l» §3Utilisation: §e/quests§3.");
            }
        }

        return false;
    }
}
