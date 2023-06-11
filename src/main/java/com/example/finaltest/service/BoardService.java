package com.example.finaltest.service;

import com.example.finaltest.dto.BoardDto;
import com.example.finaltest.dto.BoardResponseDto;
import com.example.finaltest.dto.ChangeBoardDto;

import java.util.List;

public interface BoardService {


    BoardResponseDto changeBoardName(Long id, String title, String contents) throws Exception;

    BoardResponseDto saveBoard(BoardDto boardDto);

    void deleteBoard(Long id) throws Exception;

    List<BoardResponseDto> allBoard();

    List<BoardResponseDto> listBoardByContentsDesc();

    List<BoardResponseDto> getBoardByUserId(String userName);

    BoardResponseDto getBoard(Long id);

}
