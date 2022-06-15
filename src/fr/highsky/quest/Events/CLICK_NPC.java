package fr.highsky.quest.Events;

import fr.herllox.hmoney.API.MoneyAPI;
import fr.highsky.quest.Main;
import fr.highsky.quest.Utils.PLAYER_FILE;
import fr.highsky.quest.Utils.QUEST;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CLICK_NPC implements Listener {

    @EventHandler
    public void onClickNPC(NPCRightClickEvent e) throws IOException {
        Player p = e.getClicker();
        NPC npc = e.getNPC();
        File filePlayerQuestP = new File("plugins/HighSky-Quest/PlayerData/"+ p.getName() +"/Quest-Principal.yml");
        FileConfiguration configPlayerQuestP = YamlConfiguration.loadConfiguration(filePlayerQuestP);
        File filePlayerQuestS = new File("plugins/HighSky-Quest/PlayerData/"+ p.getName() +"/Quest-Secondaire.yml");
        FileConfiguration configPlayerQuestS = YamlConfiguration.loadConfiguration(filePlayerQuestS);

        if(configPlayerQuestP.getInt("Quest-P.NPC") == npc.getId()){
            configPlayerQuestP.set("Quest-P.EnCours", true);
            PLAYER_FILE.savePlayerFileQuestP(p.getName(), configPlayerQuestP);
            File fileQuest = new File("plugins/HighSky-Quest/Principal-Quest/"+ configPlayerQuestP.getString("Quest-P.Start.Intitule") +".yml");
            FileConfiguration configQuest = YamlConfiguration.loadConfiguration(fileQuest);
            int etapeQuest = configPlayerQuestP.getInt("Quest-P.Start.Etape");
            List<String> choixQuest = List.of(configPlayerQuestP.getString("Quest-P.Start.Choix").split("/"));
            StringBuilder path = new StringBuilder();
            List<String> pages = new ArrayList<>();

            if (configQuest.contains(etapeQuest + ".Need")) {
                List<String> Require_Items = null;
                int req = 0;
                boolean hasMoney = false;
                StringBuilder msg = new StringBuilder();
                msg.append("Il vous manque ");
                if (configQuest.contains(etapeQuest + ".Need.Items")){
                    Require_Items = configQuest.getStringList(etapeQuest + ".Need.Items");
                    for (int i = 0; i < Require_Items.size(); i++) {
                        String[] req_split = Require_Items.get(i).split(":");
                        ItemStack it = new ItemStack(Material.valueOf(req_split[0]), 1);
                        if (p.getInventory().containsAtLeast(it, Integer.parseInt(req_split[1]))) {
                            req++;
                        }
                    }

                    if (req != Require_Items.size()){
                       msg.append("des objets");
                    }

                }

                if (configQuest.contains(etapeQuest + ".Need.Money")) {
                    if(MoneyAPI.getMoney(p) > configQuest.getDouble(etapeQuest + ".Need.Money")) {
                        hasMoney = true;
                        msg.append(".");
                    } else {
                        if (req != Require_Items.size()){
                            msg.append(" et de l'argent.");
                        } else {
                            msg.append("de l'argent.");
                        }
                    }
                } else {
                    hasMoney = true;
                }

                if ((req == Require_Items.size() || Require_Items == null) && hasMoney){
                    for (int i = 0; i < Require_Items.size(); i++) {
                        String[] req_split = Require_Items.get(i).split(":");
                        ItemStack it = new ItemStack(Material.valueOf(req_split[0]), Integer.parseInt(req_split[1]));
                        p.getInventory().removeItem(it);
                    }
                    if (configQuest.contains(etapeQuest + ".Need.Money")) {
                        MoneyAPI.removeMoney(p, configQuest.getDouble(etapeQuest + ".Need.Money"));
                    }
                } else {
                    p.sendMessage("§c" +configQuest.getString(etapeQuest + ".NPC.Name") + ": " + msg);
                    return;
                }

            }

            if(choixQuest.size() ==1 && choixQuest.get(0).equals("")){
                path = new StringBuilder(etapeQuest + ".Dialogue");
                pages.add("§6§n§l" + npc.getName() + ":\n§r " + configQuest.getString(path + ".Message"));
            } else {
                path = new StringBuilder(etapeQuest + ".Dialogue");
                for(String numero : choixQuest){
                    pages.add("§6§n§l" + npc.getName() + ":\n§r " + configQuest.getString(path + ".Message") + "\n\n§6§n§lVous:§r\n " + configQuest.getString(path + ".Choix." + numero + ".Reponse"));

                    if (!configQuest.contains(path + ".Choix." + numero + ".Fin")){
                        pages.add("§6§n§l" + npc.getName() + ":\n§r " + configQuest.getString(path + ".Choix." + numero + ".Message"));
                    }else {
                        if(pages.size() >= 2){
                            pages.remove(pages.size() - 2);
                        }
                    }
                    path.append(".Choix.").append(numero);


                }
            }

            QUEST.openBookQuest(p, pages, npc);



            if(configQuest.contains(path + ".Choix")){
                (new BukkitRunnable() {
                    public void run() {
                        configPlayerQuestP.set("Quest-P.Start.Scene", true);
                        try {
                            PLAYER_FILE.savePlayerFileQuestP(p.getName(), configPlayerQuestP);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).runTaskLaterAsynchronously(Main.getInstance(), 20L);
            }
        }
    }
}
