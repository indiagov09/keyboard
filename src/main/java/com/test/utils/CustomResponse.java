package com.test.utils;
import java.util.*;

import java.util.stream.*;

import org.springframework.http.HttpStatus;

import lombok.Data;

import java.util.function.*;
@Data
public class CustomResponse {
private Object data;

public CustomResponse() {
}

public CustomResponse(Object data) {
	this.data = data;
}



}
