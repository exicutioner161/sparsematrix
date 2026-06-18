
import java.util.HashMap;
import java.util.Map;

/**
 * A sparse matrix implementation backed by a {@link HashMap} mapping integer
 * keys
 * to {@link Cell} instances. Keys are computed as (row * numColumns) + col.
 *
 * @param <T> element type stored in the matrix
 */
public class SparseMatrix<T> implements Matrixable<T> {
   private final int rows;
   private final int cols;
   private final Map<Integer, Cell<T>> map;
   private char blank;

   /**
    * Constructs a new SparseMatrix with the specified dimensions.
    *
    * @param rows number of rows (non-negative)
    * @param cols number of columns (non-negative)
    */
   public SparseMatrix(int rows, int cols) {
      this.rows = rows;
      this.cols = cols;
      this.blank = '-';
      this.map = new HashMap<>();
   }

   /**
    * Returns the element at the given coordinates, or null if out of bounds or
    * absent.
    *
    * @param r row index (0-based)
    * @param c column index (0-based)
    * @return element at (r,c) or null
    */
   @Override
   public T get(int r, int c) {
      if (r < 0 || r >= rows) {
         return null;
      }
      if (c < 0 || c >= cols) {
         return null;
      }
      var cell = map.get(getKey(r, c));
      return cell == null ? null : cell.getValue();
   }

   /**
    * Sets the value at (r,c). If no cell exists at the coordinates, a new
    * cell is created.
    *
    * @param r row index (0-based)
    * @param c column index (0-based)
    * @param x new value (must not be null)
    * @return previous value at (r,c) or null if none
    */
   @Override
   public T set(int r, int c, T x) {
      if (x == null) {
         return null;
      }
      if (r < 0 || r >= rows) {
         return null;
      }
      if (c < 0 || c >= cols) {
         return null;
      }
      var cell = map.get(getKey(r, c));
      if (cell == null) {
         map.put(getKey(r, c), new Cell<>(r, c, x));
         return null;
      }
      return cell.setValue(x);
   }

   /**
    * Adds or replaces the value at the given coordinates.
    *
    * @param r row index (0-based)
    * @param c column index (0-based)
    * @param x value to add (must not be null)
    * @return true if the operation succeeded
    */
   @Override
   public boolean add(int r, int c, T x) {
      if (x == null) {
         return false;
      }
      if (r < 0 || r >= rows) {
         return false;
      }
      if (c < 0 || c >= cols) {
         return false;
      }

      int keyToAdd = getKey(r, c);
      if (map.containsKey(keyToAdd)) {
         map.get(keyToAdd).setValue(x);
      } else {
         map.put(keyToAdd, new Cell<>(r, c, x));

      }
      return true;
   }

   /**
    * Removes and returns the value at the given coordinates.
    *
    * @param r row index (0-based)
    * @param c column index (0-based)
    * @return removed value or null if none
    */
   @Override
   public T remove(int r, int c) {
      if (r < 0 || r >= rows || c < 0 || c >= cols) {
         return null;
      }
      var cell = map.remove(getKey(r, c));
      return cell == null ? null : cell.getValue();
   }

   /**
    * Computes a compact integer key for a coordinate pair. Note: this uses
    * {@code int} arithmetic and may overflow for extremely large dimensions.
    */
   private int getKey(int r, int c) {
      return (r * cols) + c;
   }

   /** {@inheritDoc} */
   @Override
   public int size() {
      return map.size();
   }

   /** {@inheritDoc} */
   @Override
   public int numRows() {
      return rows;
   }

   /** {@inheritDoc} */
   @Override
   public int numColumns() {
      return cols;
   }

   /**
    * Returns true if any stored cell contains the given value.
    *
    * @param x value to search for
    * @return true when found
    */
   @Override
   public boolean contains(T x) {
      if (x == null) {
         return false;
      }
      for (var cell : map.values()) {
         if (cell.getValue().equals(x)) {
            return true;
         }
      }
      return false;
   }

   /**
    * Returns the coordinates of the first cell containing {@code x}, or null if
    * not found.
    *
    * @param x value to find
    * @return int[] {row, col} or null
    */
   @Override
   public int[] getLocation(T x) {
      if (x == null) {
         return null;
      }
      for (var cell : map.values()) {
         if (cell.getValue().equals(x)) {
            return new int[] { cell.getRow(), cell.getCol() };
         }
      }

      return null;
   }

   /**
    * Produces a dense {@code T[][]} with stored values placed at their
    * coordinates; empty slots are {@code null}.
    * <p>
    * Note: this implementation allocates an {@code Object[][]} and casts it to
    * {@code T[][]} (unchecked).
    *
    * @return a 2D array of type {@code T[][]} with dimensions
    *         {@code [numRows()][numColumns()]}
    */
   @SuppressWarnings("unchecked")
   @Override
   public T[][] toArray() {
      Object[][] arr = new Object[rows][cols];
      for (var cell : map.values()) {
         arr[cell.getRow()][cell.getCol()] = cell.getValue();
      }

      return (T[][]) arr;
   }

   /** {@inheritDoc} */
   @Override
   public boolean isEmpty() {
      return map.isEmpty();
   }

   /** {@inheritDoc} */
   @Override
   public void clear() {
      map.clear();
   }

   /**
    * Sets the printable character used for empty slots when converting to
    * {@link String}.
    *
    * @param blank printable character for blanks
    */
   @Override
   public void setBlank(char blank) {
      this.blank = blank;
   }

   /**
    * Returns a human-readable representation of the matrix using {@link #setBlank}
    * for empty slots.
    */
   @Override
   public String toString() {
      var sb = new StringBuilder();
      var arr = toArray();
      final int lastCol = cols - 1;
      for (int r = 0; r < rows; r++) {
         for (int c = 0; c < cols - 1; c++) {
            var valToPrint = arr[r][c] == null ? blank : arr[r][c];
            sb.append(valToPrint).append(" ");
         }
         var valToPrint = arr[r][lastCol] == null ? blank : arr[r][lastCol];
         sb.append(valToPrint).append("\n");
      }
      return sb.toString();
   }
}