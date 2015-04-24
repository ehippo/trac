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

    /**
     * Lists attachments on a given page.
     *
     * Permission required: By resource
     */
    List<String> listAttachments(String pagename);

    /**
     * returns the content of an attachment.
     *
     * Permission required: By resource
     */
    byte[] getAttachment(String path);

    /**
     * (over)writes an attachment. Returns True if successful. This method is
     * compatible with WikiRPC. putAttachmentEx has a more extensive set of
     * (Trac-specific) features.
     * 
     * Permission required: By resource
     */
    Boolean putAttachment(String path, byte[] data);

    /**
     * Attach a file to a Wiki page. Returns the (possibly transformed) filename
     * of the attachment. Use this method if you don't care about WikiRPC
     * compatibility.
     * 
     * Permission required: By resource
     */
    Boolean putAttachmentEx(String pagename, String filename, String description, byte[] data);

    /**
     * Attach a file to a Wiki page. Returns the (possibly transformed) filename
     * of the attachment. Use this method if you don't care about WikiRPC
     * compatibility.
     * 
     * Permission required: By resource
     */
    Boolean putAttachmentEx(String pagename, String filename, String description, byte[] data, Boolean replace);

    /**
     * Delete a Wiki page (all versions) or a specific version by including an
     * optional version number. Attachments will also be deleted if page no
     * longer exists. Returns True for success.
     * 
     * Permission required: By resource
     */
    Boolean deletePage(String name);

    /**
     * Delete a Wiki page (all versions) or a specific version by including an
     * optional version number. Attachments will also be deleted if page no
     * longer exists. Returns True for success.
     * 
     * Permission required: By resource
     */
    Boolean deletePage(String name, Integer version);

    /**
     * Delete an attachment.
     * 
     * Permission required: By resource
     */
    Boolean deleteAttachment(String path);

    /**
     * Not implemented
     * 
     * Permission required: WIKI_VIEW
     */
    List<String> listLinks(String pagename);

    /**
     * Render arbitrary Wiki text as HTML.
     * 
     * Permission required: WIKI_VIEW
     */
    String wikiToHtml(String text);

}
