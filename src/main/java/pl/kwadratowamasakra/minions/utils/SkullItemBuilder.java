package pl.kwadratowamasakra.minions.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SkullItemBuilder {

    private final short data;
    private final List<String> lore;
    private final int amount;
    private final Material mat;
    private final String owner;
    private final String title;

    public SkullItemBuilder(final Material mat, final int amount, final int data, final String owner) {
        title = null;
        lore = new ArrayList<>();
        this.mat = mat;
        this.amount = amount;
        this.data = (short) data;
        this.owner = owner;
    }

    public final ItemStack build() {
        final ItemStack item = new ItemStack(mat, amount, data);
        final SkullMeta meta = (SkullMeta) item.getItemMeta();
        if (title != null) {
            meta.setDisplayName(MainUtil.fixColor(title));
        }
        if (owner != null) {
            meta.setOwner(owner);
        }
        if (!lore.isEmpty()) {
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

}
