package ibf2022.batch2.paf.serverstub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferInfo {
    
    private String srcAcct;

    private String destAcct;

    private Float amount;
}
