package xyz.erupt.eql;

import org.junit.Before;
import org.junit.Test;
import xyz.erupt.eql.data.Master;
import xyz.erupt.eql.data.Table2;
import xyz.erupt.eql.grammar.OrderBy;
import xyz.erupt.eql.lambda.LambdaReflect;
import xyz.erupt.eql.util.Columns;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LinqTest {

    List<Master> source = new ArrayList<>();

    List<Table2> target = new ArrayList<>();


    @Before
    public void before() {
        source.add(new Master(2, "bb", new Date()));
        source.add(new Master(9, "cc", new Date()));
        source.add(new Master(2, "cc", new Date()));
        source.add(new Master(4, "cc", new Date()));
        source.add(new Master(null, null, new Date()));

        target.add(new Table2(1, "a"));
        target.add(new Table2(2, "c"));
        target.add(new Table2(99, "c"));
    }

    @Test
    public void simple() {
        List<Master> result = Linq.from(source)
//                .leftJoin(target, Table2::getAge, Master::getAge)
                .innerJoin(target, Table2::getAge, Master::getAge)
//                .join(JoinMethod.LEFT, target, (l, r) -> l.getName().equals(r.get(Columns.of(Master::getAge))))
//                .eq(Table2::getName, "a")
//                .gt(Table2::getAge, 1)
                .groupBy(Columns.of(Master::getName))
                .having()
                .orderBy(Master::getAge)
                .select(
                        Columns.max(Master::getAge, "max"),
                        Columns.min(Table2::getName, "min"),
                        Columns.count(Table2::getName, "count"),
                        Columns.ofs(m -> m.get("xx"), "xxx"),
                        Columns.all(Master.class)
                )
                .distinct()
                .limit(9)
                .offset(0)
                .write(Master.class);
        System.out.println(result);
    }


    @Test
    public void groupBy() {
        List<Master> result = Linq.from(source)
                .groupBy(Columns.of(Master::getName))
                .select(
                        Columns.of(Master::getAge,"age_xxx"),
                        Columns.sum(Master::getAge, "sum"),
                        Columns.avg(Master::getAge, "avg"),
                        Columns.count(Master::getName, "ncount")
                )
                .write(Master.class);
    }

    @Test
    public void orderBy() {
        List<Master> result = Linq.from(source)
                .orderBy(Master::getAge, OrderBy.Direction.DESC)
                .orderBy(Master::getName)
                .write(Master.class);
    }

    @Test
    public void testLambdaInfo() {
        assert "age".equals(LambdaReflect.getInfo(Master::getAge).getField());
        assert "name".equals(LambdaReflect.getInfo(Master::getName).getField());
        assert Master.class == LambdaReflect.getInfo(Master::getName).getClazz();
    }

    @Test
    public void conditionEqTest() {
        Linq.from(source).ne(Master::getName, 1).write(null);
    }

    @Test
    public void conditionInTest() {
        Linq.from(source).in(Master::getAge, 1, null).write(null);
    }

    @Test
    public void conditionNotInTest() {
        Linq.from(source).notIn(Master::getAge, 1, 2, null).write(null);
    }

    @Test
    public void conditionLikeTest() {
        Linq.from(source).like(Master::getName, 'a').write(null);
    }

    @Test
    public void conditionLtTest() {
        Linq.from(source).gt(Master::getName, "bb").write(null);
    }


    @Test
    public void eqTest() {
        Date date = new Date();
        Float a = 1.11F;
        Float b = 1.01F;
        System.out.println(a.hashCode() > b.hashCode());
        assert eq(date, date);
    }

    @Test
    public void compareTest() {
        Date date = new Date();
        Date date2 = new Date();
        System.out.println(date.compareTo(date));
    }

    private boolean eq(Object a, Object b) {
        return a.equals(b);
    }

}
