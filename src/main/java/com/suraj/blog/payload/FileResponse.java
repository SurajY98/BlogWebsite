package com.suraj.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author suraj.yadav
 * @created on Nov 5, 2023
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {

	private String fileName;
	private String message;
}
