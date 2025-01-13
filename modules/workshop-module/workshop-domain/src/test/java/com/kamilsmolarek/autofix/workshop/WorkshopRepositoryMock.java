package com.kamilsmolarek.autofix.workshop;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.model.Workshop;
import com.kamilsmolarek.autofix.workshop.repository.WorkshopRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WorkshopRepositoryMock implements WorkshopRepository {
    private final Map<String, Workshop> workshopsById = new HashMap<>();

    @Override
    public Workshop save(Workshop workshop) {
        workshopsById.put(workshop.getId(), workshop);
        return workshop;
    }

    @Override
    public Optional<Workshop> findById(String id) {
        return Optional.ofNullable(workshopsById.get(id));
    }

    @Override
    public void deleteById(String id) {
        workshopsById.remove(id);
    }

    @Override
    public SearchResponse<Workshop> search(SearchForm form) {
        return new SearchResponse<>(new ArrayList<>(workshopsById.values()), (long) workshopsById.size());
    }
}


