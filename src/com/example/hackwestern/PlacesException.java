package com.example.hackwestern;

public class PlacesException extends RuntimeException {

	private static final long serialVersionUID = -7551318600432160726L;

	public PlacesException( Throwable t ) {
		super( t );
	}
}