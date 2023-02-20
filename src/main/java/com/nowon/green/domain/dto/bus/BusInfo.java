package com.nowon.green.domain.dto.bus;

import lombok.Data;

@Data
public class BusInfo {
	ComMsgHeader comMsgHeader;
	MsgHeader msgHeader;
	MsgBody msgBody;
}
