package com.alibaba.fastjson.issue_1100;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Created by wenshao on 19/04/2017.
 */
public class Issue1151 {
    @Test
    public void test_for_issue() throws Exception {
        A a = new A();
        a.list.add(new C(1001));
        a.list.add(new C(1002));

        String json = JSON.toJSONString(a, SerializerFeature.NotWriteRootClassName, SerializerFeature.WriteClassName);
        assertEquals("{\"list\":[{\"@type\":\"com.alibaba.fastjson.issue_1100.Issue1151$C\",\"id\":1001},{\"@type\":\"com.alibaba.fastjson.issue_1100.Issue1151$C\",\"id\":1002}]}", json);

        A a2 = JSON.parseObject(json, A.class, Feature.SupportAutoType);
        assertSame(a2.list.get(0).getClass(), C.class);
    }

    public static class A {
        public List<B> list = new ArrayList<B>();
    }

    public static  interface B {
    }

    public static class C
            implements B {
        public int id;
        public C() {
        }

        public C(int id) {
            this.id = id;
        }
    }
}
