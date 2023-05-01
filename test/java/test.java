import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/**/applicationContext.xml"})
public class test {
    @Test
    public void just() {
        System.out.println("HI");
    }

    @Test
    public void prac() {
        int[] numArr = new int[10];

        for (int i=0; i < numArr.length; i++) {
            System.out.print(numArr[i] = (int)(Math.random() * 10));
        }
        System.out.println();

        for (int i=0; i < numArr.length - 1; i++) {
            boolean change = false;

            for (int j=0; j < numArr.length - 1 - i;  j++) {
                if (numArr[j] > numArr[j+1]) {
                    int tmp = numArr[j];
                    numArr[j] = numArr[j+1];
                    numArr[j+1] = tmp;
                    change = true;
                }
            }
            if (!change) break;
        }

        for (int k=0; k < numArr.length; k++) {
            System.out.print(numArr[k]);
        }
        System.out.println();

    }

    @Test
    public void prac2() {
        int[] numArr = new int[10];
        int[] counter = new int[10];

        for (int i=0; i < numArr.length; i++) {
            numArr[i] = (int)(Math.random() * 10);
            System.out.print(numArr[i]);
        }

        System.out.println();

        for (int i=0; i < numArr.length; i++) {
            counter[numArr[i]]++;
        }

        for (int i=0; i < counter.length; i++) {
            System.out.println(i + "의 갯수 = " + counter[i]);
        }

    }

    @Test
    public void prac3() {
        int[][] score = {
                {100, 100, 100},
                {20, 20, 20},
                {30, 30, 30},
                {40, 40, 40},
                {50, 50, 50}
        };

        int korTotal = 0;
        int engTotal = 0;
        int mathTotal = 0;

        System.out.println("번호  국어  영어  수학  총점  평균");
        System.out.println("===========================");

        for (int i=0; i < score.length; i++) {
            int sum = 0;
            float avg = 0.0f;

            korTotal += score[i][0];
            engTotal += score[i][1];
            mathTotal += score[i][2];

            System.out.printf("%3d", i+1);

            for (int j=0; j < score[i].length; j++) {
                sum += score[i][j];
                System.out.printf("%5d", score[i][j]);
            }

            avg = sum / (float)score[i].length;
            System.out.printf("%5d %5.1f%n", sum, avg);

        }

        System.out.println("========================");
        System.out.printf("총점:%3d  %4d  %4d%n", korTotal, engTotal, mathTotal);

    }

    @Test
    public void test() {
        int result = factorial(10);
        System.out.println("result = " + result);
    }

    int factorial(int n) {
        int result = 0;

        if (n == 1) {
            result = 1;
        } else {
            result = n * factorial(n-1);
        }
        return result;
    }

    @Test
    public void test1() {
        StringBuffer sb = new StringBuffer("abc");
        StringBuffer sb2 = new StringBuffer("abc");

        System.out.println("sb == sb2 ? " + (sb ==sb2));
        System.out.println("sb.equals(sb2) ? " + sb.equals(sb2));

        String s = sb.toString();
        String s2 = sb2.toString();

        System.out.println("s.equals(s2) ? " + s.equals(s2));
    }

    @Test
    public void tt() {
        Integer x = 400;
        Integer y = x;
        x++;
        StringBuilder sb1 = new StringBuilder("123");
        StringBuilder sb2 = sb1;
        sb1.append("5");
        System.out.println((x == y) + "" + (sb1==sb2));
        System.out.println("x = " + x + " " + y);
    }

    @Test
    public void math() {
        String source = "HP:011-1111-1111, HOME:02-999-9999";
        String pattern = "(0\\d{1,2})-(\\d{3,4})-(\\d{4})";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);

