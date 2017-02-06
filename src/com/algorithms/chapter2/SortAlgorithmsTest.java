package com.algorithms.chapter2;
/**
 * 用于对排序算法测试的文件
 * 自己写的一个测试文件
 * @author caofengbin
 *
 */
public class SortAlgorithmsTest {

	static Character[] randomArrays = {'S','O','R','T','E','X','A','M','P','L','E'};
	static Character[] orderArrays = {'A','B','C','D','E','F','G','H','I','J','K'};
	static Character[] reverseArrays = {'Z','Y','I','H','G','F','E','D','C','B','A'};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Character[] arrays = randomArrays;
		BaseSort sortUtil = new ShellSort();
		sortUtil.reset();
		sortUtil.show(arrays);
		sortUtil.sort(arrays);
		sortUtil.show(arrays);
		System.out.println("进行比较的次数为:" + sortUtil.getCompareCount());
		System.out.println("进行交换的次数为:" + sortUtil.getExchageCount());
	}

}
