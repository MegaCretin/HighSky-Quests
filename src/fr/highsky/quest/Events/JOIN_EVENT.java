package fr.highsky.quest.Events;

import fr.highsky.quest.Utils.PLAYER_FILE;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;


public class JOIN_EVENT implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        PLAYER_FILE.addPlayerFile(p.getName());
    }
}
