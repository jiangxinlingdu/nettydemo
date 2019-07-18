package com.lingdu.idea;

/**
 * @author 匠心零度
 */
public class Test7 {
    public static void main(String[] args) {
        //便捷的处理json字符串
        /**
         {
        "employees": [
            {
                "firstName": "Bill",
                "lastName": "Gates"
            },
            {
                "firstName": "Thomas",
                "lastName": "Carter"
            }
        ]
        }

         {"employees":[{"firstName":"Bill","lastName":"Gates"},{"firstName":"Thomas","lastName":"Carter"}]}
         */
        
        String json = "{\"employees\":[{\"firstName\":\"Bill\",\"lastName\":\"Gates\"},{\"firstName\":\"Thomas\",\"lastName\":\"Carter\"}]}";
    }
}
