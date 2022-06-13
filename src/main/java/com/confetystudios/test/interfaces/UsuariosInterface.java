package com.confetystudios.test.interfaces;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.confetystudios.test.dto.UsuariosDocument;

public interface UsuariosInterface extends  MongoRepository<UsuariosDocument, Integer>{
	    @Query("{ usuario:'?0'}")
	    public List<UsuariosDocument> findItemByName(String name);
//	    
//	    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
//	    List<GroceryItem> findAll(String category);
}
