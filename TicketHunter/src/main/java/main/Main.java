package main;
import browserHandler.ChromeHandler;
import browserHandler.BrowserHandler;

public class Main {

	public static void main(String[] args) {
		BrowserHandler chrome = new ChromeHandler();
		chrome.start();

	}

}
