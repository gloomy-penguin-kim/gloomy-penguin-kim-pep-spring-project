package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidAccountIdException;
import com.example.exception.InvalidMessageIdException;
import com.example.exception.InvalidMessageTextException;
import com.example.entity.Account; 
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired; 

@Service
public class MessageService {

    private final MessageRepository messageRepository; 
    private final AccountService accountService; 

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountService accountService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    public Message create(Message message) throws InvalidMessageTextException, InvalidAccountIdException { 
        validateMessageText(message.getMessageText());
        
        if (!accountService.verifyAccountExistsByAccountId(message.getPostedBy())) {
            throw new InvalidAccountIdException("The \"posted by\" account could not be found");
        }
        
        return messageRepository.save(message); 
    } 

    public Message findById(Integer id) {
        return messageRepository.findById(id).orElseThrow(InvalidMessageIdException::new); 
    }

    public List<Message> findAllMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public List<Message> findAllMessagesByAccount(Account account) { 
        return (List<Message>) messageRepository.findAllByPostedBy(account.getAccountId());
    }

    public List<Message> findAllMessagesByAccountId(Integer accountId) { 
        return (List<Message>) messageRepository.findAllByPostedBy(accountId);
    }

    public Integer update(Integer messageId, Message message) throws InvalidMessageIdException, InvalidMessageTextException {
        validateMessageText(message.getMessageText());

        Message existingMessage = messageRepository.findById(messageId)
                        .orElseThrow(() -> new InvalidMessageIdException("Message cannot be found to update."));
 
        existingMessage.setMessageText(message.getMessageText()); 
        messageRepository.save(existingMessage);

        return 1;
    }

    public Integer delete(Integer id) {
        Optional<Message> messageToDelete = messageRepository.findById(id);
        if (!messageToDelete.isPresent()) {
            return null; 
        } 
        messageRepository.delete(messageToDelete.get());
        return 1; 
    }

    public boolean validateMessageText(String messageText) {
        if (messageText.isEmpty() || messageText.isBlank()) {
            throw new InvalidMessageTextException("The message cannot be empty.");
        }
        else if (messageText.length() > 255) {
            throw new InvalidMessageTextException("The message cannot be over 255 characters.");
        }
        return true; 
    }
}
