package com.example.xhs.common;

import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

/**
 * 分页实体类
 *
 * @author vanew
 * @since 2020/1/9
 */
@Getter
@Setter
public class PageRequestDto {

	/**
	 * 分页参数-当前页码(从1开始)
	 */
	private Integer pageNum = 1;

	/**
	 * 分页参数-每页的数量
	 */
	private Integer pageSize = 6;
	/**
	 * 排序字段
	 */
	private String orderBy;



	/**
	 * 获取查询排序字符串
	 *
	 * @return 排序字符串
	 */
	public String getOrderBy() {
		// SQL过滤，防止注入
		String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
				+ "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		if (StringUtils.isEmpty(orderBy)) {
			return orderBy;
		}
		if (sqlPattern.matcher(orderBy).find()) {
			return "";
		}
		return orderBy;
	}

}
