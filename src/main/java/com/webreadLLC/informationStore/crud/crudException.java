package com.webreadLLC.informationStore.crud;

import java.sql.SQLException;

/**
 * @author michael
 */
public class crudException extends SQLException {

    public crudException(Exception e) {
	super(e);
    }
}
