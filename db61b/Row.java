package db61b;

import java.util.Arrays;
import java.util.List;

/**
 * A single row of a database.
 * @author Truong Pham
 */
class Row {
    /**
     * A Row whose column values are DATA. The array DATA must not be altered
     * subsequently.
     */
    Row(String[] data) {
        setData(data);
    }

    /**
     * Return a Row formed from the current values of COLUMNS (in order).
     * COLUMNS must all have been resolved to non-empty TableIterators.
     */
    static Row make(List<Column> columns) {

        String[] columnData = new String[columns.size()];

        for (int i = 0; i < columns.size(); i += 1) {
            columnData[i] = columns.get(i).value();
        }
        return new Row(columnData);
    }

    /**
     * A Row whose column values are extracted by COLUMNS from ROWS (see
     * {@link db61b.Column#Column}).
     */
    Row(List<Column> columns) {
        setData(new String[columns.size()]);
        for (int i = 0; i < columns.size(); i += 1) {
            getData()[i] = columns.get(i).value();
        }
    }

    /** Return my number of columns. */
    int size() {
        return getData().length;
    }

    /** Return the value of my Kth column. Requires that 0 <= K < size(). */
    String get(int k) {
        return getData()[k];
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return Arrays.equals(getData(), ((Row) obj).getData());
        } catch (ClassCastException e) {
            return false;
        }
    }

    /*
     * NOTE: Whenever you override the .equals() method for a class, you should
     * also override hashCode so as to insure that if two objects are supposed
     * to be equal, they also return the same .hashCode() value (the converse
     * need not be true: unequal objects MAY also return the same .hashCode()).
     * The hash code is used by certain Java library classes to expedite
     * searches (see Chapter 7 of Data Structures (Into Java)).
     */

    @Override
    public int hashCode() {
        return Arrays.hashCode(getData());
    }

    /** Return Data. */
    String[] getData() {
        return _data;
    }

    /**
     * Set Data.
     * @param data
     *            array of string.
     */
    void setData(String[] data) {
        _data = data;
    }

    /** Contents of this row. */
    private String[] _data;

}
