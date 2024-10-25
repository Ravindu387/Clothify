package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderCart {
    private String itemCode;
    private String description;
    private int qty;
    private Double unitPrice;
    private Double total;
}
