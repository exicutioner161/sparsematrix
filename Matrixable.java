/**
 * Interface representing a generic two-dimensional matrix-like container.
 *
 * @param <T> element type stored in the matrix
 */
public interface Matrixable<T> {
    /**
     * Returns the element at the specified row and column.
     *
     * @param r the row index (0-based)
     * @param c the column index (0-based)
     * @return the element at (r,c), or null if out of bounds or absent
     */
    public T get(int r, int c);

    /**
     * Sets the element at the specified position. Implementations may insert a new
     * element or replace an existing value.
     *
     * @param r the row index (0-based)
     * @param c the column index (0-based)
     * @param x the new value to set
     * @return the previous value at (r,c) or null if none
     */
    public T set(int r, int c, T x);

    /**
     * Adds or replaces a value at the specified position.
     *
     * @param r the row index (0-based)
     * @param c the column index (0-based)
     * @param x the value to add
     * @return true if the value was added or replaced successfully
     */
    public boolean add(int r, int c, T x);

    /**
     * Removes and returns the value at the specified position.
     *
     * @param r the row index (0-based)
     * @param c the column index (0-based)
     * @return the removed value, or null if none or out of bounds
     */
    public T remove(int r, int c);

    /**
     * Returns the number of stored (non-blank) elements.
     *
     * @return count of stored elements
     */
    public int size();

    /**
     * Returns the number of rows in the matrix.
     *
     * @return number of rows
     */
    public int numRows();

    /**
     * Returns the number of columns in the matrix.
     *
     * @return number of columns
     */
    public int numColumns();

    /**
     * Returns whether the matrix contains the given value.
     *
     * @param x value to search for
     * @return true if present
     */
    public boolean contains(T x);

    /**
     * Returns the location of the first occurrence of the given value.
     *
     * @param x value to search for
     * @return an int[] {row, col} if found, otherwise null
     */
    public int[] getLocation(T x);

    /**
     * Converts the matrix to a dense two-dimensional array of element type
     * {@code T}. Empty slots are represented as {@code null}.
     *
     * @return a 2D array of type {@code T[][]} with dimensions
     *         {@code [numRows()][numColumns()]}
     */
    public T[][] toArray();

    /**
     * Returns whether the matrix contains no stored elements.
     *
     * @return true if empty
     */
    public boolean isEmpty();

    /**
     * Removes all stored elements from the matrix.
     */
    public void clear();

    /**
     * Sets the character used when printing empty slots in {@link #toString()}.
     *
     * @param blank character to use for blank slots
     */
    public void setBlank(char blank);
}
