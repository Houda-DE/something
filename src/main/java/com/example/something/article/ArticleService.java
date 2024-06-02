package com.example.something.article;

import com.example.something.Entities.Article;
import com.example.something.Entities.Type;
import com.example.something.Entities.User;
import com.example.something.Repositoriess.ArticleRepository;
import com.example.something.Repositoriess.UserRepository;
import com.example.something.article.DTO.AddArticleDTO;
import com.example.something.article.DTO.UpdateArticleDetailsDTO;
import com.example.something.article.DTO.UpdateArticleQuantity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    public ResponseEntity<?> createArticle(int id ,  AddArticleDTO dto){

        Optional<Article> articleOptional = articleRepository.findArticleByName(dto.getName());

        Optional<User> optionalUser = userRepository.findById(id);

        User user = optionalUser.get();
        Type type = user.getType();

        if(articleOptional.isPresent()){
            return new ResponseEntity<>("l'article exite deja , ou l'utilisateur n'existe pas" , HttpStatus.BAD_REQUEST);
        } else if (type == Type.ADMIN){
            Article article = new Article();
            article.setName(dto.getName());
            article.setDescription(dto.getDescription());
            article.setQuantite(dto.getQuantite());
            article.setImagePath(dto.getImagePath());
            article.setPrice(dto.getPrice());
            Article art = articleRepository.save(article);
            return new ResponseEntity<>(art , HttpStatus.OK);
        }else {
            return new ResponseEntity<>("vous n'etes pas admin" , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateArticleDetails(int id , UpdateArticleDetailsDTO dto){

        Optional<Article> articleOptional = articleRepository.findById(id);

        Optional<User> optionalUser = userRepository.findById(id);

        User user = optionalUser.get();
        Type type = user.getType();

        if(articleOptional.isPresent() && type == Type.ADMIN){
            Article article = new Article();
            article.setName(dto.getName());
            article.setDescription(dto.getDescription());
            article.setImagePath(dto.getImagePath());
            article.setPrice(dto.getPrice());
            Article art = articleRepository.save(article);
            return new ResponseEntity<>(art , HttpStatus.OK);
        }else {
            return new ResponseEntity<>("vous n'etes pas admin" , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateArticleQuantity(int id , UpdateArticleQuantity dto){

        Optional<Article> articleOptional = articleRepository.findById(id);

        Optional<User> optionalUser = userRepository.findById(id);

        User user = optionalUser.get();
        Type type = user.getType();

        if(articleOptional.isPresent() && type == Type.ADMIN){
            Article article = new Article();
            article.setQuantite(dto.getQuantite());
            Article art = articleRepository.save(article);
            return new ResponseEntity<>(art , HttpStatus.OK);
        }else {
            return new ResponseEntity<>("vous n'etes pas admin" , HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getAllArticles(){
        List<Article> articles = articleRepository.findAll();
        return new ResponseEntity<>(articles , HttpStatus.OK);
    }

    public ResponseEntity<?> getArticleById(int id){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            return new ResponseEntity<>(article , HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<?> deleteArticle(int id){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()){
            Article article = optionalArticle.get();
            articleRepository.delete(article);
            return new ResponseEntity<>("deleted successfully" , HttpStatus.OK);
        }
        return null;
    }

}
