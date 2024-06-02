package com.example.something.article;

import com.example.something.article.DTO.AddArticleDTO;
import com.example.something.article.DTO.UpdateArticleDetailsDTO;
import com.example.something.article.DTO.UpdateArticleQuantity;
import com.example.something.auth.DTO.SignInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService service;

    @PostMapping("/add/{id}")
    private ResponseEntity<?> create(@PathVariable int id , @RequestBody AddArticleDTO req){
        try{
            String name = req.getName();
            String description = req.getDescription();
            int price = req.getPrice();
            int quantite = req.getQuantite();
            String imagePath = req.getImagePath();

            if(name != null && description != null && price != 0 && quantite != 0 && imagePath != null) {
                ResponseEntity<?> res =  service.createArticle(id , req);
                if (res != null) {
                    return ResponseEntity.ok(res);
                } else {
                    return new ResponseEntity<>("article not created", HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity<>("one field at least is missing", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateDetails/{id}")
    private ResponseEntity<?> updateDetails(@PathVariable int id , @RequestBody UpdateArticleDetailsDTO req){
        try{
            String name = req.getName();
            String description = req.getDescription();
            int price = req.getPrice();
            String imagePath = req.getImagePath();

            if(name != null && description != null && price != 0 && imagePath != null) {
                ResponseEntity<?> res =  service.updateArticleDetails(id , req);
                if (res != null) {
                    return ResponseEntity.ok(res);
                } else {
                    return new ResponseEntity<>("article not updated", HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity<>("one field at least is missing", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateQuantity/{id}")
    private ResponseEntity<?> updateQuantity(@PathVariable int id , @RequestBody UpdateArticleQuantity req){
        try{
            int quantity = req.getQuantite();
            if(quantity != 0) {
                ResponseEntity<?> res =  service.updateArticleQuantity(id , req);
                if (res != null) {
                    return ResponseEntity.ok(res);
                } else {
                    return new ResponseEntity<>("article not updated", HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity<>("one field at least is missing", HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getall")
    private ResponseEntity<?> getAll(){
        try{
            ResponseEntity<?> res =  service.getAllArticles();
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return new ResponseEntity<>("not getted", HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{id}")
    private ResponseEntity<?> getById(@PathVariable int id){
        try{
            ResponseEntity<?> res =  service.getArticleById(id);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return new ResponseEntity<>("not getted", HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id" +
            "}")
    private ResponseEntity<?> getAll(@PathVariable int id){
        try{
            ResponseEntity<?> res =  service.deleteArticle(id);
            if (res != null) {
                return ResponseEntity.ok(res);
            } else {
                return new ResponseEntity<>("not deleted", HttpStatus.NOT_FOUND);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
