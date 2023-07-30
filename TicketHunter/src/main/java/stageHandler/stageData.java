package stageHandler;
import java.util.*;

public class stageData {
	stageData(String stageName)
	{
		this.stageName = stageName;
	}
	
	
	
	private
		String stageName;
		HashMap<String, List<List<String>>> nhaVanHoaThanhNien = new HashMap<String, List<List<String>>>();
}
