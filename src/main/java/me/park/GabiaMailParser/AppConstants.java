package me.park.GabiaMailParser;

public class AppConstants {

    // DB 연결 정보
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/mail?serverTimezone=UTC";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "1234";
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_CONNECTION_ERROR = "DB 연결에 실패하였습니다.\n프로그램을 종료합니다.";

    // 메일 서버 정보
    public static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    public static final String POPSERVER = "pop3s.hiworks.com";
    public static final String SMTPSERVER = "smtps.hiworks.com";
    public static final String SMTPPORT = "465";

    //스케쥴러
    public static final int RELOAD_TIME = 10;
    public static final String NEW_MAIL = "개의 새로운 메일이 등록되었습니다.";
    public static final String NEW_MAIL_TITLE = "가비아 메일 알림";

    // 메일 목록 field
    public static final String MAIL_TITLE = "제목";
    public static final String MAIL_FROM = "보낸사람";
    public static final String MAIL_DATE = "보낸날짜";
    public static final String MAIL_IMPORTANT = "중요메일";

    // 로그인 화면
    public static final String LOGIN_TITLE = "로그인";
    public static final String LOGIN_USERNAME = "아이디(가비아 계정)";
    public static final String LOGIN_PASSWORD = "비밀번호";
    public static final String LOGIN = "로그인";
    public static final String LOGIN_FAIL = "로그인에 실패하였습니다.";

    // 목록 화면
    public static final String MAINVIEW_TITLE = "메일 목록";
    public static final String MAILVIEW_FIND = "제목으로 검색";
    public static final String MAILVIEW_WRITE = "메일 쓰기";
    public static final String MAILVIEW_TEMP_UPDATE = "메일 템플릿 수정";
    public static final String IMPORTANT_FAIL = "중요 메일 수정 과정에서 오류가 발생했습니다.";
    public static final String TOGGLE_ALL = "전체 메일";
    public static final String TOGGLE_IMPORTANT = "중요 메일";

    // 메일 전송 화면
    public static final String MAILSENDVIEW_TITLE = "메일 쓰기";
    public static final String MAILSENDVIEW_SENDBUTTON = "전송";
    public static final String EXITBUTTON = "취소";
    public static final String MAILSENDVIEW_RECEIVER = "받는 사람";
    public static final String MAILSENDVIEW_MAILTITLE = "제목";
    public static final String MAILSENDVIEW_CONTENT = "내용";
    public static final String SENDMAIL_FAIL = "메일 형식에 맞춰서 입력해주세요";
    public static final String TITLE_FAIL = "메일 제목을 입력해주세요.";
    public static final String CONTENT_FAIL = "메일 내용을 입력해주세요.";
    public static final String MAILSEND_SUCCESS = "메일 발송에 성공했습니다.";
    public static final String MAILSEND_FAIL = "메일 발송에 실패했습니다.";

    // 템플릿 수정 화면
    public static final String TEMPLATEVIEW_TITLE = "템플릿 수정";
    public static final String TEMPLATEVIEW_UPDATEBUTTON = "저장";
    public static final String TEMPLATEVIEW_LABEL = "메일을 작성할 때 사용할 템플릿을 등록하세요.";
    public static final String TEMPLATEVIEW_UPDATESUCCESS = "템플릿을 수정하였습니다.";
    public static final String TEMPLATEVIEW_UPDATEFAIL = "템플릿을 수정하는 과정에서 오류가 발생했습니다.";

}
