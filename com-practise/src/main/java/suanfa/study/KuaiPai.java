package suanfa.study;

/**
 * 描述:
 *
 * @author zhouheng
 * @create 2019-02-18 下午 10:11
 */
public class KuaiPai {

    public static void main(String[] args) {
        int[] num = {5, 6, 4, 3, 1, 2, 10, 9, 8};
//        kuaiPai(num, 0, num.length - 1);
        insertPai(num);
//        for (int i : num) {
//            System.out.print(i + " ");
//        }

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

            num[i]=midNum;
            kuaiPai(num,i+1,end);
            kuaiPai(num,start,i-1);
        }
    }

    //插入排序
    static void insertPai(int[] num){
        for (int i=1;i<num.length;i++){
             int insertNum = num[i];
             int j = i-1;
             while (j>=0 && num[j] < insertNum){
                 num[j+1] = num[j];
                 j--;
             }
            num[j + 1] = insertNum;
        }
    }

    //简单选择排序
    static void simplePai(int[] num){
        int length = num.length;
        for (int i=0;i<length;i++){
           int value = num[i];
           int position = i;
           for(int j=i+1;j<length;j++){
             if(num[j]<value){
                 value = num[j];
                 position = j;
             }
           }
           num[position] = num[i];
           num[i] = value;
       }
    }
}
