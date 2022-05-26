/*
 * Required workaround for JComboBox
 * 
 */
public class PickAnItem {
	
    private String key;
    private String value;

    public PickAnItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}
