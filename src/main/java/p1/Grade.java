package p1;

public class Grade {
	public static char gradePrint(int marks)
	{
		char ch;
		if(marks>90)
		{
			ch='A';
		}
		else if(marks>75&&marks<90)
		{
			ch='B';
		}
		else if(marks>60&&marks<75)
		{
			ch='C';
		}
		else if(marks>45&&marks<60)
		{
			ch='D';
		}
		else
		{
			ch='F';
		}
		return ch;
	}

}
