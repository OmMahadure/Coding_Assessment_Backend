package com.main.dto;

public record LoginResponse(
		 Long id,
		    String fullName,
		    String email,
		    String role,
		    String message,
		    String accessToken,
		    String tokenType) {

}
