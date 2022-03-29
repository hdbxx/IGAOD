package com.edu.iga.Utils;

import com.edu.iga.genetics.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class NumberUtils {

    /**
     * [start-end]
     * @param start
     * @param end
     * @return
     */
    public static int getRandom(int start, int end){
        return (int)(Math.random() * (end-start+1) + start);
    }

    /**
     * 十进制数转为2进制数
     * @param num 十进制数
     * @param size 返回的位数 （可以根据自己需求设置）
     * @return
     */
    public static String decimal2Binary(int num, int size) {
        if (size <(Integer.SIZE - Integer.numberOfLeadingZeros(num))) {
            throw  new RuntimeException("传入size小于num二进制位数");
        }
        StringBuilder binStr = new StringBuilder();
        for(int i = size-1;i >= 0; i--){
            binStr.append(num >>> i & 1);
        }
        return binStr.toString();
    }


    public static List<Integer> sample(List<Integer> list, int len){
        List<Integer> newList = new ArrayList<>();
        for(int i=0; i<len; i++){
            int d = GeneticAlgorithm.randomGenerator.nextInt(list.size());
            newList.add(list.get(d));
            list.remove(d);
        }
        return newList;
    }

}
