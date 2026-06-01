package ro.ulbs.proiectaresoftware.lab1;

public class Main {
    public static void main(){
        String a="";
        for(char c='a';c<'z';c++)
        {
            a=a+c;
        }
        System.out.println(a.toLowerCase());
        System.out.println(a.toUpperCase());
        String vocale="aeiou";
        String array[]=new String[5];
        int index=-1;
        for(int i=0;i<a.length();i++)
        {
            char c=a.charAt(i);
            if(vocale.indexOf(c)!=-1)
            {
                index++;
                array[index]="";
            }
            array[index]+=c;
        }
        for(int i=0;i<5;i++)
        {
            System.out.println(array[i]);
        }
    }
}