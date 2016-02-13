package ch01.ex16;

import java.io.IOException;

public class BadDataSetException extends Exception{
	private String setName;
	private IOException ioException;

	public BadDataSetException(String setName, IOException e){
		this.setName = setName;
		this.ioException = e;
	}
}
