package com.example.demo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.*;
import com.example.demo.domain.*;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer>{
	
	
	
}
