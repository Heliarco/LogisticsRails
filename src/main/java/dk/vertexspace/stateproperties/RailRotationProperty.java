package dk.vertexspace.stateproperties;

import com.google.common.collect.Lists;
import net.minecraft.state.EnumProperty;
import java.util.Collection;

public class RailRotationProperty extends EnumProperty<RailRotation> {
    protected RailRotationProperty(String name, Collection<RailRotation> values) {
        super(name, RailRotation.class, values);
    }
    public static RailRotationProperty create(String name, RailRotation ... values) {
        return new RailRotationProperty(name, Lists.newArrayList(values));
    }
}