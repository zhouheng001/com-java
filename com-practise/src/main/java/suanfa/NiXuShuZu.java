package suanfa;

/**
 * 描述:
 *
 * @author zhouheng
 * @create 2019-08-30 11:19
 */
public class NiXuShuZu {


    public static void main(String[] args) {

        int[][] nums = new int[][]{{1, 2, 3,4}, {5, 6,7,8}, {9,10,11,12}};
        printfNi(nums);

    }

    /*************************************************     非递归方法实现    ****************************************************/

    public static void printfNi(int[][] nums) {

        int rstart = 0,cstart = 0,rend=nums.length-1,cend = nums[0].length-1;

        while (rstart<=rend||cstart<cend){
            print(nums,rstart,cstart,rend,cend);
            rstart++;
            cstart++;
            rend--;
            cend--;
        }

    }

    public static void print(int[][] nums, int rstart, int cstart, int rend, int cend) {

        for (int i = cstart; i < cend; i++) {
            System.out.println(nums[rstart][i]);
        }

        for (int i = rstart; i < rend; i++) {
            System.out.println(nums[i][cend]);
        }

        for (int i = cend; i > cstart ; i--) {
            System.out.println(nums[rend][i]);
        }

        for (int i = rend; i > rstart; i--) {
            System.out.println(nums[i][cstart]);
        }

    }

    /** 方法二 递归方法实现逆序输出二维数据 */

    public void diGui(int [][] nums,int rstart, int cstart, int rend, int cend){

        if(rstart<=rend||cstart<cend){
            print(nums,rstart,cstart,rend,cend);
            diGui(nums,rstart,cstart,rend,cend);
            rstart++;
            cstart++;
            rend--;
            cend--;
        }
    }


}