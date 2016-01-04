package db61b;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Tests of TableIterator
 *
 * @author Truong Pham
 */
public class TableIteratorTest {
    @Test
    public void testTable() {
        String[] columnTitles = { "Title1", "Title2", "Title3" };
        Table table = new Table("table", columnTitles);

        ArrayList<Column> l1 = new ArrayList<Column>();
        l1.add(new Literal("row1Column1"));
        l1.add(new Literal("row1Column2"));
        l1.add(new Literal("row1Column3"));
        Row row1 = new Row(l1);

        ArrayList<Column> l2 = new ArrayList<Column>();
        l2.add(new Literal("row2Column1"));
        l2.add(new Literal("row2Column2"));
        l2.add(new Literal("row2Column3"));
        Row row2 = new Row(l2);

        table.add(row1);
        table.add(row2);

        TableIterator iter = new TableIterator(table);

        assertEquals(2, iter.table().size());
        assertEquals(1, iter.table().columnIndex("Title2"));
        assertEquals(-1, iter.table().columnIndex("Title4"));
        assertEquals(3, iter.table().numColumns());
        assertEquals(true, iter.hasRow());
        assertEquals(true, iter.hasRow());

        iter.next();
        assertEquals(true, iter.hasRow());
        iter.next();
        assertEquals(false, iter.hasRow());

        iter.reset();
        assertEquals(true, iter.hasRow());
    }
}
