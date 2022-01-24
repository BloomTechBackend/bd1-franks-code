package com.amazon.ata.inheritance.day2.garden;

import java.util.Objects;

/**
 * Class representing a a flower that have symbolic meaning.
 */
public class SymbolicFlower extends Flower {  // subclass of Flower

    private String significance;  // add this attribute to what we get from Flower

    /**
     * Constructor populating the necessary SymbolicFlower instance variables.
     *
     * @param kind         The kind of the flower, such as "tulip"
     * @param coloration   The coloration of the flower, such as "red with white striping"
     * @param significance The meanings of the flower
     */
    //      ctor receives:  the-kind,  the_coloration, significance   when it's called
    public SymbolicFlower(String kind, String coloration, String significance) {
        // since kind and coloration are attributes of the superclass, we pass those to the superclass ctor
        super(kind, coloration, Planting.CUT);  // must call the superclass ctor from sub class ctor
                                                // passing the kind, coloration and setting Planting to CUT
        this.significance = significance;
    }

    public String getSignificance() {
        return significance;
    }

    /**
     * Symbolic flowers are always Cut for arrangements.
     * Cut flowers for arrangements should be placed in water.
     */
    @Override   // Tell the compiler to be sure is a valid override of the method (same name, return type, params)
    public String getCareInstructions() {
        return "Fill with enough cold water to submerge the cut end, at least once per day.";
    }

    @Override
    public String toString() {
        return String.format("SymbolicFlower: {Kind: %s, Coloration: %s, Planting: %s Significance: %s}",
            getKind(), getColoration(), getPlanting(), significance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false;       // Use the super class equals to compare the data it knows about
        SymbolicFlower that = (SymbolicFlower) o;
        if(this.getKind()       != that.getKind()     // use the getters in the super class to get the values in its data
        || this.coloration      != that.coloration    // access is allowed to superclass if defined as protected
        || this.getPlanting()   != that.getPlanting()) {
            return false;}
        return getSignificance().equals(that.getSignificance()); // determine if the data added by subclass is equal
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSignificance());
    }
}
