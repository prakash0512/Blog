package com.prakash.blog.mapper;

import com.prakash.blog.dto.PostDto;
import com.prakash.blog.entites.Category;
import com.prakash.blog.entites.Comment;
import com.prakash.blog.entites.Post;
import com.prakash.blog.entites.User;
import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.dto.CommentDto;
import com.prakash.blog.dto.UserDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    // Convert Post entity to PostDto
    public static PostDto postToPostDto(Post post) {
        PostDto postDto = new PostDto();

        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setImageName(post.getImageName());
        postDto.setAddedDate(post.getAddedDate());

        // Mapping Category
        if (post.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(post.getCategory().getCategoryId());
            categoryDto.setCategoryTitle(post.getCategory().getCategoryTitle());
            categoryDto.setCategoryDescription(post.getCategory().getCategoryDescription());
            postDto.setCategory(categoryDto);
        } else {
            postDto.setCategory(null);
        }

        // Mapping User
        if (post.getUser() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(post.getUser().getId());
            userDto.setName(post.getUser().getName());
            userDto.setEmail(post.getUser().getEmail());
            userDto.setPassword(post.getUser().getPassword());
            userDto.setAbout(post.getUser().getAbout());

            postDto.setUser(userDto);
        } else {
            postDto.setUser(null);
        }
        
        Set<CommentDto> commentDtos = new HashSet<>();
        for (Comment comment : post.getComments()) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setContent(comment.getContent());
            commentDtos.add(commentDto);
        }
        postDto.setComments(commentDtos);

        return postDto;
    }
    
    
    
    public static Post postDtoToPost(PostDto postDto) {
        Post post = new Post();

        post.setPostId(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());

        // Mapping Category (Check for null)
        if (postDto.getCategory() != null) {
            Category category = new Category();
            category.setCategoryId(postDto.getCategory().getCategoryId());
            category.setCategoryTitle(postDto.getCategory().getCategoryTitle());
            category.setCategoryDescription(postDto.getCategory().getCategoryDescription());
            post.setCategory(category);
        } else {
            // If category is null, you can either:
            // - Set it to a default category, or
            // - Throw an exception or return a specific message for invalid input
            // For now, we're just leaving it as null (this should be handled in your business logic)
            post.setCategory(null); 
        }

        // Mapping User (if available)
        if (postDto.getUser() != null) {
            User user = new User();
            user.setId(postDto.getUser().getId());
            user.setName(postDto.getUser().getName());
            user.setEmail(postDto.getUser().getEmail());
            user.setPassword(postDto.getUser().getPassword());
            user.setAbout(postDto.getUser().getAbout());
            post.setUser(user);
        } 

        // Handling comments mapping
        Set<Comment> comments = new HashSet<>();
        for (CommentDto commentDto : postDto.getComments()) {
            Comment comment = new Comment();
            comment.setId(commentDto.getId());
            comment.setContent(commentDto.getContent());
            comment.setPost(post);  // Set the back-reference to the Post entity
            comments.add(comment);
        }
        post.setComments(comments);

        return post;
    }


    // Convert PostDto to Post entity
  /*  public static Post postDtoToPost(PostDto postDto) {
        Post post = new Post();

        post.setPostId(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());

        // Mapping Category
        if (postDto.getCategory() != null) {
            Category category = new Category();
            category.setCategoryId(postDto.getCategory().getCategoryId());
            category.setCategoryTitle(postDto.getCategory().getCategoryTitle());
            category.setCategoryDescription(postDto.getCategory().getCategoryDescription());
            post.setCategory(category);
        } else {
            post.setCategory(null);
        }

        // Mapping User
        if (postDto.getUser() != null) {
            User user = new User();
            user.setId(postDto.getUser().getId());
            user.setName(postDto.getUser().getName());
            user.setEmail(postDto.getUser().getEmail());
            user.setPassword(postDto.getUser().getPassword());
            user.setAbout(postDto.getUser().getAbout());
            post.setUser(user);
        } 
        Set<Comment> comments = new HashSet<>();
        for (CommentDto commentDto : postDto.getComments()) {
            Comment comment = new Comment();
            comment.setId(commentDto.getId());
            comment.setContent(commentDto.getContent());
            // You should also set the post reference in the Comment (since it's a ManyToOne relationship)
            comment.setPost(post);
            comments.add(comment);
        }
        post.setComments(comments);

        return post;
    }
     */
    
   

    public static List<PostDto> postListToPostDtoList(List<Post> posts) {
        return posts.stream()
                    .map(PostMapper::postToPostDto)  
                    .collect(Collectors.toList());
    }
    
    public static List<Post> postDtoListToPostList(List<PostDto> postDtos) {
        return postDtos.stream()
                       .map(PostMapper::postDtoToPost)  
                       .collect(Collectors.toList());
    }


} 


