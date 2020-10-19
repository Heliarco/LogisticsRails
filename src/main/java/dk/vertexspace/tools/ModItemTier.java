package dk.vertexspace.tools;

import dk.vertexspace.util.RegistryHandler;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTier implements IItemTier {
    // CORRECTION: For repairMaterial, I used RegistryHandler.RUBY_SWORD.get() when I should have accessed RegistryHandler.RUBY.get(). This will allow us to use our ruby items to repair any of our items in an anvil.
    // mcreator.net/wiki/how-make-tool
    RUBY (
            3,  // Tool level
            800,    // Durability
            11.0f, // Efficiency
            3.0f, // Base damage.
            12, // Enchantability
            () ->
            {
                return Ingredient.fromItems(RegistryHandler.RUBY.get()); // Repair material
            }
    );

    // Adding more tiers. Just add more enum values


    // Attack damage of anything: 1 (all items) + baseDamage(from itemtier) + addedDamage(from item made of material)
    ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;

    }

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairMaterial;

    @Override
    public int getMaxUses() {
        return maxUses;
    }

    @Override
    public float getEfficiency() {
        return efficiency;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairMaterial.get();
    }
}
