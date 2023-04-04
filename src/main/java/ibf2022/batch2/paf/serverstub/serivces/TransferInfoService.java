package ibf2022.batch2.paf.serverstub.serivces;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.batch2.paf.exceptions.FundsTransferException;
import ibf2022.batch2.paf.serverstub.models.TransferInfo;
import ibf2022.batch2.paf.serverstub.repositories.FundsRepository;
import ibf2022.batch2.paf.serverstub.repositories.TransferInfoRepository;

@Service
public class TransferInfoService {
    @Autowired
    TransferInfoRepository transferInfoRepository;

    @Autowired
    FundsRepository fundsRepository;

    public Boolean accountExists(String name){
        return fundsRepository.findAccountByName(name).isPresent();
    }

    @Transactional(rollbackFor = FundsTransferException.class)
    public String transfer(TransferInfo transfer) throws FundsTransferException{
        String xferId = UUID.randomUUID().toString().substring(0,8);

        fundsRepository.withdraw(transfer.getSrcAcct(), transfer.getAmount());

        fundsRepository.deposit(transfer.getDestAcct(), transfer.getAmount());

        transferInfoRepository.transferInfo(transfer, xferId);

        return xferId;
    }


}