/*package com.prakash.blog.mapper;

import com.prakash.blog.dto.CategoryDto;
import com.prakash.blog.dto.CommentDto;
import com.prakash.blog.dto.PostDto;
import com.prakash.blog.entites.Category;
import com.prakash.blog.entites.Comment;
import com.prakash.blog.entites.Post;
import com.prakash.blog.entites.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PostMapper {

    // Convert Post entity to PostDto
    public static PostDto postToPostDto(Post post) {
        PostDto postDto = new PostDto();
        
        // Set basic fields
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setImageName(post.getImageName());
        postDto.setAddedDate(post.getAddedDate());

        // Set category and user (assuming CategoryDto and UserDto are already mapped)
        // Mapping Category
        if (post.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(post.getCategory().getCategoryId());
            categoryDto.setCategoryTitle(post.getCategory().getCategoryTitle());
            categoryDto.setCategoryDescription(post.getCategory().getCategoryDescription());
            postDto.setCategory(categoryDto);
        } else {
            postDto.setCategory(null);
        }
        if (postDto.getUser() != null) {
            User user = new User();
            user.setId(postDto.getUser().getId());
            user.setName(postDto.getUser().getName());
            user.setEmail(postDto.getUser().getEmail());
            user.setPassword(postDto.getUser().getPassword());
            user.setAbout(postDto.getUser().getAbout());
            post.setUser(user);
        } 

      
        Set<CommentDto> commentDtos = new HashSet<>();
        for (Comment comment : post.getComments()) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setContent(comment.getContent());
            commentDtos.add(commentDto);
        }
        postDto.setComments(commentDtos);
        
        return postDto;
    }

    // Convert PostDto to Post entity
    public static Post postDtoToPost(PostDto postDto) {
        Post post = new Post();
        
        // Set basic fields
        post.setPostId(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());

        // Mapping Category
        if (postDto.getCategory() != null) {
            Category category = new Category();
            category.setCategoryId(postDto.getCategory().getCategoryId());
            category.setCategoryTitle(postDto.getCategory().getCategoryTitle());
            category.setCategoryDescription(postDto.getCategory().getCategoryDescription());
            post.setCategory(category);
        } else {
            post.setCategory(null);
        }

        // Mapping User
        if (postDto.getUser() != null) {
            User user = new User();
            user.setId(postDto.getUser().getId());
            user.setName(postDto.getUser().getName());
            user.setEmail(postDto.getUser().getEmail());
            user.setPassword(postDto.getUser().getPassword());
            user.setAbout(postDto.getUser().getAbout());
            post.setUser(user);
        } 
        
      
      

        // Set comments (convert each CommentDto to Comment entity)
        Set<Comment> comments = new HashSet<>();
        for (CommentDto commentDto : postDto.getComments()) {
            Comment comment = new Comment();
            comment.setId(commentDto.getId());
            comment.setContent(commentDto.getContent());
            // You should also set the post reference in the Comment (since it's a ManyToOne relationship)
            comment.setPost(post);
            comments.add(comment);
        }
        post.setComments(comments);

        return post;
    }
    
    

    public static List<PostDto> postListToPostDtoList(List<Post> posts) {
        return posts.stream()
                    .map(PostMapper::postToPostDto)  
                    .collect(Collectors.toList());
    }
    
    public static List<Post> postDtoListToPostList(List<PostDto> postDtos) {
        return postDtos.stream()
                       .map(PostMapper::postDtoToPost)  
                       .collect(Collectors.toList());
    }
} */


