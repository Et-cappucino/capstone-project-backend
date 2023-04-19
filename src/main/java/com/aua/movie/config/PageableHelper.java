package com.aua.movie.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PageableHelper {

    public <T> Page<T> listToPage(List<T> items, int pageNumber, int pageSize) {
        int totalElements = items.size();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        if (pageNumber >= totalPages) {
            return new PageImpl<>(Collections.emptyList(), PageRequest.of(pageNumber, pageSize), totalElements);
        }

        int fromIndex = pageNumber * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalElements);
        return new PageImpl<>(items.subList(fromIndex, toIndex), PageRequest.of(pageNumber, pageSize), totalElements);
    }
}
