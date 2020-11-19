package ghadban.mariam.mariamproject.Data;

public class MyTask
{
    private String key;
    private String title;
    private String subject;
    private int important;
    private int necessary;
    private String owner;

    public MyTask(){
    }

    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public int getImportant(){
        return  important;
    }

    public void setImportant(int important){
        this.important = important;
    }

    public int getNecessary(){
        return  necessary;
    }

    public void setNecessary(int necessary){
        this.necessary = necessary;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String owner){
        this.owner = owner;
    }
}
