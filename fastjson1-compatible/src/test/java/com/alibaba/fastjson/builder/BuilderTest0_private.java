package com.alibaba.fastjson.builder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuilderTest0_private {
    @Test
    public void test_0() throws Exception {
        VO vo = JSON.parseObject("{\"id\":12304,\"name\":\"ljw\"}", VO.class);

        Assertions.assertEquals(12304, vo.getId());
        Assertions.assertEquals("ljw", vo.getName());
    }

    @JSONType(builder=VOBuilder.class)
    public static class VO {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    private static class VOBuilder {
        private VO vo = new VO();

        public VO build() {
            return vo;
        }

        public VOBuilder withId(int id) {
            vo.id = id;
            return this;
        }

        public VOBuilder withName(String name) {
            vo.name = name;
            return this;
        }
    }
}
