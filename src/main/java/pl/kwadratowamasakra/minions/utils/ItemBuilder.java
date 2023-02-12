package pl.kwadratowamasakra.minions.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private final short data;
    private final List<String> lore;
    private final int amount;
    private final Material mat;
    private String title;

    public ItemBuilder(final Material mat, final int amount) {
        this(mat, amount, 0);
    }

    public ItemBuilder(final Material mat, final int amount, final int data) {
        title = null;
        lore = new ArrayList<>();
        this.mat = mat;
        this.amount = amount;
        this.data = (short) data;
    }

    public final ItemBuilder addLore(final String lore) {
        this.lore.add(lore);
        return this;
    }

    public final ItemStack build() {
        final ItemStack item = new ItemStack(mat, amount, data);
        final ItemMeta itemMeta = item.getItemMeta();
        if (title != null) {
            itemMeta.setDisplayName(MainUtil.fixColor(title));
        }
        if (!lore.isEmpty()) {
            itemMeta.setLore(lore);
        }
        item.setItemMeta(itemMeta);
        return item;
    }

    public final ItemBuilder setTitle(final String title) {
        this.title = title;
        return this;
    }

}
