package com.ll.medium.domain.post.post.repository;

import com.ll.medium.domain.post.post.entity.Post;
import com.ll.medium.domain.member.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop30ByIsPublishedOrderByIdDesc(boolean isPublished);

    Page<Post> findByIsPublishedAndTitleContainingIgnoreCaseOrIsPublishedAndBodyContainingIgnoreCase(
            boolean isPublished ,String kw, boolean isPublished_,String kw_ ,Pageable pageable);
    Page<Post> findByAuthorAndTitleContainingIgnoreCaseOrAuthorAndBodyContainingIgnoreCase(
            Member author , String kw , Member author_, String kw_ , Pageable pageable
    );


}
