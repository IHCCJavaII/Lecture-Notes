public enum ResidenceHall {
    APPANOOSE(4, true),
    KEOKUK_MAHASKA(5, true),
    OAK(2, false),
    TRUSTEE(2, false),
    WAPELLO(2, true);

     final int studentsPerDorm;

     final boolean apartmentStyle;

    ResidenceHall(int studentsPerDorm, boolean apartmentStyle){
        this.studentsPerDorm = studentsPerDorm;
        this.apartmentStyle = apartmentStyle;
    }
}
