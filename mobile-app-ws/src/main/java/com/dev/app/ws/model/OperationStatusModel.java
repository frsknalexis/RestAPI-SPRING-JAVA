package com.dev.app.ws.model;

import java.io.Serializable;

public class OperationStatusModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8850541171835636963L;

	private String operationResult;
	
	private String operationName;

	public OperationStatusModel() {
		
	}

	public String getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
}
