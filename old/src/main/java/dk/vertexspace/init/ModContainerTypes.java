package dk.vertexspace.init;



import dk.vertexspace.containers.ExampleChestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public class ModContainerTypes {
    private ModContainerTypes(){}

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);

    public static final RegistryObject<ContainerType<ExampleChestContainer>> EXAMPLE_CHEST = CONTAINER_TYPES
            .register("example_chest", () -> IForgeContainerType.create(ExampleChestContainer::new));

}
