package com.example.demo.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Categoria;
import com.example.demo.services.CategoriaService;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		
		Categoria categoria = service.findCategoria(id);
		return ResponseEntity.ok().body(categoria);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Categoria>> getAllCategoria(){
		
		List<Categoria> categorias = service.getCategorias();
		return ResponseEntity.ok().body(categorias);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public  ResponseEntity<Void> Insert(@RequestBody Categoria categoria){
		categoria = service.insertCategoria(categoria);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		
		return ResponseEntity
				.created(uri)
				.build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Integer id ){
		categoria.setId(id);
		categoria = service.updateCategoria(categoria);
		return ResponseEntity.noContent().build();
	}
}
