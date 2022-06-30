package com.example.newdoctorsapp.models.doctoraddqual;

public class DoctorAddQualModel {
    public String getCertificationOrganisation() {
        return certificationOrganisation;
    }

    public void setCertificationOrganisation(String certificationOrganisation) {
        this.certificationOrganisation = certificationOrganisation;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    String certificationOrganisation;
    String qualificationName;

}
