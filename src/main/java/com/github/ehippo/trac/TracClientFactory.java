package com.github.ehippo.trac;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.util.ClientFactory;

public class TracClientFactory {

    private final ClientFactory clientFactory;

    public TracClientFactory(XmlRpcClient client) {
        clientFactory = new ClientFactory(client);
    }

    public TracClientFactory(String tracUser, String tracPassword, String tracUrl) throws MalformedURLException {
        final XmlRpcClientConfigImpl conf = new XmlRpcClientConfigImpl();
        conf.setBasicUserName(tracUser);
        conf.setBasicPassword(tracPassword);
        conf.setServerURL(new URL(tracUrl));

        final XmlRpcClient client = new XmlRpcClient();
        client.setConfig(conf);

        clientFactory = new ClientFactory(client);
    }

    public SearchClient getSearchClient() {
        return (SearchClient)clientFactory.newInstance(Thread.currentThread().getContextClassLoader(), SearchClient.class, "search");
    }

    public SystemClient getSystemClient() {
        return (SystemClient)clientFactory.newInstance(Thread.currentThread().getContextClassLoader(), SystemClient.class, "system");
    }

    public TicketClient getTicketClient() {
        return (TicketClient)clientFactory.newInstance(Thread.currentThread().getContextClassLoader(), TicketClient.class, "ticket");
    }

    public WikiClient getWikiClient() {
        return (WikiClient)clientFactory.newInstance(Thread.currentThread().getContextClassLoader(), WikiClient.class, "wiki");
    }

}
