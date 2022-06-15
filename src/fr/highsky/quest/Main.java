package fr.highsky.quest;


import fr.highsky.quest.Events.CLICK_INVENTORY;
import fr.highsky.quest.Events.CLICK_NPC;
import fr.highsky.quest.Events.BOOK_QUEST;
import fr.highsky.quest.Events.JOIN_EVENT;
import fr.highsky.quest.Inventory.QUESTS_CLICK_INVENTORY;
import fr.highsky.quest.Inventory.QUESTS_INVENTORY;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {

        instance = this;

        Bukkit.getPluginManager().registerEvents(new JOIN_EVENT(), this);
        Bukkit.getPluginManager().registerEvents(new CLICK_NPC(), this);
        Bukkit.getPluginManager().registerEvents(new BOOK_QUEST(), this);
        Bukkit.getPluginManager().registerEvents(new CLICK_INVENTORY(), this);

        getCommand("questsTest").setExecutor(new QUESTS_INVENTORY());
        Bukkit.getPluginManager().registerEvents(new QUESTS_CLICK_INVENTORY(), this);

    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return instance;
    }
}
