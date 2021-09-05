package br.com.template.restcrud.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
	private int statusCode;
	private LocalDateTime timestamp;
	private String message;
	private String description;
}
