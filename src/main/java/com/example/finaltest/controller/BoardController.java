package com.example.finaltest.controller;

import com.example.finaltest.config.security.JwtTokenProvider;
import com.example.finaltest.dto.ChangeBoardDto;
import com.example.finaltest.dto.BoardDto;
import com.example.finaltest.dto.BoardResponseDto;
import com.example.finaltest.dto.UserDto;
import com.example.finaltest.entity.Board;
import com.example.finaltest.repository.UserRepository;
import com.example.finaltest.service.BoardService;
import com.example.finaltest.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final UserDetailService userDetailService;

    @Autowired
    public BoardController(BoardService boardService, UserDetailService userDetailService) {
        this.boardService = boardService;
        this.userDetailService = userDetailService;
    }

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PutMapping
    public ResponseEntity<BoardResponseDto> changeBoard(HttpServletRequest request, @RequestBody ChangeBoardDto changeBoardDto) throws Exception{
        String id = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        BoardResponseDto selectBoard = boardService.getBoard(changeBoardDto.getId());

        if (selectBoard.getUserId().equals(id)) {
            BoardResponseDto boardResponseDto = boardService.changeBoardName(changeBoardDto.getId(), changeBoardDto.getTitle(), changeBoardDto.getContents());
            return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<BoardResponseDto> createBoard(HttpServletRequest request, @RequestParam String title, @RequestParam String contents) {
        BoardDto boardDto = new BoardDto();
        String userId = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        UserDto userDto = userDetailService.getIdTokken(userId);

        boardDto.setTitle(title);
        boardDto.setContents(contents);
        boardDto.setUserId(userDto.getUid());
        boardDto.setUserName(userDto.getName());

        BoardResponseDto boardResponseDto = boardService.saveBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBoard(HttpServletRequest request, Long boardId) throws Exception {
        String userId = jwtTokenProvider.getUsername(request.getHeader("X-AUTH-TOKEN"));
        BoardResponseDto selectBoard = boardService.getBoard(boardId);

        if (selectBoard.getUserId().equals(userId)) {
            boardService.deleteBoard(boardId);
            return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("삭제할 수 있는 권한이 없습니다.");
    }



    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDto>> allBoard() {
        List<BoardResponseDto> boardResponseDto = boardService.allBoard();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @GetMapping("/listOrderByCreateAt")
    public ResponseEntity<List<BoardResponseDto>> listBoardByContentsDesc() {
        List<BoardResponseDto> boardResponseDto = boardService.listBoardByContentsDesc();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardResponseDto>> getBoardByUserId(String userId) {
        List<BoardResponseDto> boardResponseDto = boardService.getBoardByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<BoardResponseDto> getBoard(Long id) {
        BoardResponseDto boardResponseDto = boardService.getBoard(id);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }
}
