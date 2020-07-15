package br.com.moneyup.dto;

import java.util.Optional;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorResponseDTO
{

	private ErrorResponseDTO()
	{
	}

	private String message;

	public String getMessage()
	{
		return message;
	}

	public static class Builder
	{

		private String			message;

		private ObjectError	objectError;

		public Builder withMessage(final String message)
		{
			this.message = message;
			return this;
		}

		public Builder withObjectError(ObjectError objectError)
		{
			this.objectError = objectError;
			return this;
		}

		public ErrorResponseDTO build()
		{
			final ErrorResponseDTO dto = new ErrorResponseDTO();

			if(Optional.ofNullable(objectError).isPresent())
			{

				FieldError fieldError = (FieldError) objectError;

				String fieldName = fieldError.getField();
				String errorMessage = fieldError.getDefaultMessage();

				dto.message = String.format("%s: %s", fieldName, errorMessage);
			}
			else
			{
				dto.message = message;
			}

			return dto;
		}

	}
}
