package dk.vertexspace.armor;

import dk.vertexspace.constants.RegistryHandler;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public enum ModArmorMaterial implements IArmorMaterial {



    // The name parameter has a specific meaning!
    // It defines the paths for the armor layers, so in this case: ruby_layer_1, ruby_layer_2
    RUBY(
            MOD_ID + ":ruby",
            25, // durability would be better name
             new int[] {2, 5, 6, 2}, // This is the armor values. 2 equals one "suit" of armor
            18,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, // Equipping sound
            0.0F, //by setting it to 0. toughness is disabled. Diamond has 2.0F,
            () -> { return Ingredient.fromItems(RegistryHandler.RUBY.get());} // Repair material. Lazy evaluation
    );



    private static final int[] MAX_DAMAGE_ARRAY = new int[] {11, 16, 15, 13}; // Boots leggings chest helmet
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final Supplier<Ingredient> repairMaterial;

    ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, Supplier<Ingredient> repairMaterial){
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.repairMaterial = repairMaterial;

    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {


        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float func_230304_f_() { // Knockback resistance
        return 10; // LOL
    }
}
