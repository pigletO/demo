package com.example.demo;

import com.example.demo.entity.Humen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private Humen humen;
    @Test
    public void contextLoads() {
        System.out.println(humen);
        System.out.println("abc");
    }

}
