package com.example.finaltest.dao.impl;

import com.example.finaltest.dao.BoardDao;
import com.example.finaltest.dao.BoardDao;
import com.example.finaltest.entity.Board;
import com.example.finaltest.repository.BoardRepository;
import com.example.finaltest.repository.QBoardRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.finaltest.entity.QBoard.board;

@Component
public class BoardDaoImpl implements BoardDao {

    private final BoardRepository boardRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final QBoardRepository qBoardRepository;

    @Autowired
    public BoardDaoImpl(BoardRepository boardRepository, JPAQueryFactory jpaQueryFactory, QBoardRepository qBoardRepository) {
        this.boardRepository = boardRepository;
        this.jpaQueryFactory = jpaQueryFactory;
        this.qBoardRepository = qBoardRepository;
    }

    @Override
    public Board updateBoardName(Long id, String title, String contents) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(id);
        Board board;
        if(selectedBoard.isPresent()) {
            board = selectedBoard.get();
            board.setTitle(title);
            board.setContents(contents);
            board.setUpdatedAt(LocalDateTime.now());
            board = boardRepository.save(board);
        } else {
            throw new Exception();
        }

        return board;
    }

    @Override
    public Board insertBoard(Board board) {
        Board saveBoard = boardRepository.save(board);
        return saveBoard;
    }

    @Override
    public void deleteBoard(Long id) throws Exception {
        Optional<Board> selectedBoard = boardRepository.findById(id);

        // delete
        if (selectedBoard.isPresent()) {
            Board board = selectedBoard.get();
            boardRepository.delete(board);
        } else throw new Exception();
    }

    @Override
    public List<Board> allBoard() {
        return jpaQueryFactory.selectFrom(board).where(board.userName.isNotNull()).fetch();
    }

    @Override
    public List<Board> listBoardByBoardDesc() {
        return boardRepository.findAllByOrderByCreatedAt();
    }

    @Override
    public List<Board> selectBoardByUserId(String userName) {
        List<Board> selectBoard =
                boardRepository.findAllByUserId(userName);
        return selectBoard;
    }

    @Override
    public Board selectBoard(Long id) {
        /*Board selectBoard = boardRepository.getReferenceById(number);
        return selectBoard;*/
        Predicate predicate = board.id.eq(id);
        Optional<Board> selectBoard = qBoardRepository.findOne(predicate);
        return selectBoard.isPresent() ? selectBoard.get() : null;
    }
}
