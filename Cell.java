import java.util.Objects;

/**
 * A cell storing a value and its coordinates within a matrix.
 *
 * @param <T> the type of the value stored in the cell
 */
public class Cell<T> implements Comparable<Cell<T>> {
    private T value;
    private final int row;
    private final int col;

    /**
     * Constructs a new Cell.
     *
     * @param row   the row index (0-based)
     * @param col   the column index (0-based)
     * @param value the value to store (may be null)
     */
    public Cell(int row, int col, T value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    /**
     * Replaces the stored value with the given one.
     *
     * @param x new value
     * @return the previous value
     */
    public T setValue(T x) {
        T previousValue = value;
        value = x;
        return previousValue;
    }

    /**
     * Returns the stored value.
     *
     * @return the value, possibly null
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns the stored row index.
     *
     * @return the row index (0-based)
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the stored column index.
     *
     * @return the column index (0-based)
     */
    public int getCol() {
        return col;
    }

    /**
     * Equality is based on coordinates only (row and column).
     *
     * @param o other object
     * @return true if the other object is a Cell with the same coordinates
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cell<?>)) {
            return false;

        }
        var cell = (Cell<?>) o;
        return this.row == cell.getRow() && this.col == cell.getCol();
    }

    /**
     * Compares cells by row then column.
     */
    @Override
    public int compareTo(Cell<T> o) {
        int compare = Integer.compare(this.row, o.getRow());
        return compare != 0 ? compare : Integer.compare(this.col, o.getCol());
    }

    /**
     * Computes a hash code based on coordinates.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Returns the string representation of the stored value (or "null").
     */
    @Override
    public String toString() {
        return value == null ? "null" : value.toString();
    }
}