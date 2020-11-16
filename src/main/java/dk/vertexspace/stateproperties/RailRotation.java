package dk.vertexspace.stateproperties;

import com.google.common.collect.Lists;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.Direction;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// We have a problem in that each facing of turn/tsection rails has 4 valid rotations but we cannot label them by name since it changes for each facing :/ Oh well
public enum RailRotation exte {
    ROT_0,
    ROT_1,
    ROT_2,
    ROT_3;


    public static final EnumProperty<RailRotation> ROTATION_PROPERTY = EnumProperty.create("rotation", RailRotation.class);

    @Override
    public String func_176610_l() {
        return "rail_rotation";
    }
};




public class DirectionProperty extends EnumProperty<Direction> {
    protected DirectionProperty(String name, Collection<Direction> values) {
        super(name, Direction.class, values);
    }

    /**
     * Create a new PropertyDirection with all directions that match the given Predicate
     */
    public static DirectionProperty create(String name, Predicate<Direction> filter) {
        return create(name, Arrays.stream(Direction.values()).filter(filter).collect(Collectors.toList()));
    }

    public static net.minecraft.state.DirectionProperty create(String p_196962_0_, Direction... p_196962_1_) {
        return create(p_196962_0_, Lists.newArrayList(p_196962_1_));
    }

    /**
     * Create a new PropertyDirection for the given direction values
     */
    public static net.minecraft.state.DirectionProperty create(String name, Collection<Direction> values) {
        return new net.minecraft.state.DirectionProperty(name, values);
    }
}
