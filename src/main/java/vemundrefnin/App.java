package vemundrefnin;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (command.getName().equals("sethome")) {
            if(sender instanceof Player){
                Player player = (Player) sender;
                setHome(player);
            }
            else {
                System.out.println("You need to be a player to run this command");
            }
        }
        if (command.getName().equals("home")) {
            if(sender instanceof Player){
                Player player = (Player) sender;
                Location home = Homes.getHomes(player).get(player.getUniqueId().toString() + "." + "home" + ".name");
                player.sendMessage("Teleporting home...");
                player.teleport(home);
            }
            else {
                System.out.println("You need to be a player to run this command");
            }
        }
        return false;
    }

    private void setHome(Player player) {
        Location home = player.getLocation();
        try {
            Homes.setHome(player, home);
            player.sendMessage("New home rigistered.");

        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
