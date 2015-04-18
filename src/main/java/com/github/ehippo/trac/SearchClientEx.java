package com.github.ehippo.trac;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class SearchClientEx {

    private final Search searchClient;

    public SearchClientEx(Search searchClient) {
        this.searchClient = searchClient;
    }

    public List<SearchFilter> getSearchFiltersEx() {
        final List<SearchFilter> filters = new ArrayList<SearchFilter>();
        for(Object[] rawFilter : searchClient.getSearchFilters()) {
            filters.add(new SearchFilter(rawFilter));
        }
        return filters;
    }

    public List<SearchResult> performSearch(String query) {
        final List<SearchResult> filters = new ArrayList<SearchResult>();
        for(Object[] rawFilter : searchClient.performSearch(query)) {
            filters.add(new SearchResult(rawFilter));
        }
        return filters;
    }

    public List<SearchResult> performSearch(String query, List<String> filters) {
        final List<SearchResult> searchResults = new ArrayList<SearchResult>();
        for(Object[] rawSearchResult : searchClient.performSearch(query, filters)) {
            searchResults.add(new SearchResult(rawSearchResult));
        }
        return searchResults;
    }

    public static class SearchFilter {
        private final String name;
        private final String description;

        public SearchFilter(Object[] input) {
            this.name = (String)input[0];
            this.description = (String)input[1];
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

    public static class SearchResult {

        private final String href;
        private final String title;
        private final Date date;
        private final String author;
        private final String excerpt;

        public SearchResult(Object[] input) {
            this.href = (String)input[0];
            this.title = (String)input[1];
            this.date = (Date)input[2];
            this.author = (String)input[3];
            this.excerpt = (String)input[4];
        }

        public String getHref() {
            return href;
        }

        public String getTitle() {
            return title;
        }

        public Date getDate() {
            return date;
        }

        public String getAuthor() {
            return author;
        }

        public String getExcerpt() {
            return excerpt;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("href: ").append(href)
                    .append(", title: ").append(title)
                    .append(", date: ").append(date)
                    .append(", author: ").append(author)
                    .append(", excerpt: ").append(excerpt).toString();
        }

    }

}
