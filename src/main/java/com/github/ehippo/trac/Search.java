package com.github.ehippo.trac;

import java.util.List;

public interface Search {

    /** Retrieve a list of search filters with each element in the form (name, description). 
     * 
     * Permission required: SEARCH_VIEW 
     */
    List<Object[]> getSearchFilters();

    /** 
     * Perform a search using the given filters. 
     * Defaults to all if not provided. 
     * Results are returned as a list of tuples in the form (href, title, date, author, excerpt). 
     * 
     * Permission required: SEARCH_VIEW 
     */
    List<Object[]> performSearch(String query);

    /** 
     * Perform a search using the given filters. 
     * Defaults to all if not provided. 
     * Results are returned as a list of tuples in the form (href, title, date, author, excerpt). 
     * 
     * Permission required: SEARCH_VIEW 
     */
    List<Object[]> performSearch(String query, List<String> filters);

}

