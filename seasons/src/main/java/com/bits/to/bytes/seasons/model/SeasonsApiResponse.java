package com.bits.to.bytes.seasons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeasonsApiResponse {
    String get;
    Object[] parameters;
    Object[] errors;
    int results;
    Object paging;
    List<Integer> response;
}
