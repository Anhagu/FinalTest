package com.example.finaltest.dao;

import com.example.finaltest.entity.Board;

import java.util.List;

public interface BoardDao {

    Board updateBoardName(Long id, String title, String contents) throws Exception;

    Board insertBoard(Board board);

    void deleteBoard(Long id) throws Exception;

    List<Board> allBoard();

    List<Board> listBoardByBoardDesc();

    List<Board> selectBoardByUserId(String userName);

    Board selectBoard(Long id);
}
