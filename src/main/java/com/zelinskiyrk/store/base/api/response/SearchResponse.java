package com.zelinskiyrk.store.base.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SearchResponse<T> {
    private Long count;
    private List<T> list;

    public static <T> SearchResponse<T> of(List<T> list, Long count){
        return new SearchResponse<>(count, list);
    }
}
