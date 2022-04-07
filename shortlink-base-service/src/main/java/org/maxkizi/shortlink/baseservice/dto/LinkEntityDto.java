package org.maxkizi.shortlink.baseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkEntityDto {
    private String shortLink;
    private String fullLink;
    private long countOfCalls = 0L;
}
