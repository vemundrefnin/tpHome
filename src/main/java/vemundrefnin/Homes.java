// package io.github.at.config;
package vemundrefnin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Homes {

    public static File homesFile = new File("vemundrefnin/homes.yml");
    public static FileConfiguration homes = YamlConfiguration.loadConfiguration(homesFile);

    public static void save() throws IOException {
        homes.save(homesFile);
    }

    public static void setHome(Player player, Location location) throws IOException {
        String homename = "home";
        homes.set(player.getUniqueId().toString() + "." + homename + ".x", location.getX());
        homes.set(player.getUniqueId().toString() + "." + homename + ".y", location.getY());
        homes.set(player.getUniqueId().toString() + "." + homename + ".z", location.getZ());
        homes.set(player.getUniqueId().toString() + "." + homename + ".world", location.getWorld().getName());
        homes.set(player.getUniqueId().toString() + "." + homename + ".yaw", location.getYaw());
        homes.set(player.getUniqueId().toString() + "." + homename + ".pitch", location.getPitch());
        save();
    }

    public static HashMap<String,Location> getHomes(Player player){
        HashMap<String,Location> homes = new HashMap<>();
        try {
            for (String home: Homes.homes.getConfigurationSection(player.getUniqueId().toString()).getKeys(false)) {
                Location location = new Location(Bukkit.getWorld(Homes.homes.getString(player.getUniqueId().toString() + "." + home + ".world")), // Gets world from name
                        Homes.homes.getDouble(player.getUniqueId().toString() + "." + home + ".x"), // Gets X value
                        Homes.homes.getDouble(player.getUniqueId().toString() + "." + home + ".y"), // Gets Y value
                        Homes.homes.getDouble(player.getUniqueId().toString() + "." + home + ".z"), // Gets Z value
                        Float.valueOf(String.valueOf(Homes.homes.getDouble(player.getUniqueId().toString() + "." + home + ".yaw"))),
                        Float.valueOf(String.valueOf(Homes.homes.getDouble(player.getUniqueId().toString() + "." + home + ".pitch"))));
                home = Homes.homes.getString(player.getUniqueId().toString() + "." + home + ".name") != null ? Homes.homes.getString(player.getUniqueId().toString() + "." + home + ".name") : home;
                homes.put(home,location);
            }
        } catch (NullPointerException ex) {
            Homes.homes.createSection(player.getUniqueId().toString());
        }

        return homes;
    }

    // public static void delHome(Player player, String homename) throws IOException {
    //     homes.set(player.getUniqueId().toString()+"."+homename,null);
    //     save();
    // }

    // public static void reloadHomes() throws IOException {
    //     if (homesFile == null) {
    //         homesFile = new File(Main.getInstance().getDataFolder(), "homes.yml");
    //     }
    //     homes = YamlConfiguration.loadConfiguration(homesFile);
    //     save();
    // }
}
