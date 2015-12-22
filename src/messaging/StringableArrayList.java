package messaging;

import java.util.StringTokenizer;

public class StringableArrayList<E extends Stringable> extends Stringable
{

	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fromString(String str)
	{
		StringTokenizer st = new StringTokenizer(str.substring(1, str.length() - 1), ", ");


		StringableBoolean sb = new StringableBoolean();
		sb.fromString(st.nextToken());
		if(sb != null) {

		}
	}

}
