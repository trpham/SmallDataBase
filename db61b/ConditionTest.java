package db61b;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Tests of TableIterator
 *
 * @author Truong Pham
 */
public class ConditionTest {
    @Test
    public void testTable() {
        String[] columnTitles = { "Title1", "Title2", "Title3" };
        Table table = new Table("table", columnTitles);

        ArrayList<Column> l1 = new ArrayList<Column>();
        l1.add(new Literal("row1Column1"));
        l1.add(new Literal("row1Column2"));
        l1.add(new Literal("LOL"));

        ArrayList<Column> l2 = new ArrayList<Column>();
        l2.add(new Literal("row2Column1"));
        l2.add(new Literal("row2Column2"));
        l2.add(new Literal("LOL"));

        Row row1 = new Row(l1);
        Row row2 = new Row(l2);
        table.add(row1);
        table.add(row2);

        Condition cond1 = new Condition(l1.get(0), "=", l1.get(1));
        Condition cond2 = new Condition(l1.get(0), "!=", l1.get(1));
        Condition cond3 = new Condition(l1.get(2), "=", l2.get(2));
        Condition cond4 = new Condition(l1.get(1), ">", l1.get(2));

        assertEquals(false, cond1.test());
        assertEquals(true, cond2.test());
        assertEquals(true, cond3.test());
        assertEquals(true, cond4.test());
    }
}
