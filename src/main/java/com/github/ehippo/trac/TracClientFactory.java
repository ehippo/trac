package com.github.ehippo.trac;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.util.ClientFactory;

public class TracClientFactory {

    private final ClientFactory clientFactory;

    public TracClientFactory(String tracUser, String tracPassword, String tracUrl) throws MalformedURLException {
        final XmlRpcClientConfigImpl conf = new XmlRpcClientConfigImpl();
        conf.setBasicUserName(tracUser);
        conf.setBasicPassword(tracPassword);
        conf.setServerURL(new URL(tracUrl));

        final XmlRpcClient client = new XmlRpcClient();
        client.setConfig(conf);

        clientFactory = new ClientFactory(client);
    }

    public SystemClient getSystem() {
        return (SystemClient)clientFactory.newInstance(Thread.currentThread().getContextClassLoader(), SystemClient.class, "system");
    }

    public Search getSearch() {
        return (Search)clientFactory.newInstance(Thread.currentThread().getContextClassLoader(), Search.class, "search");
    }

    public Ticket getTicket() {
        return (Ticket)clientFactory.newInstance(Thread.currentThread().getContextClassLoader(), Ticket.class, "ticket");
    }

    public Wiki getWiki() {
        return (Wiki)clientFactory.newInstance(Thread.currentThread().getContextClassLoader(), Wiki.class, "wiki");
    }

}
