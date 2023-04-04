package ibf2022.batch2.paf.serverstub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer id;

    private String fullName;
    
    private Integer isActive;

    private String acctType;

    private float balance;
}
