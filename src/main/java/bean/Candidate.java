package bean;

import java.util.Date;

public class Candidate {

    private int CandidateID;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private Date Birthday;
    private String Birthplace;
    private String Gender;
    private int EducationalBGID;
    private int ElectionListID;

    public int getCandidateID() {
        return CandidateID;
    }

    public void setCandidateID(int CandidateID) {
        this.CandidateID = CandidateID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String MiddleName) {
        this.MiddleName = MiddleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public String getBirthplace() {
        return Birthplace;
    }

    public void setBirthplace(String Birthplace) {
        this.Birthplace = Birthplace;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public int getEducationalBGID() {
        return EducationalBGID;
    }

    public void setEducationalBGID(int EducationalBGID) {
        this.EducationalBGID = EducationalBGID;
    }

    public int getElectionListID() {
        return ElectionListID;
    }

    public void setElectionListID(int ElectionListID) {
        this.ElectionListID = ElectionListID;
    }
}
