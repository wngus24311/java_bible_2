import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/applicationContext.xml"})
public class test2 {

    @Test
    public void arrayListLinkedListTest() {
        ArrayList al = new ArrayList(10000000);
        LinkedList ll = new LinkedList();
        add(al);
        add(ll);

        System.out.println("= 접근시간테스트 =");
        System.out.println("ArrayList : " + access(al));
        System.out.println("LinkedList : " + access(ll));
    }

    void add(List list) {
        for (int i=0; i<10000000; i++) {
            list.add(i+"");
        }
    }

    long access(List list) {
        long start = System.currentTimeMillis();
        for (int i=0; i< 100000; i++) {
            list.get(i);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

}
