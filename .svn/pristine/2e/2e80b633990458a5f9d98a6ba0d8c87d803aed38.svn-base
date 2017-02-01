package com.wadiz.client.ibk.batch.parser;

import java.io.File;
import java.io.IOException;

import com.wadiz.client.ibk.batch.model.WDataList;
import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.batch.model.WMessageable;

public interface IbkParser {

	WMessage parse(String text);
	
	WMessage parse(File file) throws IOException;

	WMessageable parseHeader(String text);

	WDataList parseDataList(String text);

	WMessageable parseTrailer(String text);


}
