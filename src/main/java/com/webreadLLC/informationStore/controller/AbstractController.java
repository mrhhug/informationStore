package com.webreadLLC.informationStore.controller;

import com.webreadLLC.informationStore.CRUD.CRUD;

/**
 * @author michael
 */


public abstract class AbstractController {

    static CRUD crud;
    
    AbstractController(CRUD crud) {
	this.crud = crud;
    }
}
