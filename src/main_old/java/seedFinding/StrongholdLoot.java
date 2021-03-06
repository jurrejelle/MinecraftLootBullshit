package seedFinding;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.BuriedTreasure;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.mc.MCVersion;

import java.util.List;

public class StrongholdLoot {
    public static void main(String[] args) {
        long structureSeed = 2L;
        long worldSeed = 2L;
        ChunkRand rand = new ChunkRand();
        LootContext a1 = new LootContext(8078210594421746446L);
        List<ItemStack> ItemList = MCLootTables.STRONGHOLD_CORRIDOR_CHEST.generate(a1);
        for (ItemStack item2 : ItemList) {
            System.out.println(item2.getItem().getName() + " : " + item2.getCount());
            if (!(item2.getItem().getEnchantment() == null)) {
                System.out.println("Enchantment: " + item2.getItem().getEnchantment() + " : " + item2.getItem().getLevel());
            }
        }
    }

}
