package com.wadiz.client.ibk.batch.msg;

import com.wadiz.client.ibk.batch.model.WMessage;

public interface MessageReader {

	boolean read(WMessage msg, String type);

}
