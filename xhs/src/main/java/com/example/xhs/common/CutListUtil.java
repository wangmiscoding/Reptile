package com.example.xhs.common;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author wangm
 * @since 2021/4/10
 */
public class CutListUtil {

    public static final int size = 200;

    /**
     * 将数据切片并允许
     * @param consumer 方法
     * @param list 数据
     * @param <T> 类型
     */
    public static <T> void cutSlices(Consumer<List<T>> consumer, List<T> list) {
        if (list.size() <= size) {
            consumer.accept(list);
        } else {
            consumer.accept(list.subList(0, size));
            list = list.subList(size, list.size());
            cutSlices(consumer,list);
        }
    }

}
