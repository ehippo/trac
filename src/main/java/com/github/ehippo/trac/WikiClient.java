package com.github.ehippo.trac;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface WikiClient {

    /**
     * Get list of changed pages since timestamp
     *
     * Permission required: By resource
     */
    List<HashMap<String,Object>> getRecentChanges(Date since);

    /**
     * Returns 2 with this version of the Trac API.
     *
     * Permission required: WIKI_VIEW
     */
    Integer getRPCVersionSupported();

    /**
     * Get the raw Wiki text of page, latest version.
     *
     * Permission required: By resource
     */
    String getPage(String pagename);

    /**
     * Get the raw Wiki text of page, latest version.
     *
     * Permission required: By resource
     */
    String getPage(String pagename, Integer version);

    /**
     * Get the raw Wiki text of page, latest version.
     *
     * Permission required: By resource
     */
    String getPageVersion(String pagename);

    /**
     * Get the raw Wiki text of page, latest version.
     *
     * Permission required: By resource
     */
    String getPageVersion(String pagename, Integer version);

    /**
     * Return latest version of page as rendered HTML, utf8 encoded.
     *
     * Permission required: By resource
     */
    String getPageHTML(String pagename);

    /**
     * Return latest version of page as rendered HTML, utf8 encoded.
     *
     * Permission required: By resource
     */
    String getPageHTML(String pagename, Integer version);

    /**
     * Return latest version of page as rendered HTML, utf8 encoded.
     *
     * Permission required: By resource
     */
    String getPageHTMLVersion(String pagename);

    /**
     * Return latest version of page as rendered HTML, utf8 encoded.
     *
     * Permission required: By resource
     */
    String getPageHTMLVersion(String pagename, Integer version);

    /**
     * Returns a list of all pages. The result is an array of utf8 pagenames.
     *
     * Permission required: By resource
     */
    List<String> getAllPages();

    /**
     * Returns information about the given page.
     *
     * Permission required: By resource
     */
    HashMap<String,Object> getPageInfo(String pagename);

    /**
     * Returns information about the given page.
     *
     * Permission required: By resource
     */
    HashMap<String,Object> getPageInfo(String pagename, Integer version);

    /**
     * Returns information about the given page.
     *
     * Permission required: By resource
     */
    HashMap<String,Object> getPageInfoVersion(String pagename);

    /**
     * Returns information about the given page.
     *
     * Permission required: By resource
     */
    HashMap<String,Object> getPageInfoVersion(String pagename, Integer version);

    /**
     * writes the content of the page.
     *
     * Permission required: By resource
     */
    Boolean putPage(String pagename, String content, HashMap<String,String> attributes);

}
