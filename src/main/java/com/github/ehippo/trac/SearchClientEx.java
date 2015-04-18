package com.github.ehippo.trac;

import java.util.ArrayList;
import java.util.List;

public class SearchClientEx {

    private final Search searchClient;

    public SearchClientEx(Search searchClient) {
        this.searchClient = searchClient;
    }

    public List<SearchFilter> getSearchFiltersEx() {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        for(Object[] rawFilter : searchClient.getSearchFilters()) {
            filters.add(new SearchFilter(rawFilter));
        }
        return filters;
    }

    public static class SearchFilter {
        private final String name;
        private final String description;

        public SearchFilter(Object[] input) {
            this.name = String.valueOf(input[0]);
            this.description = String.valueOf(input[1]);
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("name: ").append(name)
                    .append(", description: ").append(description).toString();
        }
    }

}
