package com.hersa.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.hersa.app.bom.filestore.FileStore;
import com.hersa.app.dao.filestore.FileStoreCreateException;

public interface FileStoreInterfaces {

	void saveVideo(FileStore store, InputStream inputStream) throws IOException, FileStoreCreateException;

	File getVideoFileInputStream(String id) throws FileNotFoundException;

}