package it.isac.server.utils.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Superclass for db configuration properties. Used for
 * serialization/deserialization.
 * 
 * @author Pievis
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class DBConfig {

}
