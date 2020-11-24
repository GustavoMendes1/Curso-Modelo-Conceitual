package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.services.exceptions.DataIntegrityException;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired 
	private CategoriaRepository repo;
	
	public Categoria findCategoria(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
	}
	
	public List<Categoria> getCategorias(){
		List<Categoria> listaCategorias = repo.findAll();
		return listaCategorias; 
	}
	
	public Categoria insertCategoria(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}
	
	public Categoria updateCategoria(Categoria categoria) {
		findCategoria(categoria.getId());
		return repo.save(categoria);
	}
	
	public void delete(Integer id) {
		findCategoria(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel deletar uma categoria que possui produtos!"); 
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer size, String orderBy,String direction){
		
		PageRequest pageRequest =  PageRequest.of(page, size,Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
		
	}
}
