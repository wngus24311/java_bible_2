package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.util.ValidationEventCollector;
import java.util.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	QueueEx1 q;

	@Test
	void contextLoads() {
		System.out.println("테스트임");

	}
	String[] args = {"\"((2+3)*1+3\""};
	@Test
	void myStackTest() {
		if (args.length != 1) {
			System.out.println("Usage : java ExpValidCheck \"EXPRESSION\"");
			System.out.println("Example : java ExpValidCheck \"((2+3)*1)+3\"");
			System.out.println("");
		}

		Stack st = new Stack();
		String expression = args[0];

		System.out.println("expression : " + expression);

		try {
			for (int i=0; i < expression.length(); i++) {
				char ch = expression.charAt(i);

				if (ch == '(') {
					st.push(ch+"");
				} else if (ch == ')') {
					st.pop();
				}
			}

			if (st.isEmpty()) {
				System.out.println("괄호가 일치합니다.");
			} else {
				System.out.println("괄호가 일치하지 않습니다.");
			}

		} catch (EmptyStackException e) {
			System.out.println("괄호가 일치하지 않습니다.");
		}

	}

	@Test
	void testQueue() {
		System.out.println("help를 입력하면 도움말을 볼 수 있습니다.");
		System.out.println(args.length);

		while (true) {
			System.out.print(">>");
			try {
				// 화면으로부터 라인단위로 입력받는다.
//				Scanner s = new Scanner(System.in);
				String s = "dir";
				String input = s.trim();

				if ("".equals(input))
					continue;

				if (input.equalsIgnoreCase("q")) {
					System.exit(0);
				} else if (input.equalsIgnoreCase("help")) {
					System.out.println(" help - 도움말을 보여줍니다.");
					System.out.println(" q 또는 Q - 프로그램을 종료합니다.");
					System.out.println(" history - 최근에 입력한 명령어를 " + q.MAX_SIZE + "개 보여줍니다.");
				} else if (input.equalsIgnoreCase("history")) {
					int i = 0;
					// 입력받은 명령어를 저장하고,
					q.save(input);

					// LinkedList의 내용을 보여준다.
					LinkedList tmp = (LinkedList) q.q;
					ListIterator it = tmp.listIterator();

					while (it.hasNext()) {
						System.out.println(++i + "." + it.next());
					}
				} else {
					q.save(input);
					System.out.println(input);
				}
			} catch (Exception e) {
				System.out.println("입력 오류입니다.");
			}

		}

	}

	@Test
	public void priorityQueue() {
		Queue pq = new PriorityQueue();
		pq.offer(3);
		pq.offer(1);
		pq.offer(5);
		pq.offer(2);
		pq.offer(4);
		System.out.println("pq : " + pq);

		Object obj = null;

		while ((obj = pq.poll()) != null){
			System.out.println(obj);
		}

	}

	@Test
	public void iteratorTest() {
		ArrayList list = new ArrayList();
		list.add("2");
		list.add("1");
		list.add("3");
		list.add("4");
		list.add("5");

		Iterator it = list.iterator();

		while (it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
		}
	}

}