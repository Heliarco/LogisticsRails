package dk.vertexspace.stateproperties;

import net.minecraft.util.IStringSerializable;

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
        switch(this){
            case ROT_0:
                return ROT_1;
            case ROT_1:
                return ROT_2;
            case ROT_2:
                return ROT_3;
            case ROT_3:
                return ROT_0;
        }
        return ROT_0;
    }
}