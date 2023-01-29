package com.example.bubbleskhu.bubble.application;

import com.example.bubbleskhu.bubble.dao.BubbleRepository;
import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.bubble.dao.dto.request.BubbleRequestDTO;
import com.example.bubbleskhu.bubble.dao.dto.response.BubbleResponseDTO;
import com.example.bubbleskhu.global.error.CustomException;
import com.example.bubbleskhu.joinTeam.dao.JoinTeamRepository;
import com.example.bubbleskhu.user.dao.UserRepository;
import com.example.bubbleskhu.joinTeam.domain.JoinTeam;
import com.example.bubbleskhu.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BubbleService {
    private final UserRepository userRepository;
    private final BubbleRepository bubbleRepository;
    private final JoinTeamRepository joinTeamRepository;
    @Transactional
    public void saveBubble(Principal principal,BubbleRequestDTO bubbleRequestDTO) {
        User user = getUser(principal);
        Bubble bubble = Bubble.builder()
                .name(bubbleRequestDTO.getName())
                .limitNumberOfUser(bubbleRequestDTO.getNumberOfUser())
                .lesson(bubbleRequestDTO.getLesson())
                .build();
        bubbleRepository.save(bubble);

        joinTeamRepository.save(JoinTeam.builder()
                .bubble(bubble)
                .user(user)
                .build());
    }

    private User getUser(Principal principal) {
        String name = principal.getName();
        User user = userRepository.findByUsername(name).get();
        return user;
    }

    @Transactional(readOnly = true)
    public BubbleResponseDTO findBubbleById(Long id) {
        Bubble bubble = bubbleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Bubble 존재하지 않습니다.", HttpStatus.NOT_FOUND));
        return BubbleResponseDTO.builder()
                .id(bubble.getId())
                .name(bubble.getName())
                .lesson(bubble.getLesson())
                .presentNumberOfUser(bubble.getUsers().size())
                .build();
    }

    @Transactional
    public void deleteBubbleById(Long id) {
        Bubble bubble = bubbleRepository.findById(id).orElseThrow(() -> new CustomException( "해다 Bubble이 존재하지 않습니다.",HttpStatus.NOT_FOUND));
        joinTeamRepository.findByBubble(bubble).stream().forEach(joinTeam -> joinTeamRepository.delete(joinTeam));
        bubbleRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<BubbleResponseDTO> findAll() {
        return bubbleRepository.findAll().stream().map(bubble -> BubbleResponseDTO.builder()
                .id(bubble.getId())
                .name(bubble.getName())
                .lesson(bubble.getLesson())
                .presentNumberOfUser(bubble.getUsers().size())
                .build()).collect(Collectors.toList());
    }

    @Transactional
    public void joinBubble(Long id, Principal principal) {
        User user = getUser(principal);
        Bubble bubble = bubbleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Bubble 존재하지 않습니다.", HttpStatus.NOT_FOUND));
        joinTeamRepository.save(JoinTeam.builder()
                .bubble(bubble)
                .user(user)
                .build());

    }
}
