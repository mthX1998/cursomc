package com.mancini.cursomc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mancini.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Integer>{

	Optional<Produto> findById(Integer id);
     
}
