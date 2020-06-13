package com.duke.dls.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventoryRequest {

    private Long inventoryId;

    private String laptopSn;

    private String powerAdapterSn;

    private String status;

    private String ischeckedout;

    private String comments;

    private Long studentId;
}
