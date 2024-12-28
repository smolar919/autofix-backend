package com.kamilsmolarek.autofix.workshop.repository;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.model.Workshop;

import java.util.Optional;

public interface WorkshopRepository {
    Workshop save(Workshop workshop);
    Optional<Workshop> findById(String id);
    void deleteById(String id);
    SearchResponse<Workshop> search(SearchForm form);
}
