package fr.highsky.quest.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PLAYER_FILE {

    public static void addPlayerFile(String name) throws IOException {

        File fileQuestP = new File("plugins/HighSky-Quest/PlayerData/"+name+"/Quest-Principal.yml");
        FileConfiguration configQuestP = YamlConfiguration.loadConfiguration(fileQuestP);
        File fileQuestS = new File("plugins/HighSky-Quest/PlayerData/"+name+"/Quest-Secondaire.yml");
        FileConfiguration configQuestS = YamlConfiguration.loadConfiguration(fileQuestS);
        if(!fileQuestP.exists()){
            Files.createDirectory(Path.of("plugins/HighSky-Quest/PlayerData/" + name + "/"));
            fileQuestP.createNewFile();

            configQuestP.set("Quest-P.QuestNum", 1);
            configQuestP.set("Quest-P.EnCours", false);
            configQuestP.set("Quest-P.NPC", 58);
            configQuestP.set("Quest-P.Start.Intitule", 1);
            configQuestP.set("Quest-P.Start.Scene", false);
            configQuestP.set("Quest-P.Start.Etape", 1);
            configQuestP.set("Quest-P.Start.Choix", "");
            configQuestP.set("Quest-P.Fin", false);

            savePlayerFileQuestP(name, configQuestP);
        }

        if(!fileQuestS.exists()){
            fileQuestS.createNewFile();

            File fileQO = new File("plugins/HighSky-Quest/Secondaire-Quest/NPC.yml");
            FileConfiguration configQO = YamlConfiguration.loadConfiguration(fileQO);

            for(String s : configQO.getStringList("NPC")){
                int i = 0;
                File fileQONPC = new File("plugins/HighSky-Quest/Secondaire-Quest/"+s+".yml");
                FileConfiguration configQONPC = YamlConfiguration.loadConfiguration(fileQONPC);

                configQuestS.set("Quest-S."+s+".Last", 0);
                configQuestS.set("Quest-S."+s+".EnCours", 0);

                for(String ss : configQONPC.getConfigurationSection("Quest").getKeys(false)){
                    i++;
                    configQuestS.set("Quest-S."+s+".Liste."+i+"Q", false);
                }
            }

            savePlayerFileQuestS(name, configQuestS);
        } else {
            File fileQO = new File("plugins/HighSky-Quest/Secondaire-Quest/NPC.yml");
            FileConfiguration configQO = YamlConfiguration.loadConfiguration(fileQO);

            for(String s : configQO.getStringList("NPC")){
                int i = 0;
                File fileQONPC = new File("plugins/HighSky-Quest/Secondaire-Quest/"+s+".yml");
                FileConfiguration configQONPC = YamlConfiguration.loadConfiguration(fileQONPC);

                if(!configQuestS.contains("Quest-S."+s+".Last")){
                    configQuestS.set("Quest-S."+s+".Last", 0);
                }

                if(!configQuestS.contains("Quest-S."+s+".EnCours")){
                    configQuestS.set("Quest-S."+s+".EnCours", 0);
                }


                for(String ss : configQONPC.getConfigurationSection("Quest").getKeys(false)){
                    i++;
                    if (!configQuestS.contains("Quest-S."+s+".Liste."+i+"Q")){
                        configQuestS.set("Quest-S."+s+".Liste."+i+"Q", false);
                    }
                }
            }
            savePlayerFileQuestS(name, configQuestS);
        }
    }


    public static void savePlayerFileQuestP(String name, FileConfiguration configuration) throws IOException {
        File fileQuestP = new File("plugins/HighSky-Quest/PlayerData/"+name+"/Quest-Principal.yml");
        configuration.save(fileQuestP);
    }
    public static void savePlayerFileQuestS(String name, FileConfiguration configuration) throws IOException {
        File fileQuestS = new File("plugins/HighSky-Quest/PlayerData/"+name+"/Quest-Secondaire.yml");
        configuration.save(fileQuestS);
    }
}
