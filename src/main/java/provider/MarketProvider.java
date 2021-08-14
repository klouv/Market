package provider;

import io.github.portlek.smartinventory.Icon;
import io.github.portlek.smartinventory.InventoryContents;
import io.github.portlek.smartinventory.InventoryProvider;
import kMarket.KlouvMarket;

public class MarketProvider implements InventoryProvider {


    @Override
    public void init(InventoryContents contents) {

        KlouvMarket.map.values().forEach((value) -> {
            contents.set(value.getSlot(), Icon.click(value.getItem(),clickEvent -> {

                contents.player().getInventory().addItem(value.getItem());


            }));
        });


    }
}
