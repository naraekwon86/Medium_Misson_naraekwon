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
import com.ll.medium.domain.post.postComment.entity.PostComment;
import jakarta.persistence.*;

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

    @OneToMany(mappedBy = "post", cascade = ALL , orphanRemoval = true)
    @Builder.Default
    @OrderBy("id DESC")
    private List<PostComment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    private boolean isPublished;
    @Setter(PROTECTED)
    private long hit;
    @Column(columnDefinition = "TEXT")
    private boolean isPaid; // true,false

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
    public PostComment writeComment(Member actor, String body){
        PostComment postComment = PostComment.builder()
                .post(this)
                .author(actor)
                .body(body)
                .build();
        comments.add(postComment);
        return postComment;
    }

}
