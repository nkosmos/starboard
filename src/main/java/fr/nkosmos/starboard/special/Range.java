package fr.nkosmos.starboard.special;

import lombok.Getter;

@Getter
public class Range<T extends Comparable<T>> {
    private final T lowerBound;
    private final T higherBound;

    private final boolean strictEqual;
    private final boolean equalMode;

    public Range(T first, T second, boolean strictEqual) {
        this.strictEqual = strictEqual;
        this.equalMode = first.compareTo(second) == 0;

        if (first.compareTo(second) > 0) {
            this.higherBound = first;
            this.lowerBound = second;
        } else {
            this.lowerBound = first;
            this.higherBound = second;
        }
    }

    public boolean isInRange(T that) {
        if (equalMode) { // special case
            return that.compareTo(higherBound) == 0;
        }

        if(strictEqual){
            return this.lowerBound.compareTo(that) < 0
                    && this.higherBound.compareTo(that) > 0;
        }

        return this.lowerBound.compareTo(that) <= 0
                && this.higherBound.compareTo(that) >= 0;
    }
}
