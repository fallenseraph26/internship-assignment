import java.util.*;
public class Priority 
{
	static int n,l=-1;
	static String event[];
	static String queue[];
	static double gpa[];
	static int token[];
	public static void sort()
	{
        for(int i=0; i < l; i++)
        {  
            for(int j=1; j < (l-i); j++)
            {  
               if(gpa[j-1] > gpa[j])
               { 
                    double temp = gpa[j-1];
                    gpa[j-1] = gpa[j];
                    gpa[j] = temp;
                    String temp1=queue[j-1];
                	queue[j-1]=queue[j];
                	queue[j]=temp1;
                    int temp2=token[j-1];
                    token[j-1]=token[j];
                    token[j]=temp2;
                }
            }
        }
	}
	public static void input()
	{
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt()+1;
		event=new String[n];
		queue=new String[n];
		gpa=new double[n];
		token=new int[n];
		for(int i=0;i<n;i++)
			event[i]=sc.nextLine();
	}
	public static void process()
	{
		for(int i=0;i<n;i++)
		{
			int k=0,E=0;//s=0;
			for(int j=0;j<event[i].length();j++)
			{
				if(event[i].charAt(j)==' ')
				{
					//s=0;
					if(E==0 && event[i].substring(k, j).equals("ENTER"))
							{
								k=j+1;
								E++;
								l++;
							}
					else
					{
						if(E==1)
							queue[l]=event[i].substring(k,j+1);
						else
						{
							gpa[l]=Double.parseDouble(event[i].substring(k,j));
							token[l]=Integer.parseInt(event[i].substring(j+1));
							if(l>1 && gpa[l]<gpa[l-1])
								sort();
						}
						k=j+1;
						E++;
					}
				}
					else if(event[i].substring(k).equals("SERVED"))
					{
						sort();
						if(gpa[l]==gpa[l-1])
						{
							int c=queue[l].compareTo(queue[l-1]);
							if(c<0)
								l--;
							else if(c>0)
							{
								String temp=queue[l];
								queue[l]=queue[l-1];
								queue[l-1]=temp;
								int temp1=token[l];
								token[l]=token[l-1];
								token[l-1]=temp1;
								l--;
							}
							else
							{
								if(token[l]<token[l-1])
									l--;
								else
								{
									int temp1=token[l];
									token[l]=token[l-1];
									token[l-1]=temp1;
									l--;
								}
							}
						}
						else
							l--;
						break;
					}
					else
						continue;
				}
			
		}
	}
	public static void display()
	{
		if(l>=0)
		{
			while(l!=-1)
			{
				System.out.println(queue[l]);
				l--;
			}
		}
		else
			System.out.println("Empty");
	}

	public static void main(String args[])
	{
		input();
		process();
		display();
	}
}