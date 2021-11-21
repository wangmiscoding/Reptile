package com.example.xhs.service.inter;

/**
 * @author wangm
 * @since 2021/3/24
 */
public interface Product<T> {

    T get(Integer id);
}
