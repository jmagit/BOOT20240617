package com.example.application.endpoints;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapFaultException;

import com.example.webservices.schemas.calculator.AddRequest;
import com.example.webservices.schemas.calculator.AddResponse;
import com.example.webservices.schemas.calculator.DivideRequest;
import com.example.webservices.schemas.calculator.DivideResponse;
import com.example.webservices.schemas.calculator.MultiplyRequest;
import com.example.webservices.schemas.calculator.MultiplyResponse;
import com.example.webservices.schemas.calculator.SubtractRequest;
import com.example.webservices.schemas.calculator.SubtractResponse;

@Endpoint
public class CalculatorEndpoint {
	private static final String NAMESPACE_URI = "http://example.com/webservices/schemas/calculator";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRequest")
	@ResponsePayload
	public AddResponse add(@RequestPayload AddRequest request) {
		var result = new AddResponse();
		result.setAddResult(redondea(request.getOp1() + request.getOp2()));
		return result;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "subtractRequest")
	@ResponsePayload
	public SubtractResponse subtract(@RequestPayload SubtractRequest request) {
		var result = new SubtractResponse();
		result.setSubtractResult(redondea(request.getOp1() - request.getOp2()));
		return result;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "multiplyRequest")
	@ResponsePayload
	public MultiplyResponse multiply(@RequestPayload MultiplyRequest request) {
		var result = new MultiplyResponse();
		result.setMultiplyResult(redondea(request.getOp1() * request.getOp2()));
		return result;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "divideRequest")
	@ResponsePayload
	public DivideResponse divide(@RequestPayload DivideRequest request) {
		if(request.getOp2() == 0)
//			throw new ArithmeticException("/ by zero");
			throw new SoapFaultException("Division by 0", new ArithmeticException("/ by zero")); //IllegalArgumentException();
		var result = new DivideResponse();
		result.setDivideResult(redondea(request.getOp1() / request.getOp2()));
		return result;
	}

	private double redondea(double o) {
		return (new BigDecimal(o)).setScale(16, RoundingMode.HALF_UP).doubleValue();
	}

}
