package seedFinding;

import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.function.EnchantWithLevelsFunction;
import kaptainwutax.featureutils.loot.enchantment.Enchantments;
import kaptainwutax.featureutils.loot.item.Item;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.seedutils.mc.MCVersion;

public class VersionsTest {
    public static void main(String[] args) {
		// A test to show that with different versions, the same lootseed can have different outcomes
        LootContext context = new LootContext(8078210594421746446l);
        ItemStack testStack1 = new ItemStack(new Item("book"), 1);
        EnchantWithLevelsFunction ewf1 = new EnchantWithLevelsFunction(30, 50, true, true);
        ewf1.process(testStack1, context);
        for (int i = 0; i < testStack1.getItem().getEnchantment().size(); i++) {
            System.out.println(testStack1.getItem().getEnchantment().get(i)+" "+testStack1.getItem().getLevel().get(i));
        }
        LootContext context2 = new LootContext(8078210594421746446l);
        ItemStack testStack2 = new ItemStack(new Item("book"), 1);
		//Realistically, you'd call this before the MCLootTables .generate function 
        Enchantments.setVersion(MCVersion.v1_12_2);
        EnchantWithLevelsFunction ewf2 = new EnchantWithLevelsFunction(30, 50, true, true);
        ewf2.process(testStack2, context2);
        for (int i = 0; i < testStack2.getItem().getEnchantment().size(); i++) {
            System.out.println(testStack2.getItem().getEnchantment().get(i)+" "+testStack2.getItem().getLevel().get(i));
        }
    }
}
