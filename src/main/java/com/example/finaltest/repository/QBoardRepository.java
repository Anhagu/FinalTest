package com.example.finaltest.repository;

import com.example.finaltest.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface QBoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

}
