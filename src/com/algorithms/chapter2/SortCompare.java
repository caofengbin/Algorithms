package com.algorithms.chapter2;

import java.util.Random;

/**
 * 书P160页给的一个专门用来测试算法效率的工具类
 * @author caofengbin
 *
 */
public class SortCompare {

	private static Random randomUtil = new Random(System.currentTimeMillis());
	
	public static long caculateTime(BaseSort sortUtil,Comparable[] arrays) {
		long beginTime = System.currentTimeMillis();
		sortUtil.reset();
		sortUtil.sort(arrays);
		long endTime = System.currentTimeMillis();
		return endTime - beginTime ;
	}
	
	/**
	 * 
	 * @param sortUtil  选择的排序算法
	 * @param N			指定的数组长度
	 * @param T			指定待排序的数组个数
	 * @return
	 */
	public static long timeRandomInput(BaseSort sortUtil,int N,int T) {
		long total = 0;
		Double[] a = new Double[N];
		for(int t = 0 ; t < T ; t++) {
			// 进行一次测试，生成一个数组并排序
			for(int i = 0 ; i < N ; i++)
				a[i] = randomUtil.nextDouble();
			total += caculateTime(sortUtil,a);
		}
		return total;
	}
	
	public static void main(String[] args) {
		int arrayLenth = 1000;
		int arrayNumber = 100;
		long t1 = timeRandomInput(new SelectionSort(),arrayLenth,arrayNumber);
		long t2 = timeRandomInput(new InsertionSort(),arrayLenth,arrayNumber);
		System.out.println("对于长度为：" + arrayLenth + "的" + arrayNumber + "个数组");
		System.out.println("用SelectionSort排序用时:"+t1+"毫秒");
		System.out.println("用InsertionSort排序用时:"+t2+"毫秒");
	}

}
