package com.wadiz.client.ibk.msg;

import com.wadiz.client.ibk.model.WMessage;

public interface MessageGenerator {

	boolean generate(WMessage msg, String date);

}
