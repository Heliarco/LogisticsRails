package dk.vertexspace.stateproperties;

import net.minecraft.util.IStringSerializable;
import org.apache.commons.lang3.ArrayUtils;

// We have a problem in that each facing of turn/t section rails has 4 valid rotations but we cannot label them by name since it changes for each facing :/ Oh well
public enum RailRotation implements IStringSerializable {
    ROT_0("0"),
    ROT_1("1"),
    ROT_2("2"),
    ROT_3("3");

    private final String name;
    RailRotation(String name){
        this.name = name;
    }

    @Override  // A WILD guess would be that this method should be "getName"
    public String func_176610_l() {
        return this.name;
    }
    public RailRotation next(){
        RailRotation[] values = RailRotation.values();
        int i = ArrayUtils.indexOf(values,this);
        i++;
        if(i >= values.length){
            i = 0;
        }
        return values[i];
    }
}