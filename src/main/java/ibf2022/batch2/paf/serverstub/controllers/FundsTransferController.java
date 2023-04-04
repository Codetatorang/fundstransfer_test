package ibf2022.batch2.paf.serverstub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.paf.exceptions.FundsTransferException;
import ibf2022.batch2.paf.serverstub.models.TransferInfo;
import ibf2022.batch2.paf.serverstub.serivces.TransferInfoService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/api")
public class FundsTransferController {
	@Autowired
	TransferInfoService xferSvc;

	@PostMapping(path = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postTransfer(@RequestBody String payload) {
		System.out.printf(">>> payload: %s\n", payload);

		TransferInfo transfer = ibf2022.batch2.paf.Utils.toTransfer(payload);

		if(!xferSvc.accountExists(transfer.getSrcAcct())){
			JsonObject err = Json.createObjectBuilder()
				.add("message", "Account %s does not exists".formatted(transfer.getSrcAcct()))
				.build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err.toString());
		}

		try{
			String xferId = xferSvc.transfer(transfer);

			JsonObject resp = Json.createObjectBuilder()
				.add("transactionId", xferId)
				.build();
			return ResponseEntity.ok(resp.toString());
		}catch(FundsTransferException ex){
			JsonObject err = Json.createObjectBuilder()
				.add("message", ex.getMessage())
				.build();
			return ResponseEntity.badRequest().body(err.toString());
		}
		// Transfer successful return the following JSON object
		// { "transactionId", "aTransactionId" }
		//
		// Transfer failed return the following JSON object
		// { "message", "Error message" }

	}
}
