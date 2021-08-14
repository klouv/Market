package kMarket.command;

import io.github.portlek.smartinventory.InventoryProvider;
import io.github.portlek.smartinventory.Page;
import io.github.portlek.smartinventory.SmartInventory;
import io.github.portlek.smartinventory.manager.BasicSmartInventory;
import kMarket.KlouvMarket;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import provider.MarketProvider;

public class MarketCommand implements CommandExecutor {

    private final KlouvMarket plugin;

    public MarketCommand(KlouvMarket klouvMarket) {
        this.plugin = klouvMarket;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("you can't use the command");
            return true;
        }
        Player player = (Player) sender;
        SmartInventory sInv = new BasicSmartInventory(plugin);
        sInv.init();
        Page.build(sInv)
                .provider(new MarketProvider())
                .row(plugin.row)
                .title(plugin.title)
                .open(player);

        return true;
    }
}
