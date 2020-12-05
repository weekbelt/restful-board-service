package me.weekbelt.restfulboardservice.modules.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.restfulboardservice.modules.baseEntity.BaseTimeEntity;

import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Lob
    private String content;

    private Integer viewCount;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Builder
    public Board(String title, String content, Integer viewCount, BoardType boardType) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.boardType = boardType;
    }

    public void plusViewCount() {
        viewCount++;
    }

}

