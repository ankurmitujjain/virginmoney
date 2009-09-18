package com.virginmoneygiving.security.verify.custom.cas.vmg;

/**
 *
 * @author Phil Collins
 *
 */
public interface SingleSignOutSender {

 public void sendSignOutMessage(String TicketId);
}
