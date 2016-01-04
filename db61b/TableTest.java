package db61b;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Tests of Table
 * @author Truong Pham
 */
public class TableTest {
    @Test
    public void testTable() {
        String[] columnTitles = { "Title1", "Title2", "Title3" };
        Table table = new Table("table", columnTitles);

        assertEquals("table", table.name());
        assertEquals(3, table.numColumns());
        assertEquals(1, table.columnIndex("Title2"));
        assertEquals("Title2", table.title(1));
        assertEquals(0, table.size());

        ArrayList<Column> l = new ArrayList<Column>();
        l.add(new Literal("column1"));
        l.add(new Literal("column2"));
        l.add(new Literal("column3"));
        Row row = new Row(l);

        boolean checkAdd = table.add(row);
        assertEquals(true, checkAdd);
        assertEquals(1, table.size());

        Table table2 = Table.readTable("students");

        assertEquals("students", table2.name());
        assertEquals(6, table2.numColumns());
        assertEquals(1, table2.columnIndex("Lastname"));
        assertEquals("SID", table2.title(0));
        assertEquals(6, table2.size());
    }

}
