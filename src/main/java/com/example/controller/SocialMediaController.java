package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Account; 
import com.example.entity.Message;

import com.example.service.AccountService;
import com.example.service.MessageService;

import com.example.exception.InvalidAccountIdException;
import com.example.exception.InvalidMessageIdException;
import com.example.exception.InvalidMessageTextException;
import com.example.exception.InvalidPasswordException;
import com.example.exception.InvalidUsernameException;
 
import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@Controller
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService; 

    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService; 
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> accountRegister(@RequestBody Account newAccount) {
        try {
            return ResponseEntity.ok(accountService.register(newAccount));
        } catch (Exception exception) {
            return ResponseEntity.status(409).body(null);
        }
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> accountLogin(@RequestBody Account newAccount) {
        try {  
            return ResponseEntity.ok(accountService.login(newAccount)); 
        }
        catch (InvalidUsernameException | InvalidPasswordException exception) {
            return ResponseEntity.status(401).body(null);
        } 
    }

    // create message 
    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> messageCreate(@RequestBody Message newMessage) {
        try {  
            return ResponseEntity.ok(messageService.create(newMessage));  
        } catch (InvalidAccountIdException | InvalidMessageTextException exception) {
            return ResponseEntity.status(400).body(null);
        }  
    }

    // delete message 
    @DeleteMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> messageDelete(@PathVariable Integer messageId) { 
        return ResponseEntity.ok().body(messageService.delete(messageId));
    }

    // get messages for account
    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody ResponseEntity<List<Message>> messagesByAccountId(@PathVariable Integer accountId) { 
        return ResponseEntity.ok().body(messageService.findAllMessagesByAccountId(accountId));
    }

    // get messages for account
    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> messagesAll() { 
        return ResponseEntity.ok().body(messageService.findAllMessages());
    } 

    // get messages for account
    @GetMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Message> messageByMessageId(@PathVariable Integer messageId) { 
        try {
            return ResponseEntity.ok().body(messageService.findById(messageId)); 
        }
        catch(InvalidMessageIdException exception) {
            return ResponseEntity.ok().body(null); 
        }    
    } 

    // get messages for account
    @PatchMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> messageUpdate(@RequestBody Message message, @PathVariable Integer messageId) { 
        try {
            return ResponseEntity.ok().body(messageService.update(messageId, message)); 
        }
        catch (InvalidMessageIdException | InvalidMessageTextException exception) {
            return ResponseEntity.status(400).body(null); 
        } 
    }
}
