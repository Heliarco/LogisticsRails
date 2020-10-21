package dk.vertexspace.items;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import static dk.vertexspace.constants.TabGroups.TAB;

public class PoisonApple extends Item {
    public PoisonApple() {
        super(
                new Item.Properties()
                    .group(TAB)
                    .food(
                            new Food.Builder() // THIS HAS MANY OTHER PROPERTIES!
                                .hunger(4) // 2 hunger bars
                                .saturation(1.3f) // 2 sat bars
                                .effect(() ->new EffectInstance(Effects.NAUSEA, 300, 1), 0.5f) // Duration is in TICKS (TPS is always 20
                                .effect(() ->new EffectInstance(Effects.POISON, 300, 5), 0.8f) // Duration is in TICKS (TPS is always 20
                                .effect(() ->new EffectInstance(Effects.HUNGER, 300, 5), 1f) // Duration is in TICKS (TPS is always 20
                                .setAlwaysEdible()
                                .build()
                    ));
    } // Maybe itembase
}
