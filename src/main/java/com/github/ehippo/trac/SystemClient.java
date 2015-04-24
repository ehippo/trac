package com.github.ehippo.trac;

import java.util.List;
import java.util.Map;

public interface SystemClient {

    /**
     * Takes an array of RPC calls encoded as structs of the form 
     * (in a Pythonish notation here): {'methodName': string, 'params': array}. 
     * For JSON-RPC multicall, signatures is an array of regular method call structs, 
     * and result is an array of return structures.
     * 
     * Permission required: XML_RPC 
     * 
     * <pre>
     * Example:
     * 
     * {@code
     * List<Object> params = new ArrayList<Object>();
     * params.add(100);
     *  
     * Map<String,Object> ht = new HashMap<String,Object>();
     * ht.put("methodName", "ticket.get");
     * ht.put("params", params);
     *  
     * List<Object> params2 = new ArrayList<Object>();
     * params2.add(101);
     *
     * Map<String,Object> ht2 = new HashMap<String,Object>();
     * ht2.put("methodName", "ticket.get");
     * ht2.put("params", params2);
     * 
     * List<Map<String,Object>> input = new ArrayList<Map<String,Object>>();
     * input.add(ht);
     * input.add(ht2);
     * List<Object> ouput = tracClientFactory.getSystem().multicall(input);
     * }
     * </pre>
     */
    List<Object> multicall(List<Map<String,Object>> signatures);

    /**
     * This method returns a list of strings, one for each (non-system) method supported by the RPC server.
     * 
     * Permission required: XML_RPC 
     */
    List<String> listMethods();

    /**
     * This method takes one parameter, the name of a method implemented by the RPC server. 
     * It returns a documentation string describing the use of that method. If no such string is available, an empty string is returned. The documentation string may contain HTML markup.
     * 
     * Permission required: XML_RPC 
     */
    String methodHelp(String method);

    /**
     * This method takes one parameter, the name of a method implemented by the RPC server. 
     * It returns an array of possible signatures for this method. A signature is an array of types. 
     * The first of these types is the return type of the method, the rest are parameters.
     * 
     * Permission required: XML_RPC
     */
    List<String> methodSignature(String method);

    /**
     * Returns a list with three elements. First element is the epoch (0=Trac 0.10, 1=Trac 0.11 or higher).
     * Second element is the major version number, third is the minor. 
     * Changes to the major version indicate API breaking changes, while minor version changes 
     * are simple additions, bug fixes, etc.
     * 
     * Permission required: XML_RPC
     */
    List<Integer> getAPIVersion();

}

