public class Student {
    private String fullName;
    private String studentId;
    private boolean isUndergraduate;
    private int level;
    private int modulesRegistered;
    private boolean isInternational;
    private int tutoringHours;

    public Student(){}

    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getStudentId(){
        return studentId;
    }
    public void setStudentId(String studentId){
        this.studentId = studentId;
    }

    public boolean isUndergraduate() {
        return isUndergraduate;
    }
    public void setUndergraduate(boolean undergraduate) {
        isUndergraduate = undergraduate;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }


    public int getModulesRegistered() {
        return modulesRegistered;
    }
    public void setModulesRegistered(int modulesRegistered) {
        this.modulesRegistered = modulesRegistered;
    }


    public boolean isInternational() {
        return isInternational;
    }
    public void setInternational(boolean international) {
        isInternational = international;
    }


    public int getTutoringHours() {
        return tutoringHours;
    }
    public void setTutoringHours(int tutoringHours) {
        if(tutoringHours>10){
            this.tutoringHours=10;
        }else{
            this.tutoringHours = tutoringHours;
        }
    }
}
