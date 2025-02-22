package com.alibaba.json.bvt.issue_1700;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.spi.Module;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Issue1780_Module {
    @Test
	public void test_for_issue() {
		org.json.JSONObject req = new org.json.JSONObject();

		SerializeConfig config = new SerializeConfig();
//		config.register(new myModule());
		req.put("id", 1111);
		req.put("name", "name11");
		String text = JSON.toJSONString(req, SerializerFeature.SortField);

		assertTrue("{\"id\":1111,\"name\":\"name11\"}".equals(text) || "{\"name\":\"name11\",\"id\":1111}".equals(text));
	}

	public class myModule
            implements Module {

		@SuppressWarnings("rawtypes")
		public ObjectDeserializer createDeserializer(ParserConfig config, Class type) {
			return null;
		}

		@SuppressWarnings("rawtypes")
		public ObjectSerializer createSerializer(SerializeConfig config, Class type) {
			return new ObjectSerializer() {

				public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType,
						int features) throws IOException {
					System.out.println("-------------myModule.createSerializer-------------------");
					org.json.JSONObject req = (org.json.JSONObject) object;
					serializer.out.write(req.toString());
				}
			};
		}

	}
}
