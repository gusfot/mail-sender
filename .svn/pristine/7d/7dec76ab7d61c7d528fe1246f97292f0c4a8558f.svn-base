package com.wadiz.client.common;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.wadiz.client.ibk.batch.model.TbIbkInvestIfInfo;
import com.wadiz.client.ibk.batch.model.WDataList;
import com.wadiz.client.ibk.batch.model.WMessage;
import com.wadiz.client.ibk.batch.parser.IbkParser;
import com.wadiz.client.ibk.batch.parser.InvestIfInfoParser;
import com.wadiz.client.ibk.common.config.IbkClientConfig;

public class FileUtilTest {

	@Test
	public void testMove() throws IOException {
		String fileName = "D:/com.wadiz.client.ibk/cbk_agn_mngm_scur_0300_20160120_day";
		Path oldFile = Paths.get(fileName);
		String completedFileName = "D:/com.wadiz.client.ibk/send.done";
		Path movePath = Paths.get(completedFileName);
		Files.move(oldFile , movePath .resolve(oldFile.getFileName()));
	}
}
