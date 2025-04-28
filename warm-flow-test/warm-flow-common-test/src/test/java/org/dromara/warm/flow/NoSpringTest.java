package org.dromara.warm.flow;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NoSpringTest {

    @Test
    public void test() {

        Map<String, Object> map1 = new HashMap<>();
        map1.put("a", "a");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("b", "b");
        Map<String, Object> map3 = new HashMap<>();
        map3.putAll(map1);
        map3.putAll(map2);
        System.out.println(map3);
        map3.put("b", "c");
        System.out.println(map2);
        System.out.println(map3);
    }
}
