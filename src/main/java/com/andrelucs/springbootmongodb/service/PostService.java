package com.andrelucs.springbootmongodb.service;

import com.andrelucs.springbootmongodb.domain.Post;
import com.andrelucs.springbootmongodb.dto.CommentDTO;
import com.andrelucs.springbootmongodb.repository.PostRepository;
import com.andrelucs.springbootmongodb.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(String id){
        Post u = postRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Post not found"));

        return u;
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
    public Post insert(Post obj){
        Post u = postRepository.insert(obj);
        return u;
    }

    public Post addComment(CommentDTO obj, Post post){
        post.getComments().add(obj);
        return update(post);
    }

    public void deleteById(String id){
		findById(id);

        postRepository.deleteById(id);
    }

    public Post update(Post obj){
        Post p = findById(obj.getId());
		updateData(p, obj);
        return postRepository.save(p);
    }

	private void updateData(Post o1, Post o2){
		if(o2.getBody() != null && o2.getBody() != o1.getBody()) o1.setBody(o2.getBody());
		if(o2.getTitle() != null && o2.getTitle() != o1.getTitle()) o1.setTitle(o2.getTitle());
        if(o2.getComments().equals(null) && !o2.getComments().equals(o1.getComments())) o1.setComments(o2.getComments());
	}
}
