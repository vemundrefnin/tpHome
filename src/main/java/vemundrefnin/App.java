package vemundrefnin;
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
        if (command.getName().equals("die")) {
            if(sender instanceof Player){
                Player player = (Player) sender;
                player.setHealth(0);
                player.sendMessage("die");
            }
            else {
                System.out.println("You need to be a player to run this command");
            }
        }
        return false;
    }
}
