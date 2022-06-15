package fr.highsky.quest.Events;

import fr.herllox.hmoney.API.MoneyAPI;
import fr.highsky.quest.Main;
import fr.highsky.quest.Utils.CHOIX_INVENTORY;
import fr.highsky.quest.Utils.PLAYER_FILE;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BOOK_QUEST implements Listener {

    @EventHandler
    public void OnCloseBook(PlayerMoveEvent e) throws IOException {

        Player p = e.getPlayer();
        File filePlayer = new File("plugins/HighSky-Quest/PlayerData/"+ p.getName() +"/Quest-Principal.yml");
        FileConfiguration configPlayer = YamlConfiguration.loadConfiguration(filePlayer);
        if(configPlayer.getBoolean("Quest-P.EnCours")){
            File fileQuest = new File("plugins/HighSky-Quest/Principal-Quest/"+ configPlayer.getString("Quest-P.Start.Intitule") +".yml");
            int etapeQuest = configPlayer.getInt("Quest-P.Start.Etape");
            FileConfiguration configQuest = YamlConfiguration.loadConfiguration(fileQuest);
            List<String> choixQuest = List.of(Objects.requireNonNull(configPlayer.getString("Quest-P.Start.Choix")).split("/"));
            StringBuilder path = new StringBuilder();
            NPC npc = CitizensAPI.getNPCRegistry().getById(configPlayer.getInt("Quest-P.NPC"));

            if(configPlayer.getBoolean("Quest-P.Start.Scene")){
                e.setCancelled(true);


                if(choixQuest.size() ==1 && choixQuest.get(0).equals("")){
                    path = new StringBuilder(etapeQuest + ".Dialogue");
                } else {
                    path = new StringBuilder(etapeQuest + ".Dialogue");
                    for(String numero : choixQuest){
                        path.append(".Choix.").append(numero);
                    }
                }

                if(configQuest.contains(path + ".Choix")){
                    List<String> reponses = new ArrayList<>();
                    reponses.add(configQuest.getString(path + ".Choix.1.Reponse"));
                    reponses.add(configQuest.getString(path + ".Choix.2.Reponse"));
                    p.openInventory(CHOIX_INVENTORY.createInventoryChoix(npc.getName() ,configQuest.getString(path + ".Message") , reponses));
                }


            }

            if(configPlayer.getBoolean("Quest-P.Fin")){
                File playerFileHistory = new File("plugins/HighSky-Quest/PlayerData/"+ p.getName() +"/Historique.yml");
                FileConfiguration configPlayerFileHistory = YamlConfiguration.loadConfiguration(playerFileHistory);
                (new BukkitRunnable() {
                    public void run() {

                        configPlayer.set("Quest-P.Fin", false);
                        configPlayer.set("Quest-P.EnCours", false);
                        if(configQuest.contains(String.valueOf(etapeQuest + 1))){
                            p.sendTitle("Nouvelle étapes !", configQuest.getString(etapeQuest + ".Quest.Intitule"), 1, 100, 1);
                            configPlayer.set("Quest-P.NPC", configQuest.getInt(etapeQuest + 1 + ".NPC.ID"));
                            configPlayer.set("Quest-P.Start.Choix", "");
                            configPlayer.set("Quest-P.Start.Etape", etapeQuest + 1);


                        }else {
                            p.sendTitle("Quête Terminer!", "", 1, 100, 1);
                            //TODO REWARD

                            if (configQuest.contains(etapeQuest + ".End.Reward.Items")){
                                List<String> itemsReward = configQuest.getStringList(etapeQuest + ".End.Reward.Items");

                                itemsReward.forEach(r -> {
                                    p.getInventory().addItem(new ItemStack(Material.valueOf((String) r)));
                                });
                            }

                            if (configQuest.contains(etapeQuest + ".End.Reward.Money")){
                                MoneyAPI.giveMoney(p, configQuest.getDouble(etapeQuest + ".End.Reward.Money"));
                            }
                            
                        }

                        try {
                            PLAYER_FILE.savePlayerFileQuestP(p.getName(), configPlayer);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    }
                }).runTaskAsynchronously(Main.getInstance());
            }
        }


    }
}
