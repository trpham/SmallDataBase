package db61b;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static db61b.Utils.*;

/**
 * A single table in a database.
 *
 * @author Truong Pham
 */
class Table implements Iterable<Row> {
    /**
     * A new Table named NAME whose columns are give by COLUMNTITLES, which must
     * be distinct (else exception thrown).
     */
    Table(String name, String[] columnTitles) {
        _name = name;
        _columns = new HashSet<Column>();
        _rows = new HashSet<Row>();
        _titles = new String[columnTitles.length];

        for (int i = 0; i < columnTitles.length; i += 1) {
            _titles[i] = columnTitles[i];
        }

        for (int i = 0; i < columnTitles.length; i += 1) {
            _columns.add(new Column(this, columnTitles[i]));
        }
    }

    /** A new Table named NAME whose column names are give by COLUMNTITLES. */
    Table(String name, List<String> columnTitles) {
        this(name, columnTitles.toArray(new String[columnTitles.size()]));
    }

    /** Return the number of columns in this table. */
    int numColumns() {
        return _titles.length;
    }

    /** Returns my name. */
    String name() {
        return _name;
    }

    /** Returns a TableIterator over my rows in an unspecified order. */
    TableIterator tableIterator() {
        return new TableIterator(this);
    }

    /** Returns an iterator that returns my rows in an unspecified order. */
    @Override
    public Iterator<Row> iterator() {
        return _rows.iterator();
    }

    /** Return the title of the Kth column. Requires 0 <= K < columns(). */
    String title(int k) {
        return _titles[k];
    }

    /**
     * Return the number of the column whose title is TITLE, or -1 if there
     * isn't one.
     */
    int columnIndex(String title) {
        for (int i = 0; i < _titles.length; i += 1) {
            if (_titles[i].equals(title)) {
                return i;
            }
        }
        return -1;
    }

    /** Return the number of Rows in this table. */
    int size() {
        return _rows.size();
    }

    /**
     * Add ROW to THIS if no equal row already exists. Return true if anything
     * was added, false otherwise.
     */
    boolean add(Row row) {
        return _rows.add(row);
    }

    /**
     * Read the contents of the file NAME.db, and return as a Table. Format
     * errors in the .db file cause a DBException.
     */
    static Table readTable(String name) {
        BufferedReader input;
        Table table;
        input = null;
        table = null;

        try {
            input = new BufferedReader(new FileReader(name + ".db"));
            String header = input.readLine();
            if (header == null) {
                throw error("missing header in DB file");
            }
            String[] columnNames = header.split(",");

            int size = columnNames.length;

            table = new Table(name, columnNames);

            table._titles = new String[size];

            for (int i = 0; i < columnNames.length; i += 1) {
                table._titles[i] = columnNames[i];
                table._columns.add(new Column(table, table._titles[i]));
            }

            String aLine;
            while ((aLine = input.readLine()) != null) {
                String[] line = aLine.split(",");
                table.add(new Row(line));
            }

            System.out.println("Loaded " + name + ".db");

        } catch (FileNotFoundException e) {
            throw error("could not find %s.db", name);
        } catch (IOException e) {
            throw error("problem reading from %s.db", name);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    /* Ignore IOException */
                }
            }
        }
        return table;
    }

    /**
     * Write the contents of TABLE into the file NAME.db. Any I/O errors cause a
     * DBException.
     */
    void writeTable(String name) {
        PrintStream output;
        output = null;

        try {
            String sep;
            sep = "";
            output = new PrintStream(name + ".db");
            for (int i = 0; i < _titles.length; i += 1) {
                if (i == _titles.length - 1) {
                    output.print(_titles[i]);
                } else {
                    output.print(_titles[i] + ",");
                }
            }

            output.println();

            Iterator<Row> iter = _rows.iterator();

            while (iter.hasNext()) {
                Row r = iter.next();
                String[] aRow = r.getData();
                for (int i = 0; i < aRow.length; i += 1) {
                    if (i == aRow.length - 1) {
                        output.print(aRow[i]);
                    } else {
                        output.print(aRow[i] + ",");
                    }
                }
                output.println();
            }

            System.out.println("Stored " + name + ".db");

        } catch (IOException e) {
            throw error("trouble writing to %s.db", name);
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    /**
     * Print my contents on the standard output, separated by spaces and
     * indented by two spaces.
     */
    void print() {

        Iterator<Row> iter = _rows.iterator();

        while (iter.hasNext()) {
            Row r = iter.next();
            String[] aRow = r.getData();
            System.out.print("  ");
            for (String elem : aRow) {
                System.out.print(elem + " ");
            }
            System.out.println("");
        }
    }

    /** My name. */
    private final String _name;
    /** My column titles. */
    private String[] _titles;
    /** HashSet of Rows. */
    private HashSet<Row> _rows;
    /** HashSet of Columns. */
    private HashSet<Column> _columns;

}
