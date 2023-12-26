package com.ll.medium.domain.post.post.entity;

import com.ll.medium.domain.member.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import com.ll.medium.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import com.ll.medium.domain.post.postLike.entity.PostLike;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.CascadeType.ALL;
@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
@Setter
public class Post extends BaseEntity{
    @OneToMany(mappedBy = "post",cascade = ALL, orphanRemoval = true)
    @Builder.Default
    private List<PostLike> likes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    private String title;
    private String body;
    private boolean isPublished;
    @Setter(PROTECTED)
    private long hit;
    public void increaseHit(){
        hit++;
    }
    public void addlike(Member member){
        if (hasLike(member)){
            return;
        }
        likes.add(PostLike.builder()
                .post(this)
                .member(member)
                .build());
    }
    public boolean hasLike(Member member){
        return likes.stream()
                .anyMatch(postLike -> postLike.getMember().equals(member));
    }
    public void deleteLike(Member member){
        likes.removeIf(postLike -> postLike.getMember().equals(member));
    }


}
