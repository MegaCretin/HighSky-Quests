package fr.highsky.quest.Events;

import fr.highsky.quest.Main;
import fr.highsky.quest.Utils.PLAYER_FILE;
import fr.highsky.quest.Utils.QUEST;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CLICK_INVENTORY implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) throws IOException {

        Player p = (Player) e.getWhoClicked();

        if (e.getClickedInventory() == null){
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equalsIgnoreCase("§6Réponse §8» ")) {
            e.setCancelled(true);

            if (e.getInventory() == null || e.getCurrentItem() == null || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta()) {
                return;
            }

            switch(e.getCurrentItem().getType()) {
                case BOOK:
                    p.closeInventory();
                    File filePlayer = new File("plugins/HighSky-Quest/PlayerData/"+ p.getName() +"/Quest-Principal.yml");
                    FileConfiguration configPlayer = YamlConfiguration.loadConfiguration(filePlayer);
                    StringBuilder choixBuilder = new StringBuilder();
                    if (Objects.equals(configPlayer.getString("Quest-P.Start.Choix"), "")){
                        choixBuilder.append(e.getCurrentItem().getItemMeta().getDisplayName().substring(e.getCurrentItem().getItemMeta().getDisplayName().length() - 1));
                    } else {
                        choixBuilder.append(configPlayer.getString("Quest-P.Start.Choix")).append("/").append(e.getCurrentItem().getItemMeta().getDisplayName().substring(e.getCurrentItem().getItemMeta().getDisplayName().length() - 1));
                    }
                    configPlayer.set("Quest-P.Start.Choix", choixBuilder.toString());
                    configPlayer.set("Quest-P.Start.Scene", false);
                    PLAYER_FILE.savePlayerFileQuestP(p.getName(), configPlayer);

                    NPC npc = CitizensAPI.getNPCRegistry().getById(configPlayer.getInt("Quest-P.NPC"));

                    File fileQuest = new File("plugins/HighSky-Quest/Principal-Quest/"+ configPlayer.getString("Quest-P.Start.Intitule") +".yml");
                    FileConfiguration configQuest = YamlConfiguration.loadConfiguration(fileQuest);
                    int etapeQuest = configPlayer.getInt("Quest-P.Start.Etape");
                    List<String> choixQuest = List.of(configPlayer.getString("Quest-P.Start.Choix").split("/"));
                    StringBuilder path;
                    List<String> pages = new ArrayList<>();

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
                                if(pages.size() >= 3){
                                    pages.remove(pages.size() - 2);
                                }
                            }
                            path.append(".Choix.").append(numero);

                        }
                    }

                    if(configQuest.contains(path + ".Fin")){
                        if(configQuest.getBoolean(path + ".Fin")){
                            pages.add("§3§n§lQuest:\n§r§0 " + configQuest.getString( etapeQuest + ".Quest.Message"));
                            configPlayer.set("Quest-P.Fin", true);
                            PLAYER_FILE.savePlayerFileQuestP(p.getName(), configPlayer);
                        }
                    }

                    QUEST.openBookQuest(p, pages, npc);

                    if(configQuest.contains(path + ".Choix")){
                        (new BukkitRunnable() {
                            public void run() {
                                configPlayer.set("Quest-P.Start.Scene", true);
                                try {
                                    PLAYER_FILE.savePlayerFileQuestP(p.getName(), configPlayer);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }).runTaskLaterAsynchronously(Main.getInstance(), 20L);
                    }
            }
        }
    }
}
