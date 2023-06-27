package ibf2022.tfip.simplesecondbrain.Server.repositories;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    private final String COLLECTION_NAME = "notes";

    public List<Document> getUsersNotes(){
        List<Document> notes = new ArrayList<Document>();
        notes =  mongoTemplate.findAll(Document.class, COLLECTION_NAME);
        return notes;
    }

    public void saveUserNotes(Document note){
        mongoTemplate.save(note, COLLECTION_NAME);
    }

    public void saveAllUserNotes(List<Document> notes){
        mongoTemplate.insert(notes, COLLECTION_NAME);
    }
    
    public void deleteUserNotes(Document note){
        mongoTemplate.remove(note, COLLECTION_NAME);
    }

    public void updateUserNotes(Document note){
        mongoTemplate.save(note, COLLECTION_NAME);
    }


}