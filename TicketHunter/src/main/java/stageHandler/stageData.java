package stageHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.*;


public class stageData {
	public stageData()
	{
		this.m_stageJsonPath = "data\\stages.json";
	}
	
	public stageData(String stageName, String rowName)
	{
		this.m_stageName = stageName;
		this.m_rowName = rowName;
		this.m_stageJsonPath = "data\\stages.json";
		loadStageData();
	}
	
	public Map<String, List<List<String>>> getStageDataMap()
	{
		return this.m_stageDataMap;
	}
	
	public Map<String, List<List<String>>> getStageDataMap(String stageName)
	{
		this.m_stageName = stageName;
		loadStageData();
		return this.m_stageDataMap;
	}
	
	public List<List<String>> getRowList()
	{
		return this.m_stageDataMap.get(this.m_rowName);
	}
	
	public List<List<String>> getRowList(String stageName, String rowName)
	{
		getStageDataMap(stageName);
		return this.m_stageDataMap.get(rowName);
	}
	
	@SuppressWarnings("unchecked")
	private void loadStageData()
	{
		try
		{
			// Get appropriate stage
			byte[] stageJsonData = Files.readAllBytes(Paths.get(this.m_stageJsonPath));
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode stages = objectMapper.readTree(stageJsonData);
			JsonNode stage = stages.path(this.m_stageName);
			
			// Convert Json stage to Map
			this.m_stageDataMap = new HashMap<String, List<List<String>>>();
			this.m_stageDataMap = objectMapper.convertValue(stage, HashMap.class);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private
		String m_stageName;
		String m_rowName;
		final String m_stageJsonPath;
		Map<String, List<List<String>>> m_stageDataMap;
}
