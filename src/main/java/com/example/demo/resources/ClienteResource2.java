package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Cliente;
import com.example.demo.services.ClienteService;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/Clientes")
public class ClienteResource2 {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
	}
}
