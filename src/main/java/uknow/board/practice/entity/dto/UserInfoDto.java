package uknow.board.practice.entity.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoDto {
    private Long id;
    private String uid; // 회원 ID (JWT 토큰 내 정보)
    private String name;
}
