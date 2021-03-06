package com.manage.system.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class ApplicationDTO {
	private Integer application_num;
	@NotBlank
	private String user_id;
	@NotBlank
	private String visit_place;
	@NotBlank
	private Date visit_date;
	@NotBlank
	private String visitor_num;
	@NotBlank
	private String visitor_mail;
	@NotBlank
	private String visit_reason;
	@NotBlank
	private Date application_date;
	private String application_state;
	private String refuse_reason;
	private String admin_id;
}
