package dk.vertexspace.stateproperties;

import net.minecraft.state.EnumProperty;

import java.util.Arrays;
import java.util.Collection;

public class RailBendKindProperty extends EnumProperty<RailBendKind> {
    protected RailBendKindProperty(String name, Collection<RailBendKind> values) {
        super(name, RailBendKind.class, values);
    }
    public static RailBendKindProperty create(String name){
        return new RailBendKindProperty(name, Arrays.asList(RailBendKind.values()));
    }

}
