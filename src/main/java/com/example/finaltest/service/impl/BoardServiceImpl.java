package com.example.finaltest.service.impl;

import com.example.finaltest.dao.BoardDao;
import com.example.finaltest.dto.BoardDto;
import com.example.finaltest.dto.BoardResponseDto;
import com.example.finaltest.dto.ChangeBoardDto;
import com.example.finaltest.entity.Board;
import com.example.finaltest.entity.Board;
import com.example.finaltest.service.BoardService;
import com.example.finaltest.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDao boardDao;

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public BoardResponseDto changeBoardName(Long id, String title, String contents) throws Exception {
        Board changeBoard = boardDao.updateBoardName(id, title, contents);

        BoardResponseDto boardResponseDto = new BoardResponseDto();

        boardResponseDto.setTitle(changeBoard.getTitle());
        boardResponseDto.setContents(changeBoard.getContents());

        return boardResponseDto;
    }

    @Override
    public BoardResponseDto saveBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setUserId(boardDto.getUserId());
        board.setUserName(boardDto.getUserName());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        Board saveBoard = boardDao.insertBoard(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto();

        boardResponseDto.setId(saveBoard.getId());
        boardResponseDto.setTitle(saveBoard.getTitle());
        boardResponseDto.setContents(saveBoard.getContents());
        boardResponseDto.setUserId(saveBoard.getUserId());
        boardResponseDto.setUserName(saveBoard.getUserName());

        return boardResponseDto;
    }

    @Override
    public void deleteBoard(Long id) throws Exception {
        boardDao.deleteBoard(id);
    }

    @Override
    public List<BoardResponseDto> allBoard() {
        List<Board> boards = boardDao.allBoard();
        List<BoardResponseDto> boardResponseDtoList =
                boards.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boardResponseDtoList;
    }

    @Override
    public List<BoardResponseDto> listBoardByContentsDesc() {
        return boardDao.listBoardByBoardDesc().stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<BoardResponseDto> getBoardByUserId(String userName) {
        List<Board> board = boardDao.selectBoardByUserId(userName);
        List<BoardResponseDto> boardResponseDtoList =
                board.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        return boardResponseDtoList;
    }

    @Override
    public BoardResponseDto getBoard(Long id) {
        Board board = boardDao.selectBoard(id);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setTitle(board.getTitle());
        boardResponseDto.setContents(board.getContents());
        boardResponseDto.setUserId(board.getUserId());
        boardResponseDto.setUserName(board.getUserName());

        return boardResponseDto;
    }

}
