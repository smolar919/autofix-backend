package com.kamilsmolarek.autofix.commons.search;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResponse<T> {
    private List<T> items;
    private Long total;
}