        int i=0;
        while (m.find()) {
            System.out.println(++i + ": " + m.group() + " -> " + m.group(1)
                    + ", " + m.group(2) + ", " + m.group(3));
        }

    }

    @Test
    public void exp() {
        String input = "백";
        System.out.println(input);
        System.out.println(hangulToNum(input));

    }

    long hangulToNum(String input) {
        long result = 0;
        long tmpResult = 0;
        long num = 0;

        final String NUMBER = "영일이삼사오육칠팔구";
        final String UNIT = "십백천만억조";
        final long[] UNIT_NUM = {10, 100, 1000, 10000, (long) 1e8, (long) 1e12};

        StringTokenizer st = new StringTokenizer(input, UNIT, true);

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            int check = NUMBER.indexOf(token);

            if (check == -1) {
                if ("만억조".indexOf(token) == -1) {
                    tmpResult += (num != 0 ? num : 1) * UNIT_NUM[UNIT.indexOf(token)];
                } else {
                    tmpResult += num;
                    result += (tmpResult != 0 ? tmpResult : 1) * UNIT_NUM[UNIT.indexOf(token)];
                    tmpResult = 0;
                }
                num = 0;
            } else {
                num = check;
            }
        }

        return result + tmpResult + num;

    }

    @Test
    public void dayByDay() {
        Calendar today = Calendar.getInstance();
        System.out.println("이 해의 년도 : " + today.get(Calendar.YEAR));
        System.out.println("월 (0~11, 0:1월) : " + (today.get(Calendar.MONTH) + 1));
        System.out.println("이 해의 몇 째 주 : " + today.get(Calendar.WEEK_OF_YEAR));
        System.out.println("이 달의 맻 째 주 : " + today.get(Calendar.WEEK_OF_MONTH));

        // DATE와 DAY_OF_MONTH는 같다.
        System.out.println("이 달의 몇 일 : " + today.get(Calendar.DATE));
        System.out.println("이 달의 몇 일 : " + today.get(Calendar.DAY_OF_MONTH));
        System.out.println("이 해의 몇 일 : " + today.get(Calendar.DAY_OF_YEAR));
        System.out.println("요일(1~7, 1:일요일) : " + today.get(Calendar.DAY_OF_WEEK));
        System.out.println("이 달의 몇 째 요일 : " + today.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("오전_오후 (0:오전, 1:오후) : " + today.get(Calendar.AM_PM));
        System.out.println("시간 (0~11) : " + today.get(Calendar.HOUR));
        System.out.println("시간 (0~23) : " + today.get(Calendar.HOUR_OF_DAY));
        System.out.println("분 (0~59) : " + today.get(Calendar.MINUTE));
        System.out.println("초 (0~59) : " + today.get(Calendar.SECOND));
        System.out.println("1000분의 1초 (0~999) : " + today.get(Calendar.MILLISECOND));

        // 천분의 1초를 시간으로 표시하기 위해 3600000으로 나누었다. (1시간 = 60 * 60초)
        System.out.println("TimeZone(-12~+12) : " + today.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000));	// 60*60*1000;
        System.out.println("이 달의 마지막 날: " + today.getActualMaximum(Calendar.DATE));
    }

    @Test
    public void calendarEx2() {
        String pattern = "yyyy/MM/dd";
        DateFormat df = new SimpleDateFormat(pattern);
        Scanner s = new Scanner(System.in);

        Date inDate = null;

        System.out.println("날짜를 " + pattern + "의 형태로 입력해주세요.(입력예:2015/12/31)");
        String str = "2015/12/31";
        while (str.equals("2015/12/31")) {
            try {
                inDate = df.parse(s.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("날짜를 " + pattern + "의 형태로 다시 입력해주세여.(입력예:2015/12/31)");
            }
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(inDate);
        Calendar today = Calendar.getInstance();
        long day = (cal.getTimeInMillis() - today.getTimeInMillis()) / (60 * 60 * 1000);
        System.out.println("입력하신 날짜는 현재와 " + day + "시간 차이가 있습니다.");

    }

    @Test
    public void choiceFormat() {
        String pattern = "60#D|70#C|80<B|90#A";
        int[] scores = {91, 90, 80, 88, 70, 52, 60};

        ChoiceFormat form = new ChoiceFormat(pattern);

        for (int i=0; i < scores.length; i++) {
            System.out.println(scores[i] + ":" + form.format(scores[i]));
        }
    }

    @Test
    public void MessageFormat() {
        String tableName = "CUST_INFO";
        String msg = "INSERT INTO " + tableName + " VALUES ('' {0} '', '' {1} '', '' {2} '', '' {3} '');";

        Object[][] arguments = {
                {"이자바", "02-123-1234", "27", "07-09"},
                {"김프로", "032-333-1234", "33", "10-07"}
        };

        for (int i=0; i < arguments.length; i++) {
            String result = MessageFormat.format(msg, arguments[i]);
            System.out.println(result);
        }
    }

    @Test
    public void time() {
        LocalDate date = LocalDate.of(2015, 12, 31);
        LocalTime time = LocalTime.of(12, 34, 56);

        LocalDateTime dt = LocalDateTime.of(date, time);

        ZoneId zid = ZoneId.of("Asia/Seoul");
        ZonedDateTime zdt = dt.atZone(zid);

        ZonedDateTime seoulTime = ZonedDateTime.now();
        ZoneId nyid = ZoneId.of("America/New_York");
        ZonedDateTime nyTime = ZonedDateTime.now().withZoneSameInstant(nyid);

        OffsetDateTime odt = zdt.toOffsetDateTime();

        System.out.println("dt = " + dt);
        System.out.println("zid = " + zid);
        System.out.println("zdt = " + zdt);
        System.out.println("seoulTime = " + seoulTime);
        System.out.println("nyTime = " + nyTime);
        System.out.println("odt = " + odt);
    }

    @Test
    public void collectionPrac() {
        final int LIMIT = 10;
        String source = "0123456789abcdefghijABCDEFGHIJ!@#$%^&*()ZZZ";
        int length = source.length();

        List list = new ArrayList(length / LIMIT + 10);	// 크기를 약간 여유 있게 잡는다.

        for (int i=0; i < length; i+=LIMIT) {
            if (i + LIMIT < length) {
                list.add(source.substring(i, i + LIMIT));
            } else {
                list.add(source.substring(i));
            }
        }

        for (int i=0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void linkedListTest() {
        // 추가할 데이터의 개수를 고려해서 충분히 잡아야한다.
        ArrayList al = new ArrayList(4900000);
        LinkedList ll = new LinkedList();

        //

        System.out.println("= 순차적으로 추가하기 =");
        System.out.println("ArrayList : " + add1(al));
        System.out.println("LinkedList : " + add1(ll));
        System.out.println();
        System.out.println("= 중간에 추가하기 =");
        System.out.println("ArrayList : " + add2(al));
        System.out.println("LikedList : " + add2(ll));
        System.out.println();
        System.out.println("= 중간에서 삭제하기 =");
        System.out.println("ArrayList : " + remove2(al));
        System.out.println("LinkedList : " + remove2(ll));
        System.out.println();
        System.out.println("= 순차적으로 삭제하기 =");
        System.out.println("ArrayList : " + remove1(al));
        System.out.println("LinkedList : " + remove1(ll));
    }

    long add1(List list) {
        long start = System.currentTimeMillis();

        for (int i=0; i < 2000000; i++) {
            list.add(i + "");
        }

        long end = System.currentTimeMillis();
        return  end - start;
    }

    long add2(List list) {
        long start = System.currentTimeMillis();

        for (int i=0; i < 10000; i++) {
            list.add(500, "X");
        }

        long end = System.currentTimeMillis();

        return end - start;
    }

    long remove1(List list) {
        long start = System.currentTimeMillis();

        for (int i=list.size() - 1; i >= 0; i--) {
            list.remove(i);
        }

        long end = System.currentTimeMillis();

        return end - start;
    }

    long remove2(List list) {
        long start = System.currentTimeMillis();

        for (int i=0; i < 10000; i++) list.remove(i);

        long end = System.currentTimeMillis();

        return end - start;
    }

}
