package com.github.lxgang.utils.similarity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class Algorithm {
	private static Logger logger = LoggerFactory.getLogger(Algorithm.class);
	
	/**
	 * 编辑距离法
	 * 保持小数点后6位，最后一位四舍五入
	 * @param source 待检测字符串
	 * @param target 已有字符串
	 * @return
	 */
	public static BigDecimal levenshtein(String source, String target) {
		return levenshtein(source, target, 6, RoundingMode.HALF_UP);
	}
	
	
	/**
	 * 余弦法
	 * 保持小数点后6位，最后一位四舍五入
	 * @param source 待检测字符串
	 * @param target 已有字符串
	 * @return
	 */
	public static BigDecimal cosine(String source, String target){
		return cosine(source, target, 6, RoundingMode.HALF_UP);
	}
	
	
	
	/**
	 * 编辑距离法 相似度计算
	 * @param source 待检测字符串
	 * @param target 已有字符串
	 * @param scale 浮点数小数位
	 * @param roundingMode 进位方式
	 * @return
	 */
	public static BigDecimal levenshtein(String source, String target, int scale, RoundingMode roundingMode) {
		int sourceLength = source.length();
		int targetLength = target.length();

		int[][] diff = new int[sourceLength + 1][targetLength + 1];

		for (int i = 0; i <= sourceLength; i++) {
			diff[i][0] = i;
		}
		for (int i = 0; i <= targetLength; i++) {
			diff[0][i] = i;
		}

		int temp;
		for (int i = 1; i <= sourceLength; i++) {
			for (int j = 1; j <= targetLength; j++) {
				if (source.charAt(i - 1) == target.charAt(j - 1)) {
					temp = 0;
				} else {
					temp = 1;
				}

				diff[i][j] = Math.min(Math.min(diff[i - 1][j - 1] + temp, diff[i][j - 1] + 1), diff[i - 1][j] + 1);
			}
		}
		
		BigDecimal similarity = BigDecimal.ONE.subtract(new BigDecimal(diff[sourceLength][targetLength]).divide(new BigDecimal(Math.max(sourceLength, targetLength)), scale, roundingMode));
		return similarity;
	}
	
	/**
	 * 余弦法 相似度计算
	 * @param source 待检测字符串
	 * @param target 已有字符串
	 * @param scale 浮点数小数位
	 * @param roundingMode 进位方式
	 * @return	余弦值
	 */
	public static BigDecimal cosine(String source, String target, int scale, RoundingMode roundingMode){
		//创建向量空间模型，使用map实现，主键为词项，值为长度为2的数组，存放着对应词项在字符串中的出现次数
		Map<String, int[]> vectorSpace = new HashMap<String, int[]>();
		//为了避免频繁产生局部变量，所以将itemCountArray声明在此
		int[] itemCountArray = null;
		
		String[] sourceArray = source.split(" ");
		for(int i = 0; i < sourceArray.length; i++) {
			if(vectorSpace.containsKey(sourceArray[i])){
				(vectorSpace.get(sourceArray[i])[0])++;
			} else {
				itemCountArray = new int[2];
				itemCountArray[0] = 1;
				itemCountArray[1] = 0;
				vectorSpace.put(sourceArray[i], itemCountArray);
			}
		}

		String[] targetArray = target.split(" ");
		for(int i = 0; i < targetArray.length; i++) {
			if(vectorSpace.containsKey(targetArray[i])){
				(vectorSpace.get(targetArray[i])[1])++;
			} else {  
				itemCountArray = new int[2];
				itemCountArray[0] = 0;
				itemCountArray[1] = 1;
				vectorSpace.put(targetArray[i], itemCountArray);
			}
		}
		
		//计算相似度
		BigDecimal sourceModulo = new BigDecimal("0");		//源向量的模
		BigDecimal targetModulo = new BigDecimal("0");		//目标向量的模
		BigDecimal vectorProduct = new BigDecimal("0");		//向量内积
		Iterator<String> iterator = vectorSpace.keySet().iterator();
	
		while(iterator.hasNext()) {  
			itemCountArray = vectorSpace.get(iterator.next());
			
			BigDecimal aa = new BigDecimal(itemCountArray[0]);
			BigDecimal bb = new BigDecimal(itemCountArray[1]);
	
			sourceModulo = sourceModulo.add(aa.multiply(aa));
			targetModulo = targetModulo.add(bb.multiply(bb));
			vectorProduct = vectorProduct.add(aa.multiply(bb));
			
		}
	
		sourceModulo = new BigDecimal(Math.sqrt(sourceModulo.doubleValue())).setScale(scale, roundingMode);
		targetModulo = new BigDecimal(Math.sqrt(targetModulo.doubleValue())).setScale(scale, roundingMode);
	
		//返回相似度
		return vectorProduct.divide(sourceModulo.multiply(targetModulo), scale, roundingMode);
	}
}

