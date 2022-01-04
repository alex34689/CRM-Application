package com.crm.institute.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Passgenerator {
	public static void main(String... args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		System.out.println(bCryptPasswordEncoder.encode("abc123.."));
	}
}
//$2a$04$Y4w9FXdVrqNRR8JPjELSy.PXpP7CENeqxdl4UOYPbFNAM7QIplMne
//$2a$04$QuQXUuUfRXB67HYUUYT3O./s6o7HNcyq/gTUaI.PpMs1VtUDOtlnO

