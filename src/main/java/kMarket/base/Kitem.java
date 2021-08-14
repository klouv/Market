package kMarket.base;

import io.github.portlek.smartinventory.event.abs.ClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class Kitem {

    private final float money;
    private ItemStack item;
    private final int slot;

    public Kitem(ItemStack itemStack, int slot, float money) {
        this.item = itemStack;
        this.slot = slot;
        this.money = money;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getSlot() {
        return slot;
    }

    public float getMoney() {
        return money;
    }

}
