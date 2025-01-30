package com.eazyapp.requestwrapper;

import com.eazyapp.dto.OrderDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestWrapper {
    private OrderDTO order;
    private double totalAmount; // It may not be necessary to have this as separate from OrderDTO
}
