package com.github.ehippo.trac;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TicketClientEx {

    private final TicketClient ticketClient;

    public TicketClientEx(TicketClient ticketClient) {
        this.ticketClient = ticketClient;
    }

    public List<Ticket> query() {
        final List<Ticket> tickets = new ArrayList<Ticket>();
        for(Integer id : ticketClient.query()) {
            tickets.add(get(id));
        }
        return tickets;
    }

    public List<Ticket> query(String qstr) {
        final List<Ticket> tickets = new ArrayList<Ticket>();
        for(Integer id : ticketClient.query(qstr)) {
            tickets.add(get(id));
        }
        return tickets;
    }

    public List<Ticket> getRecentChanges(Date since) {
        final List<Ticket> tickets = new ArrayList<Ticket>();
        for(Integer id : ticketClient.getRecentChanges(since)) {
            tickets.add(get(id));
        }
        return tickets;
    }

    public Ticket get(Integer id) {
        return new Ticket(ticketClient.get(id));
    }

    // Create builder
    //    Integer create(String summary, String description);
    //    Integer create(String summary, String description, HashMap<String, String> attribute);
    //    Integer create(String summary, String description, HashMap<String, String> attribute, Boolean notify);
    //    Integer create(String summary, String description, HashMap<String, String> attribute, Boolean notify, Date when);
    //    create().comment(comment).attr(name, value).attr(name, value).notify().author().create();

    // update(ticket)
    //    Ticket update(Integer id, String comment);
    //    Ticket update(Integer id, String comment, HashMap<String, String> attributes);
    //    Ticket update(Integer id, String comment, HashMap<String, String> attributes, Boolean notify);
    //    Ticket update(Integer id, String comment, HashMap<String, String> attributes, Boolean notify, String author);
    //    Ticket update(Integer id, String comment, HashMap<String, String> attributes, Boolean notify, String author, Date when);
    //    update(id).comment(comment).attr(name, value).attr(name, value).notify().author().update();

    //    Integer delete(Integer id);

    //    List<HashMap<String,String>> getTicketFields();

    public class Ticket {

        private final Integer id;
        private final Date created;
        private final Date changed;
        private final Map<String, String> attributes;

        private List<Action> actions;

        @SuppressWarnings("unchecked")
        public Ticket(List<Object> ticket) {
            this.id = (Integer)ticket.get(0);
            this.created = (Date)ticket.get(1);
            this.changed = (Date)ticket.get(2);
            this.attributes = (Map<String, String>)ticket.get(3);
        }

        public Integer getId() {
            return id;
        }

        public Date getDateCreated() {
            return created;
        }

        public Date getDateChanged() {
            return changed;
        }

        public Map<String,String> getAttributes() {
            return attributes;
        }

        public List<Action> getActions() {
            if(actions == null) {
                actions = new ArrayList<Action>();
                for(Object[] action : ticketClient.getActions(id)) {
                    actions.add(new Action(action));
                }
            }
            return actions;
        }

        //    List<Object[]> changeLog(Integer id);
        //    List<Object> changeLog(Integer id, Integer when);

        //    List<Object[]> listAttachments(Integer ticket);
        //    byte[] getAttachment(Integer ticket, String filename);

        //    String putAttachment(Integer ticket, String filename, String description, byte[] data);
        //    String putAttachment(Integer ticket, String filename, String description, byte[] data, Boolean replace);
        //    Boolean deleteAttachment(Integer ticket, String filename);

    }

    public static class Action {

        private final String action;
        private final String label;
        private final String hints;
        private final List<ActionInputField> inputFields = new ArrayList<ActionInputField>();

        public Action(Object[] action) {
            this.action = (String)action[0];
            this.label = (String)action[1];
            this.hints = (String)action[2];
            Object[] rawInputFields = (Object[])action[3];
            if(rawInputFields != null && rawInputFields.length > 0) {
                for(Object rawInputField : rawInputFields) {
                    inputFields.add(new ActionInputField((Object[])rawInputField));
                }
            }
        }

        public String getAction() {
            return action;
        }

        public String getLabel() {
            return label;
        }

        public String getHints() {
            return hints;
        }

        public List<ActionInputField> getInputFields() {
            return inputFields;
        }

    }

    public static class ActionInputField {

        private final String name;
        private final String value;
        private final List<String> options = new ArrayList<String>();

        public ActionInputField(Object[] rawInputField) {
            this.name = (String)rawInputField[0];
            this.value = (String)rawInputField[1];
            Object[] rawOptions = (Object[])rawInputField[2];
            if(rawOptions!=null && rawOptions.length>0) {
                for(Object rawOption : rawOptions) {
                    options.add((String)rawOption);
                }
            }
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }

        public List<String> getOptions() {
            return options;
        }

    }

    public static class LogEntry {

    }

}
