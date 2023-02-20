package com.nowon.green.domain.dto.bus.station;

import lombok.Data;

@Data
public class ComMsgHeader {
	
	private String errMsg;
	private String responseMsgID;
	private String responseTime;
	private String requestMsgID;
	private String successYN;
	private String returnCode;

}
