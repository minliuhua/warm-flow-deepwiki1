/*
 *    Copyright 2024-2025, Warm-Flow (290631660@qq.com).
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.dromara.warm.flow;


import org.dromara.warm.flow.core.json.JsonConvert;
import org.dromara.warm.flow.core.dto.DefJson;
import org.dromara.warm.plugin.json.JsonConvertFastJson;
import org.dromara.warm.plugin.json.JsonConvertGson;
import org.dromara.warm.plugin.json.JsonConvertJackson;
import org.dromara.warm.plugin.json.JsonConvertSnack;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class JsonTest {


    public void testBase(JsonConvert jsonConvert) {
        String jsonString = "{\n" +
                "\t\"flowCode\":\"leaveFlow-meet-sign\",\n" +
                "\t\"flowName\":\"会签\",\n" +
                "\t\"formCustom\":\"N\",\n" +
                "\t\"formPath\":\"system/leave/approve\",\n" +
                "\t\"version\":\"1\",\n" +
                "\t\"nodeList\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"coordinate\":\"120,280|120,280\",\n" +
                "\t\t\t\"nodeCode\":\"1\",\n" +
                "\t\t\t\"nodeName\":\"开始\",\n" +
                "\t\t\t\"nodeRatio\":0.000,\n" +
                "\t\t\t\"nodeType\":0,\n" +
                "\t\t\t\"skipList\":[\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"coordinate\":\"140,280;230,280\",\n" +
                "\t\t\t\t\t\"nextNodeCode\":\"2\",\n" +
                "\t\t\t\t\t\"nowNodeCode\":\"1\",\n" +
                "\t\t\t\t\t\"skipType\":\"PASS\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"coordinate\":\"280,280|280,280\",\n" +
                "\t\t\t\"nodeCode\":\"2\",\n" +
                "\t\t\t\"nodeName\":\"待提交\",\n" +
                "\t\t\t\"nodeRatio\":0.000,\n" +
                "\t\t\t\"nodeType\":1,\n" +
                "\t\t\t\"permissionFlag\":\"1,2,100\",\n" +
                "\t\t\t\"skipList\":[\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"coordinate\":\"330,280;430,280\",\n" +
                "\t\t\t\t\t\"nextNodeCode\":\"3\",\n" +
                "\t\t\t\t\t\"nowNodeCode\":\"2\",\n" +
                "\t\t\t\t\t\"skipType\":\"PASS\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"coordinate\":\"480,280|480,280\",\n" +
                "\t\t\t\"nodeCode\":\"3\",\n" +
                "\t\t\t\"nodeName\":\"组长审批\",\n" +
                "\t\t\t\"nodeRatio\":100.000,\n" +
                "\t\t\t\"nodeType\":1,\n" +
                "\t\t\t\"permissionFlag\":\"1,2,100\",\n" +
                "\t\t\t\"skipList\":[\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"coordinate\":\"530,280;650,280\",\n" +
                "\t\t\t\t\t\"nextNodeCode\":\"4\",\n" +
                "\t\t\t\t\t\"nowNodeCode\":\"3\",\n" +
                "\t\t\t\t\t\"skipType\":\"PASS\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"coordinate\":\"700,280|700,280\",\n" +
                "\t\t\t\"nodeCode\":\"4\",\n" +
                "\t\t\t\"nodeName\":\"部门经理审批\",\n" +
                "\t\t\t\"nodeRatio\":0.000,\n" +
                "\t\t\t\"nodeType\":1,\n" +
                "\t\t\t\"permissionFlag\":\"1,2,100\",\n" +
                "\t\t\t\"skipList\":[\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"coordinate\":\"750,280;870,280\",\n" +
                "\t\t\t\t\t\"nextNodeCode\":\"5\",\n" +
                "\t\t\t\t\t\"nowNodeCode\":\"4\",\n" +
                "\t\t\t\t\t\"skipType\":\"PASS\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"coordinate\":\"700,240;700,210;280,210;280,240\",\n" +
                "\t\t\t\t\t\"nextNodeCode\":\"2\",\n" +
                "\t\t\t\t\t\"nowNodeCode\":\"4\",\n" +
                "\t\t\t\t\t\"skipType\":\"REJECT\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"coordinate\":\"920,280|920,280\",\n" +
                "\t\t\t\"nodeCode\":\"5\",\n" +
                "\t\t\t\"nodeName\":\"hr审批\",\n" +
                "\t\t\t\"nodeRatio\":0.000,\n" +
                "\t\t\t\"nodeType\":1,\n" +
                "\t\t\t\"permissionFlag\":\"1,2,100\",\n" +
                "\t\t\t\"skipList\":[\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"coordinate\":\"970,280;1100,280\",\n" +
                "\t\t\t\t\t\"nextNodeCode\":\"6\",\n" +
                "\t\t\t\t\t\"nowNodeCode\":\"5\",\n" +
                "\t\t\t\t\t\"skipType\":\"PASS\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"coordinate\":\"1120,280|1120,280\",\n" +
                "\t\t\t\"nodeCode\":\"6\",\n" +
                "\t\t\t\"nodeName\":\"结束\",\n" +
                "\t\t\t\"nodeRatio\":0.000,\n" +
                "\t\t\t\"nodeType\":2,\n" +
                "\t\t\t\"skipList\":[\n" +
                "\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}\n";
//        DefJson defJson = jsonConvert.strToBean(jsonString, DefJson.class);
//        System.out.println(defJson);
//        String json = jsonConvert.objToStr(defJson);
//        System.out.println(json);

        String jsonListStr = "[{\n" +
                "  \"flowCode\": \"leaveFlow-serial1\",\n" +
                "  \"flowName\": \"串行-简单\",\n" +
                "  \"formCustom\": \"N\",\n" +
                "  \"formPath\": \"system/leave/approve\",\n" +
                "  \"version\": \"1\",\n" +
                "  \"nodeList\": [\n" +
                "    {\n" +
                "      \"coordinate\": \"120,280|120,280\",\n" +
                "      \"nodeCode\": \"1\",\n" +
                "      \"nodeName\": \"开始\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 0,\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"140,280;230,280\",\n" +
                "          \"nextNodeCode\": \"2\",\n" +
                "          \"nowNodeCode\": \"1\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"280,280|280,280\",\n" +
                "      \"nodeCode\": \"2\",\n" +
                "      \"nodeName\": \"待提交\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:2\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"330,280;430,280\",\n" +
                "          \"nextNodeCode\": \"3\",\n" +
                "          \"nowNodeCode\": \"2\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"480,280|480,280\",\n" +
                "      \"nodeCode\": \"3\",\n" +
                "      \"nodeName\": \"组长审批\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:2\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"530,280;650,280\",\n" +
                "          \"nextNodeCode\": \"4\",\n" +
                "          \"nowNodeCode\": \"3\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"700,280|700,280\",\n" +
                "      \"nodeCode\": \"4\",\n" +
                "      \"nodeName\": \"部门经理审批\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:2\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"750,280;870,280\",\n" +
                "          \"nextNodeCode\": \"5\",\n" +
                "          \"nowNodeCode\": \"4\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"coordinate\": \"700,240;700,210;280,210;280,240\",\n" +
                "          \"nextNodeCode\": \"2\",\n" +
                "          \"nowNodeCode\": \"4\",\n" +
                "          \"skipType\": \"REJECT\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"920,280|920,280\",\n" +
                "      \"nodeCode\": \"5\",\n" +
                "      \"nodeName\": \"hr审批\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:2\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"970,280;1100,280\",\n" +
                "          \"nextNodeCode\": \"6\",\n" +
                "          \"nowNodeCode\": \"5\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"1120,280|1120,280\",\n" +
                "      \"nodeCode\": \"6\",\n" +
                "      \"nodeName\": \"结束\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 2,\n" +
                "      \"skipList\": [\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n,{\n" +
                "  \"flowCode\": \"leaveFlow-serial2\",\n" +
                "  \"flowName\": \"串行-通过互斥\",\n" +
                "  \"formCustom\": \"N\",\n" +
                "  \"formPath\": \"system/leave/approve\",\n" +
                "  \"version\": \"1\",\n" +
                "  \"nodeList\": [\n" +
                "    {\n" +
                "      \"coordinate\": \"140,300|140,300\",\n" +
                "      \"nodeCode\": \"1\",\n" +
                "      \"nodeName\": \"开始\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 0,\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"160,300;250,300\",\n" +
                "          \"nextNodeCode\": \"2\",\n" +
                "          \"nowNodeCode\": \"1\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"300,300|300,300\",\n" +
                "      \"nodeCode\": \"2\",\n" +
                "      \"nodeName\": \"待提交\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:3\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"350,300;450,300\",\n" +
                "          \"nextNodeCode\": \"3\",\n" +
                "          \"nowNodeCode\": \"2\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"500,300|500,300\",\n" +
                "      \"nodeCode\": \"3\",\n" +
                "      \"nodeName\": \"组长审批\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:3\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"550,300;635,300\",\n" +
                "          \"nextNodeCode\": \"5\",\n" +
                "          \"nowNodeCode\": \"3\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"840,180|840,180\",\n" +
                "      \"nodeCode\": \"4\",\n" +
                "      \"nodeName\": \"大组长审批\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:3\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"890,180;990,180\",\n" +
                "          \"nextNodeCode\": \"6\",\n" +
                "          \"nowNodeCode\": \"4\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"660,300\",\n" +
                "      \"nodeCode\": \"5\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 3,\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"660,275;660,180;790,180|660,225\",\n" +
                "          \"nextNodeCode\": \"4\",\n" +
                "          \"nowNodeCode\": \"5\",\n" +
                "          \"skipCondition\": \"@@le@@|flag@@le@@4\",\n" +
                "          \"skipName\": \"请假时间小于等于4天\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"coordinate\": \"660,325;660,420;790,420|660,375\",\n" +
                "          \"nextNodeCode\": \"7\",\n" +
                "          \"nowNodeCode\": \"5\",\n" +
                "          \"skipCondition\": \"@@spel@@|#{@user.eval(#flag)}\",\n" +
                "          \"skipName\": \"请假时间大于4天\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"1040,180|1040,180\",\n" +
                "      \"nodeCode\": \"6\",\n" +
                "      \"nodeName\": \"部门经理审批\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:3\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"1090,180;1141,180;1141,300;1180,300\",\n" +
                "          \"nextNodeCode\": \"8\",\n" +
                "          \"nowNodeCode\": \"6\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"coordinate\": \"1040,140;1040,120;840,120;840,140\",\n" +
                "          \"nextNodeCode\": \"4\",\n" +
                "          \"nowNodeCode\": \"6\",\n" +
                "          \"skipType\": \"REJECT\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"840,420|840,420\",\n" +
                "      \"nodeCode\": \"7\",\n" +
                "      \"nodeName\": \"董事长审批\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 1,\n" +
                "      \"permissionFlag\": \"role:1@@role:3\",\n" +
                "      \"skipList\": [\n" +
                "        {\n" +
                "          \"coordinate\": \"890,420;910,420;910,300;1180,300\",\n" +
                "          \"nextNodeCode\": \"8\",\n" +
                "          \"nowNodeCode\": \"7\",\n" +
                "          \"skipType\": \"PASS\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"coordinate\": \"1200,300|1200,300\",\n" +
                "      \"nodeCode\": \"8\",\n" +
                "      \"nodeName\": \"结束\",\n" +
                "      \"nodeRatio\": 0.000,\n" +
                "      \"nodeType\": 2,\n" +
                "      \"skipList\": [\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}\n]";
        List<DefJson> defJsons = jsonConvert.strToList(jsonString);
        System.out.println(defJsons);
    }

    /**
     * fastJson json字符串和bean之间转换
     */
    @Test
    public void testFastJson() {
        testBase(new JsonConvertFastJson());
    }

    /**
     * GsonJson json字符串和bean之间转换
     */
    @Test
    public void testGsonJson() {
        testBase(new JsonConvertGson());
    }

    /**
     * jackson json字符串和bean之间转换
     */
    @Test
    public void testJackson() {
        testBase(new JsonConvertJackson());
    }

    /**
     * snack json字符串和bean之间转换
     */
    @Test
    public void testSnack() {
        testBase(new JsonConvertSnack());
    }

}
