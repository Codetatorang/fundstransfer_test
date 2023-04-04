package ibf2022.batch2.paf.serverstub.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.paf.Utils;
import ibf2022.batch2.paf.serverstub.models.TransferInfo;

@Repository
public class TransferInfoRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void transferInfo(TransferInfo transfer, String transactionId){
        Document doc = Utils.toDocument(transfer, transactionId);

        mongoTemplate.insert(doc,"fundstransfer");
    }
}
