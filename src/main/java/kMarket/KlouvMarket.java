package kMarket;
import com.cryptomorin.xseries.XItemStack;
import kMarket.base.Kitem;
import kMarket.command.MarketCommand;
import kMarket.file.ConfigFile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import java.util.HashMap;
import java.util.Map;

public class KlouvMarket extends JavaPlugin implements Listener {

    public static Map<String, Kitem> map = new HashMap<>();
    public String title;
    public int row;
    private Economy econ = null;

    @Override
    public void onEnable() {
        if ((!setupEconomy())) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        ConfigFile config = new ConfigFile(this,"config");

        title = config.getString("title");
        row = config.getInt("row");

        ConfigurationSection section = config.getConfigurationSection("market");
        for (String Key : section.getKeys(false)) {
            ConfigurationSection item = section.getConfigurationSection(Key);

            ItemStack Item = XItemStack.deserialize(item.getConfigurationSection("item"));
            float money = item.getInt("money");
            int slot = item.getInt("slot");
            map.put(Key, new Kitem(Item, slot, money));
        }
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("market").setExecutor(new MarketCommand(this));
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public void onDisable() {



    }
}
