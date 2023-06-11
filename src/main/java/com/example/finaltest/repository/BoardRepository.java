package com.example.finaltest.repository;

import com.example.finaltest.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByUserId(String userId);

    List<Board> findAllByOrderByCreatedAt();

//    @Query("SELECT p FROM Board AS p WHERE p.stock = ?1")
//    List<Board> listByStock(@Param("stock") int stock);
}
