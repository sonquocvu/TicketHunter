package main;
import browserHandler.ChromeHandler;
import browserHandler.BrowserHandler;

public class Main {

	public static void main(String[] args) {
		BrowserHandler chrome = new ChromeHandler("nhaVanHoaThanhNien", "M", 
									"https://ticketbox.vn/event/kich-thanh-xa-bach-xa-86983?opm=tbox.searchlistcategory.list.7",
									"Vu Quoc", "Son", "vuquocson1995@gmail.com", "0979093748");
		chrome.start();
	}
}
