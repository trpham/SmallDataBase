package db61b;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Tests of Row
 * @author Truong Pham
 */
public class RowTest {

    @Test
    public void testRow() {
        ArrayList<Column> l = new ArrayList<Column>();
        l.add(new Literal("column1"));
        l.add(new Literal("column2"));
        l.add(new Literal("column3"));
        l.add(new Literal("column4"));
        Row r = new Row(l);

        assertEquals(4, r.size());
        assertEquals("column2", r.get(1));

        l.add(new Literal("column5"));
        Row r2 = Row.make(l);
        assertEquals(5, r2.size());
        assertEquals("column5", r2.get(4));
    }
}
