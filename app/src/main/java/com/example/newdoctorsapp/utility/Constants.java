package com.example.newdoctorsapp.utility;

public class Constants {
    public final static String BASE_URL = "http://18.218.162.226:3000/";
     public final static String TOKEN_ID = "tokenId";
     public final static String SESSION= "session";
     public final static String ERROR= "Oops...";
    public final static int SUCCESS_STATUS = 200;
    public final static int PROFILE_STATUS = 202;
    public final static int SUCCESS_OTP = 201;
    public final static int ERROR_STATUS = 400;
    public final static String API_OTPVERIFACTION = "otpVerifaction";
    public final static String API_LOGIN = "login";
    public final static String API_CREATEDOCTOR = "CREATEDOCTOR";
    public final static String API_GETPROFILE = "GETPROFILE";
    public final static String API_ADDHOSPITAL = "HOSPITAL";
    public final static String API_ADDQULIFACTION = "QULIFACTION";
    public final static String API_ADDSESSION = "ADDSESSION";
    public final static String API_GETCITYSTATECUNTRY = "CITYSTATECUNTRY";
    public final static String API_FINDDOCTORBYBODOPART = "findDoctorBySpecialityOrBodyPart";
    public final static String API_GETHOSPITALLIST = "GETHOSPITALLIST";
    public final static String API_POSTAPPOINTMENTLIST = "POSTAPOINTMENTLIST";
    public final static String API_APPOINTMENTLIST = "APOINTMENTLIST";

    public final static String API_GETSPECIALITY = "SpecialityBodyPartAndDisease";
    public final static String API_KYCDETAIL = "KYCDETAIL";
    public final static String API_GTHOSPITALLISTBYDOCTORID = "GTHOSPITALLISTBYDOCTORID";
    public final static String API_GETTOTALEARNING = "GETTOTALEARNING";
    public final static String API_GETPENDINGAMOUNT = "GETPENDINGAMOUNT";
    public final static String API_GETAPPOINTMENTSUMMARY = "GETAPPOINTMENTSUMMARY";
    public final static String API_PROFILEUPDATE = "PROFILEUPDATE";
    public final static String API_KYCDETAILUPDATE = "KYCDETAILUPDATE";
    public final static String API_DELETEPROFILEHOSPITAL = "DELETEPROFILEHOSPITAL";
    public final static String API_DELETESPECLIZATIONANDQU = "DELETESPECLIZATIONANDQU";
    public final static String API_UPDATESCHEDULE = "UPDATESCHEDULE";
    public final static String API_UPDATEQULIFACTION = "UPDATEQULIFACTION";
    public final static String API_USERVERIFACTION = "USERVERIFACTION";
    //getSpecialityBodyPartAndDisease


    public final static String VIEW_DATE_FORMAT = "EEE, dd MMM yyyy hh:mm:ss ";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String USER_ID = "61d8299aec4e869ec162c765";

    // token user
   // public final static String TOKEN_ID = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiNWE1MzhiNGMtZTI3ZS00ZWZiLWFhMDMtMGNkY2VjMGNiZjhlIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbW9iaWxlcGhvbmUiOiI5NjUwNjE3NzIzIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvY291bnRyeSI6Iis5MSIsImp0aSI6IjE4ZjQ0MmJiLWVjZjktNDQ5MC1hOTBhLThkMjVjNTBhYWE1OCIsImV4cCI6MTYzODA3OTU5NiwiaXNzIjoiQWxhVGVjaCIsImF1ZCI6ImNhcmUzNjBDbGllbnQifQ.JhsBB2SpIWy1mmJLPnklDU-EipOiNwEb36NTP0fIhls";
  //  public final static String TOKEN_ID = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYmRmYmI5N2YtZDljOS00MWRlLTk1YWQtOTlhYTU0N2UzNDc5IiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbW9iaWxlcGhvbmUiOiI5NjUwNjE3NzIzIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvY291bnRyeSI6Iis5MSIsImp0aSI6IjU4MTZiZGMyLTg5ZjUtNGE3OC04Nzc3LTQ0MGRiZjRhNDc1ZSIsImV4cCI6MTYzOTY3MDAxNywiaXNzIjoiQWxhVGVjaCIsImF1ZCI6ImNhcmUzNjBDbGllbnQifQ.y9Eeze8k-LFzIyDLsBuVcQwB9c-pvdAb9SiIBAt5CT4";

