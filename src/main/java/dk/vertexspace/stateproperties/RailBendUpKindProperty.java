package dk.vertexspace.stateproperties;

import net.minecraft.state.EnumProperty;

import java.util.Arrays;
import java.util.Collection;

public class RailBendUpKindProperty extends EnumProperty<RailBendUpKind> {
    protected RailBendUpKindProperty(String name, Collection<RailBendUpKind> values) {
        super(name, RailBendUpKind.class, values);
    }
    public static RailBendUpKindProperty create(String name){
        return new RailBendUpKindProperty(name, Arrays.asList(RailBendUpKind.values()));
    }

}
