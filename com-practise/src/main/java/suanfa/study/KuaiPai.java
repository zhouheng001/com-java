package suanfa.study;

import com.sun.xml.internal.fastinfoset.util.CharArray;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Queue;

import javax.management.QueryEval;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 描述:
 *
 * @author zhouheng
 * @create 2019-02-18 下午 10:11
 */
public class KuaiPai {

    private static final char add = '+';

    private static final char reduce = '-';

    private static final char multi = '*';

    private static final char divede = '/';

    public static void main(String[] args) {
        //        int[] num = {5, 6, 4, 3, 1, 2, 10, 9, 8};
        //        kuaiPai(num, 0, num.length - 1);
        //        insertPai(num);
        //        for (int i : num) {
        //            System.out.print(i + " ");
        //        }
        String s = "6 + 10 * 12 + 6 / 2 + 11";

        List<Integer> numList = new ArrayList<>();
        List<Character> symbolList = new ArrayList<>();

        strToNumList(s, numList, symbolList);

        int e = 0;
        int size = symbolList.size();

        // 先做乘除
        while (true) {

            if (e == size) {
                break;
            }

            if (symbolList.get(e).equals(multi)) {

                Integer g = numList.get(e);
                Integer h = numList.get(e + 1);

                symbolList.remove(e);
                numList.remove(e);
                numList.remove(e);

                numList.add(e, g * h);
                size--;
                continue;
            }

            if (symbolList.get(e).equals(divede)) {

                Integer g = numList.get(e);
                Integer h = numList.get(e + 1);

                symbolList.remove(e);
                numList.remove(e);
                numList.remove(e);

                numList.add(e, g / h);
                size--;
                continue;
            }

            e++;

        }

        int t = 0;
        int ySize = symbolList.size();

        // 在做加减
        while (true) {

            if (t == ySize) {
                break;
            }

            if (symbolList.get(t).equals(add)) {

                Integer g = numList.get(t);
                Integer h = numList.get(t + 1);

                symbolList.remove(t);
                numList.remove(t);
                numList.remove(t);

                numList.add(t, g + h);
                ySize--;
                continue;
            }

            if (symbolList.get(t).equals(reduce)) {

                Integer g = numList.get(t);
                Integer h = numList.get(t + 1);

                symbolList.remove(t);
                numList.remove(t);
                numList.remove(t);

                numList.add(t, g - h);

                ySize--;
                continue;
            }

            t++;

        }

        System.out.println(numList);

    }

    /**
     * 将字符串转变为数组
     *
     * @param str        字符串
     * @param numList    数字数组
     * @param symbolList 符号数组
     */
    private static void strToNumList(String str, List<Integer> numList, List<Character> symbolList) {
        StringBuilder stringBuilder = new StringBuilder();

        String trim = str.replaceAll(" ", "");
        char[] chars = trim.toCharArray();

        int a, b, c, d;

        int i = 0;
        for (char aChar : chars) {

            i++;

            if (i == chars.length) {
                stringBuilder.append(aChar);
                numList.add(Integer.valueOf(stringBuilder.toString()));
                continue;
            }

            if (aChar == add) {
                a = Integer.valueOf(stringBuilder.toString());
                numList.add(a);
                stringBuilder = new StringBuilder();
                symbolList.add(aChar);
                continue;
            }

            if (aChar == reduce) {
                b = Integer.valueOf(stringBuilder.toString());
                numList.add(b);
                stringBuilder = new StringBuilder();
                symbolList.add(aChar);
                continue;
            }

            if (aChar == multi) {
                c = Integer.valueOf(stringBuilder.toString());
                numList.add(c);
                stringBuilder = new StringBuilder();
                symbolList.add(aChar);
                continue;
            }

            if (aChar == divede) {
                d = Integer.valueOf(stringBuilder.toString());
                numList.add(d);
                stringBuilder = new StringBuilder();
                symbolList.add(aChar);
                continue;
            }

            stringBuilder.append(aChar);
        }
    }


    //快速排序
    static void kuaiPai(int[] num, int start, int end) {
        if (start < end) {
            int i = start;
            int j = end;

            int midNum = num[i]; //标杆位置

            while (i < j) {
                while (i < j) {
                    if (midNum > num[j]) {
                        num[i] = num[j];
                        break;
                    }
                    j--;
                }
                while (i < j) {
                    if (midNum <= num[i]) {
                        num[j] = num[i];
                        break;
                    }
                    i++;
                }
            }

            num[i] = midNum;
            kuaiPai(num, i + 1, end);
            kuaiPai(num, start, i - 1);
        }
    }

    //插入排序
    static void insertPai(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int insertNum = num[i];
            int j = i - 1;
            while (j >= 0 && num[j] < insertNum) {
                num[j + 1] = num[j];
                j--;
            }
            num[j + 1] = insertNum;
        }
    }

    //简单选择排序
    static void simplePai(int[] num) {
        int length = num.length;
        for (int i = 0; i < length; i++) {
            int value = num[i];
            int position = i;
            for (int j = i + 1; j < length; j++) {
                if (num[j] < value) {
                    value = num[j];
                    position = j;
                }
            }
            num[position] = num[i];
            num[i] = value;
        }
    }
}
