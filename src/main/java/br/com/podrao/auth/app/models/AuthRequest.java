package br.com.podrao.auth.app.models;

import lombok.Getter;

@Getter
public class AuthRequest {

	private String username;
	private String password;
}