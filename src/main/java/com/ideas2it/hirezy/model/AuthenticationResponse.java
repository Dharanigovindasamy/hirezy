package com.ideas2it.hirezy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * This class is used to act as the
 * response to the request sent by the user
 * @author audhithiyah
 */
public class AuthenticationResponse {

    private String token;


}
