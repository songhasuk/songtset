package com.nowon.green.domain.dto.bus.station;

import lombok.Data;

@Data
public class StationByRoute {
	
	private ComMsgHeader comMsgHeader;
	private MsgHeader msgHeader;
	private MsgBody msgBody;
}
