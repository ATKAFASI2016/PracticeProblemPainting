package atkafasi.model.instructions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Point extends Square {

    public Point(int row, int column) {
        super(row, column, 0);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(getRow()).
                append(getColumn()).
                toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point))
            return false;
        if (obj == this)
            return true;

        Point rhs = (Point) obj;
        return new EqualsBuilder().
                append(getRow(), rhs.getRow()).
                append(getColumn(), rhs.getColumn()).
                isEquals();
    }
}
