package dk.vertexspace.constants;

import dk.vertexspace.armor.ModArmorMaterial;
import dk.vertexspace.blocks.BlockItemBase;
import dk.vertexspace.blocks.RubyBlock;
import dk.vertexspace.items.ItemBase;
import dk.vertexspace.tools.ModItemTier;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;
import static dk.vertexspace.constants.TabGroups.TAB;

public class RegistryHandler {

    // Registered names here are ALWAYS lowercase with underscore separators.

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Registers
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);


    // Items
    public static final RegistryObject<Item> CLAYCRETE = ITEMS.register("claycrete", ItemBase::new);


    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);



    // Tools
    public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () ->
            new SwordItem(
                    ModItemTier.RUBY,
                    20,
                    -2.4F, // Cooldown. Base value is 4 ... whatever
                    new Item.Properties().group(TAB) // Checkout what other properties are on item properties here!
            ));


    // Other built in tools:
    // PickaxeItem, shovelitem, axeitem, same arguments
    // Hoeitem has different constructor. No damage value

    // Armor
    public static final RegistryObject<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD, new Item.Properties().group(TAB)));

    public static final RegistryObject<ArmorItem> RUBY_CHEST = ITEMS.register("ruby_chestplate", () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST, new Item.Properties().group(TAB)));

    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS, new Item.Properties().group(TAB)));

    public static final RegistryObject<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET, new Item.Properties().group(TAB)));




    // Blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);

    // Block Items
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () -> new BlockItemBase(RUBY_BLOCK.get()));



}
