package fr.highsky.quest.Utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import static org.bukkit.Material.PLAYER_HEAD;

public class HEAD_CUSTOM {

    public HEAD_CUSTOM(){
        super();
    }

    public static ItemStack CreationCustomP(String name, String url){

        ItemStack it = new ItemStack(PLAYER_HEAD, 1);
        SkullMeta itemMeta = (SkullMeta) it.getItemMeta();
        itemMeta.setDisplayName(name);
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/" +url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField2 = null;
        try
        {
            profileField2 = itemMeta.getClass().getDeclaredField("profile");
            profileField2.setAccessible(true);
            profileField2.set(itemMeta, profile);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException er)
        {
            er.printStackTrace();
        }
        it.setItemMeta(itemMeta);

        return it;
    }

    public static ItemStack CreationCustom(String name, String lore, String url){

        ItemStack it = new ItemStack(PLAYER_HEAD, 1);
        SkullMeta itemMeta = (SkullMeta) it.getItemMeta();
        itemMeta.setLore(Arrays.asList("",lore,""));
        itemMeta.setDisplayName(name);
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/" +url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField2 = null;
        try
        {
            profileField2 = itemMeta.getClass().getDeclaredField("profile");
            profileField2.setAccessible(true);
            profileField2.set(itemMeta, profile);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException er)
        {
            er.printStackTrace();
        }
        it.setItemMeta(itemMeta);

        return it;
    }

    public static ItemStack CreationHCustom(String name, String lore,String colorPourcentage, String url){

        ItemStack it = new ItemStack(PLAYER_HEAD, 1);
        SkullMeta itemMeta = (SkullMeta) it.getItemMeta();
        itemMeta.setLore(Arrays.asList("","§f§nQuêtes terminées:"," "+colorPourcentage+"§7"+lore,""));
        itemMeta.setDisplayName("§8§l» " + name + " §8§l«");
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", "http://textures.minecraft.net/texture/" +url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField2 = null;
        try
        {
            profileField2 = itemMeta.getClass().getDeclaredField("profile");
            profileField2.setAccessible(true);
            profileField2.set(itemMeta, profile);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException er)
        {
            er.printStackTrace();
        }
        it.setItemMeta(itemMeta);

        return it;
    }
}
