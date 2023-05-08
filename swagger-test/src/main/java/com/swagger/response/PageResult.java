package com.swagger.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <T>
 *            Construct return entity
 * @author liudz
 * @since Oracle JDK1.8
 **/
@Data
public class PageResult<T> implements Serializable {
    private int pageNum;
    private int pageSize;
    private long totalCount;
    private long totalPage;
    private List<T> list;
}