        public final static String USER_TOKEN_ID1= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmaXJzdE5hbWUiOiJBZGl0eWEiLCJsYXN0TmFtZSI6IlJhd2F0IiwiZ2VuZGVyIjoiTWFsZSIsIkRPQiI6IjIwMjEtMTEtMzBUMTc6MTc6MTcuMDAwWiIsInBob25lTnVtYmVyIjoiODYwMTUwMDE5MCIsImVtYWlsIjoiYWttb3VyeWExOTk5QGdtYWlsLmNvbSIsInBhc3N3b3JkIjoiJDJiJDEwJGdiWVEzZkYzMTc5S0FrS3kwRi5sM2VOOUt4aUQyS2VWMHIvdC5XWE4ybGYzSXhqNkhYNnlhIiwiYWN0aXZlIjp0cnVlLCJkZWxldGVkIjpmYWxzZSwiaG9zcGl0YWxEZXRhaWxzIjpbeyJob3NwaXRhbCI6IjYxYzZlZjE1NDg5YmViOTQ0YThlZjg2OCIsImNvbnN1bHRhdGlvbkZlZSI6eyJtaW4iOjEwMCwibWF4Ijo1MDB9LCJfaWQiOiI2MWQ4Mjk5YWVjNGU4NjllYzE2MmM3NjYifV0sInJlZ2lzdHJhdGlvbiI6eyJyZWdpc3RyYXRpb25OdW1iZXIiOiI3ODkwNyIsInJlZ2lzdHJhdGlvbkNvdW5jaWwiOiJSZWdpc3RyYXRpb24gQ291bmNpbCIsInJlZ2lzdHJhdGlvbkRhdGUiOiIyMDIxLTExLTMwVDE3OjE3OjE3LjAwMFoiLCJfaWQiOiI2MWQ4Mjk5YWVjNGU4NjllYzE2MmM3NjcifSwic3BlY2lhbGl6YXRpb24iOlsiNjFiNGQ1NDQyYWYzMjkxNjNhZDI4ZGI3IiwiNjFiMTIxNjRkOGQzNjFlNTBmYmUyNmE0Il0sIktZQ0RldGFpbHMiOnsicGFuQ2FyZCI6IjIzNTY2NiIsImJhbmtOYW1lIjoiQiBOQU1FIiwiYmFua0FjY291bnROdW1iZXIiOiIxMjM0NTYiLCJJRlNDIjoiMTIzNDU3IiwiYWRoYWFyQ2FyZCI6Ijg2MDE1MjY1NTY3OCIsIl9pZCI6IjYxZDgyOTlhZWM0ZTg2OWVjMTYyYzc2OCJ9LCJxdWFsaWZpY2F0aW9uIjpbIjYxYmNjM2JmNGI5OTdhZDhmZDA4N2JjNSJdLCJfaWQiOiI2MWQ4Mjk5YWVjNGU4NjllYzE2MmM3NjUiLCJfX3YiOjAsImlkIjoiNjFkODI5OWFlYzRlODY5ZWMxNjJjNzY1IiwiaWF0IjoxNjQxNTU2Mzc4fQ.sZhc05TCeXsxVLKPoe6TlN1rSQgeNJDS5x3SdSwDmgY";

    public final static String USER_TOKEN_ID="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiTmV3IDIgSG9zcGl0YWwiLCJhZGRyZXNzIjoiNjFjNmI3N2Q3OWFiOWE3MzE3NWViYzYyIiwiZG9jdG9ycyI6WyI2MWIyMmUwNjg3NzUyYjM3MDU2ODE5NTciXSwic3BlY2lhbGlzZWRJbiI6WyI2MWI3NDI4NWY0ZTk0NmQ4NjFjMWRkMDkiXSwiYW5lbWl0eSI6WyI2MWIyZDg2YTE2Yjg4MzZhMzgyYWU5NDUiXSwidHJlYXRtZW50VHlwZSI6W10sInR5cGUiOiJHb3Zlcm5tZW50IiwicGF5bWVudCI6WyI2MWI0YTUwNDE2Y2MzZGQwOTFjNTRlZGQiLCI2MWI0YTU0ZjE2Y2MzZGQwOTFjNTRlZTciLCI2MWI0YTUxYjE2Y2MzZGQwOTFjNTRlZGYiXSwiZGVsZXRlZCI6ZmFsc2UsImNvbnRhY3ROdW1iZXIiOiI3NjY3NTc1OTgzIiwibnVtYmVyT2ZCZWQiOjc2LCJsb2NhdGlvbiI6eyJlbnVtIjpbXSwiX2lkIjoiNjFjNmI3N2Y3OWFiOWE3MzE3NWViYzY1In0sIl9pZCI6IjYxYzZiNzdmNzlhYjlhNzMxNzVlYmM2NCIsIl9fdiI6MCwiaWF0IjoxNjQwNDEzMDU1fQ.4TVMwa_4lf-ArfinzeZjSJXTf81xfDU7syOosrYMVhg";
}
