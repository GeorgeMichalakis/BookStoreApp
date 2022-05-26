
public class StringTransformer {
	
	public String AccentRemovalUpperCase(String untransformedString) {
		
		String transformedString = untransformedString.toUpperCase();
		
		transformedString = transformedString.replaceAll("¸","Å");
		transformedString = transformedString.replaceAll("¾","Õ");
		transformedString = transformedString.replaceAll("º","É");
		transformedString = transformedString.replaceAll("¼","Ï");
		transformedString = transformedString.replaceAll("¢","Á");
		transformedString = transformedString.replaceAll("¹","Ç");
		transformedString = transformedString.replaceAll("¿","Ù");
		transformedString = transformedString.replace("'", "");
		
		return transformedString;
	}

}
