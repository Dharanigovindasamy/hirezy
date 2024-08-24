package com.ideas2it.hirezy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     This class used as location entity details
 *
 * </p>
 *
 * @author dharani.govindhasamy
 * @version 1
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LocationDto {
    private  Long id;
    private String state;
    private String city;
}
