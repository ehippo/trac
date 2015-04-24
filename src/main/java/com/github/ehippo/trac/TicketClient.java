package com.github.ehippo.trac;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface TicketClient {

    /**
     * Perform a ticket query, returning a list of ticket ID's. All queries will
     * use stored settings for maximum number of results per page and paging
     * options. Use max=n to define number of results to receive, and use page=n
     * to page through larger result sets. Using max=0 will turn off paging and
     * return all results.
     * 
     * Default: qstr="status!=closed"
     * 
     * Permission required: By resource
     */
    List<Integer> query();

    /**
     * Perform a ticket query, returning a list of ticket ID's. All queries will
     * use stored settings for maximum number of results per page and paging
     * options. Use max=n to define number of results to receive, and use page=n
     * to page through larger result sets. Using max=0 will turn off paging and
     * return all results.
     * 
     * Permission required: By resource
     */
    List<Integer> query(String qstr);

    /**
     * Returns a list of IDs of tickets that have changed since timestamp.
     * 
     * Permission required: By resource
     */
    List<Integer> getRecentChanges(Date since);

    /**
     * Deprecated - will be removed. Replaced by getActions().
     * 
     * Permission required: By resource
     */
    List<String> getAvailableActions(Integer id);

    /**
     * Returns the actions that can be performed on the ticket as a list of
     * [action, label, hints, [input_fields]] elements, where input_fields is a
     * list of [name, value, [options]] for any required action inputs.
     * 
     * Permission required: By resource
     */
    List<Object[]> getActions(Integer id);

    /**
     * Fetch a ticket. Returns [id, time_created, time_changed, attributes].
     * 
     * Permission required: By resource
     */
    List<Object> get(Integer id);

    /**
     * Create a new ticket, returning the ticket ID. Overriding 'when' requires
     * admin permission.
     * 
     * Permission required: TICKET_CREATE
     */
    Integer create(String summary, String description);

    /**
     * Create a new ticket, returning the ticket ID. Overriding 'when' requires
     * admin permission.
     * 
     * Permission required: TICKET_CREATE
     */
    Integer create(String summary, String description, HashMap<String, String> attribute);

    /**
     * Create a new ticket, returning the ticket ID. Overriding 'when' requires
     * admin permission.
     * 
     * Permission required: TICKET_CREATE
     */
    Integer create(String summary, String description, HashMap<String, String> attribute, Boolean notify);

    /**
     * Create a new ticket, returning the ticket ID. Overriding 'when' requires
     * admin permission.
     * 
     * Permission required: TICKET_CREATE, ADMIN
     */
    Integer create(String summary, String description, HashMap<String, String> attribute, Boolean notify, Date when);

    /**
     * Update a ticket, returning the new ticket in the same form as get().
     * 'New-style' call requires two additional items in attributes: (1)
     * 'action' for workflow support (including any supporting fields as
     * retrieved by getActions()), (2) '_ts' changetime token for detecting
     * update collisions (as received from get() or update() calls). Calling
     * update without 'action' and '_ts' changetime token is deprecated, and
     * will raise errors in a future version.
     * 
     * Permission required: By resource
     */
    List<Object> update(Integer id, String comment);

    /**
     * Update a ticket, returning the new ticket in the same form as get().
     * 'New-style' call requires two additional items in attributes: (1)
     * 'action' for workflow support (including any supporting fields as
     * retrieved by getActions()), (2) '_ts' changetime token for detecting
     * update collisions (as received from get() or update() calls). Calling
     * update without 'action' and '_ts' changetime token is deprecated, and
     * will raise errors in a future version.
     * 
     * Permission required: By resource
     */
    List<Object> update(Integer id, String comment, HashMap<String, String> attributes);

    /**
     * Update a ticket, returning the new ticket in the same form as get().
     * 'New-style' call requires two additional items in attributes: (1)
     * 'action' for workflow support (including any supporting fields as
     * retrieved by getActions()), (2) '_ts' changetime token for detecting
     * update collisions (as received from get() or update() calls). Calling
     * update without 'action' and '_ts' changetime token is deprecated, and
     * will raise errors in a future version.
     * 
     * Permission required: By resource
     */
    List<Object> update(Integer id, String comment, HashMap<String, String> attributes, Boolean notify);

    /**
     * Update a ticket, returning the new ticket in the same form as get().
     * 'New-style' call requires two additional items in attributes: (1)
     * 'action' for workflow support (including any supporting fields as
     * retrieved by getActions()), (2) '_ts' changetime token for detecting
     * update collisions (as received from get() or update() calls). Calling
     * update without 'action' and '_ts' changetime token is deprecated, and
     * will raise errors in a future version.
     * 
     * Permission required: By resource
     */
    List<Object> update(Integer id, String comment, HashMap<String, String> attributes, Boolean notify, String author);

    /**
     * Update a ticket, returning the new ticket in the same form as get().
     * 'New-style' call requires two additional items in attributes: (1)
     * 'action' for workflow support (including any supporting fields as
     * retrieved by getActions()), (2) '_ts' changetime token for detecting
     * update collisions (as received from get() or update() calls). Calling
     * update without 'action' and '_ts' changetime token is deprecated, and
     * will raise errors in a future version.
     * 
     * Permission required: By resource
     */
    List<Object> update(Integer id, String comment, HashMap<String, String> attributes, Boolean notify, String author, Date when);

    /**
     * Delete ticket with the given id.
     *
     * Permission required: By resource
     */
    Integer delete(Integer id);

    /**
     * Return the changelog as a list of tuples of the form (time, author,
     * field, oldvalue, newvalue, permanent). While the other tuple elements are
     * quite self-explanatory, the permanent flag is used to distinguish
     * collateral changes that are not yet immutable (like attachments,
     * currently). :since 1.0: the db parameter is no longer needed and will be
     * removed in version 1.1.1
     * 
     * Permission required: By resource
     */
    List<Object[]> changeLog(Integer id);

    /**
     * Return the changelog as a list of tuples of the form (time, author,
     * field, oldvalue, newvalue, permanent). While the other tuple elements are
     * quite self-explanatory, the permanent flag is used to distinguish
     * collateral changes that are not yet immutable (like attachments,
     * currently). :since 1.0: the db parameter is no longer needed and will be
     * removed in version 1.1.1
     * 
     * Permission required: By resource
     */
    List<Object> changeLog(Integer id, Integer when);

    /**
     * Lists attachments for a given ticket. Returns (filename, description,
     * size, time, author) for each attachment.
     * 
     * Permission required: By resource
     */
    List<Object[]> listAttachments(Integer ticket);

    /**
     * returns the content of an attachment.
     * 
     * Permission required: By resource
     */
    byte[] getAttachment(Integer ticket, String filename);

    /**
     * Add an attachment, optionally (and defaulting to) overwriting an existing
     * one. Returns filename.
     * 
     * Permission required: By resource
     */
    String putAttachment(Integer ticket, String filename, String description, byte[] data);

    /**
     * Add an attachment, optionally (and defaulting to) overwriting an existing
     * one. Returns filename.
     * 
     * Permission required: By resource
     */
    String putAttachment(Integer ticket, String filename, String description, byte[] data, Boolean replace);

    /**
     * Delete an attachment.
     *
     * Permission required: By resource
     */
    Boolean deleteAttachment(Integer ticket, String filename);

    /**
     * Delete an attachment.
     *
     * Permission required: TICKET_VIEW
     */
    List<HashMap<String,String>> getTicketFields();

}