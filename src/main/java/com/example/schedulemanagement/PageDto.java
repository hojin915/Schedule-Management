package com.example.schedulemanagement;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

// 페이지로 감싸기
@Getter
public class PageDto<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    // pageImpl 로 생성한 Page 래핑
    public PageDto(Page<T> pageData) {
        this.content = pageData.getContent();
        this.page = pageData.getNumber();
        this.size = pageData.getSize();
        this.totalElements = pageData.getTotalElements();
        this.totalPages = pageData.getTotalPages();
        this.last = pageData.isLast();
    }
}