package com.kamilsmolarek.autofix.commons.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchSort {
    private String by;
    private SearchSortOrder order;
}
