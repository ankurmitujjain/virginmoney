package com.virginmoneygiving.indexerservice.jms;

/**
 * Scheduled service that rebuilds charity index from the database contents.
 * 
 * Mostly used to set up the initial index after a restart. Works by reading
 * the datasource for a list of charities and firing off a bunch of add messages.
 * 
 * @author Ian Priest
 */
public class CharityDatabaseIndexer {

}
