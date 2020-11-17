package dk.vertexspace.stateproperties;

import com.google.common.collect.Lists;
import net.minecraft.state.EnumProperty;
import java.util.Collection;

public class RailRotationProperty extends EnumProperty<RailRotation> {
    protected RailRotationProperty(String name, Collection<RailRotation> values) {
        super(name, RailRotation.class, values);
    }
    public static RailRotationProperty create(String name) {
        return create(name, RailRotation.ROT_0, RailRotation.ROT_1, RailRotation.ROT_2, RailRotation.ROT_3);
    }

    private static RailRotationProperty create(String name, RailRotation ... values) {
        return new RailRotationProperty(name, Lists.newArrayList(values));
    }
}