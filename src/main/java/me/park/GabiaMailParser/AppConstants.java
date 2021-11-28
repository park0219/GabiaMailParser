package me.park.GabiaMailParser;

public class AppConstants {

    // jdbc
    public static final String JDBC_URL = "jdbc:mysql://192.168.0.148:3306/mail?serverTimezone=UTC";
    public static final String JDBC_USERNAME = "mail";
    public static final String JDBC_PASSWORD = "1234";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // mail
    public static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    public static final String POPSERVER = "pop3s.hiworks.com";
    public static final String SMTPSERVER = "smtps.hiworks.com";
    public static final String SMTPPORT = "465";

    //스케쥴러
    public static final int RELOAD_TIME = 10 * 60 * 1000;
    public static final String NEW_MAIL = "개의 새로운 메일이 등록되었습니다.";
    public static final String NEW_MAIL_TITLE = "가비아 메일 알림";

    // 메일 목록 field
    public static final String MAIL_TITLE = "제목";
    public static final String MAIL_FROM = "보낸사람";
    public static final String MAIL_DATE = "보낸날짜";
    public static final String MAIL_IMPORTANT = "중요메일";

    // login view
    public static final String LOGIN_TITLE = "가비아 계정으로 로그인하기";
    public static final String LOGIN_USERNAME = "아이디";
    public static final String LOGIN_PASSWORD = "비밀번호";
    public static final String LOGIN = "로그인";
    public static final String LOGIN_FAIL = "로그인에 실패하였습니다.";

    // main view
    public static final String MAINVIEW_TITLE = "메일 목록";
    public static final String MAILVIEW_FIND = "제목으로 검색";
    public static final String MAILVIEW_WRITE = "메일 쓰기";
    public static final String MAILVIEW_TEMP_UPDATE = "메일 템플릿 수정";
    public static final String IMPORTANT_FAIL = "중요 메일 수정 과정에서 오류가 발생했습니다.";

    // add view
    public static final String ADDVIEW_TITLE = "添加学生信息";
    public static final String ADDVIEW_ADDBUTTON = "添加";
    public static final String EXITBUTTON = "退出";

    // delete view
    public static final String DELETEVIEW_TITLE = "删除学生信息";
    public static final String DELETEVIEW_DELETEBUTTON = "删除";

    // update view
    public static final String UPDATEVIEW_TITLE = "更新学生信息";
    public static final String UPDATEVIEW_UPDATEBUTTON = "更新";

}
