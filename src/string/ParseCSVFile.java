public class ParseCSVFile {

	// some_name|some_address|some_phone|some_job
	// {name:some_name, address:some_addres,phone:some_phone, job:some_job}
	public String parseJson(String s) {
		if(s == null || "".equals(s)) return "{}";
		StringBuffer json = new StringBuffer();
		json.append("{");
		String[] items = s.splits("|");
		for(String item: items) {
			String[] cols = item.splits("_");
			json.append(cols[1]);
			json.append(":");
			json.append(item).append(",");
		}
		json.deleteCharAt(json.length()-1);
		json.append("}");
		return json.toString();
	}
}