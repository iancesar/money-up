package br.com.moneyup.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import br.com.moneyup.entity.enumerate.TransactionType;
import br.com.moneyup.util.Formatter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionsDTO implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	private Long					id;

	private String					title;

	private BigDecimal			price;

	private TransactionType		type;

	private Boolean				consolidated;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate				date;

	public String getPriceFormatted()
	{
		String price = Formatter.toCurrency(this.price);
		if(type.equals(TransactionType.EXPENSE))
		{
			return String.format("- %s", price);
		}
		else
		{
			return String.format("+ %s", price);
		}
	}

}
