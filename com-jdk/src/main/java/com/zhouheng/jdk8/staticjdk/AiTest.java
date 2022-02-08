package com.zhouheng.jdk8.staticjdk;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * 描述:
 *
 * @author zhouheng
 * @create 2021-07-09 2:07 下午
 */
public class AiTest {

    public static void main(String[] args) {

//        String sql = "delete  from  cp_manufacture_info  where  id  = %s;";
//        String ids = "114,95,191,100,261,113,127,89,93,209,179,169,265,154,194,102,240,122,242,145,243,210,131,263,146,156,86,186,104,161,181,167," +
//                "219,190,103,178,123,244,107,84,78,97,91,193,228,241,211,126,162,168,82,254,116,117,98,134,196,94,121,109,153,203,142,237,139,130,235,81,246,238,239,128,199";
//        String[] split = ids.split(",");
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s : split) {
//            stringBuilder.append(String.format(sql,s)+"\n");
//        }
//        System.out.println(stringBuilder);

        Double a = 2.3D;
        Double b = 1.121D;
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        System.out.println(a1.remainder(b1).setScale(2, RoundingMode.HALF_UP));


    }

}
