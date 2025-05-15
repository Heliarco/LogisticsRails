package dk.vertexspace.stateproperties;

import net.minecraft.state.EnumProperty;

import java.util.Arrays;
import java.util.Collection;

public class RailRotationProperty extends EnumProperty<RailRotation> {
    protected RailRotationProperty(String name, Collection<RailRotation> values) {
        super(name, RailRotation.class, values);
    }
    public static RailRotationProperty create(String name) {
        return new RailRotationProperty(name, Arrays.asList(RailRotation.values()));
    }
}