package com.ideas2it.hirezy.dto;

import jakarta.validation.constraints.NotBlank;
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
    private  long id;

    @NotBlank(message = "State can't be blank")
    private String state;

    @NotBlank(message = "City can't be blank")
    private String city;
}
