package pl.kwadratowamasakra.minions.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import pl.kwadratowamasakra.minions.methods.Minion;
import pl.kwadratowamasakra.minions.methods.ServerHelper;
import pl.kwadratowamasakra.minions.utils.ItemBuilder;
import pl.kwadratowamasakra.minions.utils.MainUtil;

public class MinionInventory {

    public static Inventory getMinionGui(final ServerHelper serverHelper, final Minion minion) {
        final Inventory inv = Bukkit.createInventory(null, 27, MainUtil.fixColor("&8>> &2&lMINION &8<<"));
        for (int i = 0; i < inv.getSize(); ++i) {
            inv.setItem(i, serverHelper.getDefaultGlass());
        }

        inv.setItem(13, minion.getItemStack());

        inv.setItem(26, new ItemBuilder(Material.MONSTER_EGG, 1, 58).setTitle(" &8>> &7USUN MINIONA").build());
        return inv;
    }

}
