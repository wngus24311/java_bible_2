package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class QueueEx1 {
    static Queue q = new LinkedList();
    static final int MAX_SIZE = 5;

    public void save(String input) {
        // queue에 저장한다.
        if (!"".equals(input))
            q.offer(input);

        // queue의 최대크기를 넘으면 제일 처음 입력된 것을 삭제한다.
        if (q.size() > MAX_SIZE)
            q.remove();
    }
}
