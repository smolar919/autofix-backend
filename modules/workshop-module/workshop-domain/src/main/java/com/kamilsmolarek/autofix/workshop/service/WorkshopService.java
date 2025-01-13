package com.kamilsmolarek.autofix.workshop.service;

import com.kamilsmolarek.autofix.commons.search.SearchForm;
import com.kamilsmolarek.autofix.commons.search.SearchResponse;
import com.kamilsmolarek.autofix.workshop.forms.CreateWorkshopForm;
import com.kamilsmolarek.autofix.workshop.forms.EditWorkshopForm;
import com.kamilsmolarek.autofix.workshop.model.Workshop;

public interface WorkshopService {
    Workshop create(CreateWorkshopForm form);
    Workshop edit(String id, EditWorkshopForm form);
    void delete(String id);
    SearchResponse<Workshop> search(SearchForm form);
    Workshop get(String id);
}
