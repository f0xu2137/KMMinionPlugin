package pl.kwadratowamasakra.minions.utils;

import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Color;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class DiscoArmorUtil {

    public static void sendArmorChange(final Player p, final int entityId, final int slot, final ItemStack item) {
        final PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutEntityEquipment(entityId, slot, CraftItemStack.asNMSCopy(item)));
    }

    public static ItemStack setArmorColor(final ItemStack itemStack, final Color color) {
        if (color != null) {
            final ItemMeta itemMeta = itemStack.getItemMeta();
            final LeatherArmorMeta meta = (LeatherArmorMeta) itemMeta;
            meta.setColor(color);
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }


}
