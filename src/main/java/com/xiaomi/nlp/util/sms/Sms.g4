grammar Sms;

@parser::members {

    int preBulletNum;
    int curBulletNum;

    static int bullet2Int(String bullet) {
        int p = 0, q = bullet.length() - 1;
        while (!Character.isDigit(bullet.charAt(p))) ++p;
        while (!Character.isDigit(bullet.charAt(q))) --q;
        int res = 0;
        for (int i = p; i <= q; ++i) res = res * 10 + bullet.charAt(i) - '0';
        return res;
    }

    static boolean isameBulletStyle(String a, String b) {
        a = a.trim(); b = b.trim();
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) != b.charAt(i) && !Character.isDigit(a.charAt(i)))
                return false;
        return true;
    }
}

s : smsLinear EOF;

/*
sms [String preBulletTokenText]
        @init {
        System.out.println("preBulletTokenText: " + $preBulletTokenText);
        //int preBulletNum = -1, curBulletNum = -1;
        preBulletNum = bullet2Int(preBulletTokenText);
        if (getCurrentToken().getType() == SmsParser.BULLET)
            curBulletNum = bullet2Int(getCurrentToken().getText());
        String nPreBulletTokenText = getCurrentToken().getText();
        if (getCurrentToken().getType() == SmsParser.BULLET &&
            (
            preBulletNum == 0 ||
            curBulletNum == preBulletNum + 1 &&
            isameBulletStyle(getCurrentToken().getText(), $preBulletTokenText)
            ))
            $preBulletTokenText = nPreBulletTokenText;
        }
        :
        {
        getCurrentToken().getType() == SmsParser.BULLET &&
        (
        preBulletNum == 0 ||
        curBulletNum == preBulletNum + 1 &&
        isameBulletStyle(getCurrentToken().getText(), $preBulletTokenText)
        )
        }?
        {System.out.println("get1");}
        bullet[$preBulletTokenText] ordSent sms[nPreBulletTokenText] |
        //{System.out.println("get2");}
        //BULLET ordSent sms[$preBulletTokenText] |
        {System.out.println("get3");}
        ordSent sms[$preBulletTokenText] |
        EOF
;
smsNest [int preBulletNum, String preBulletTokenText]:
        {System.out.println(bullet2int(getCurrentToken().getText()) + "==" + ($preBulletNum + 1));}
        {
        getCurrentToken().getType() == SmsParser.BULLET &&
        bullet2int(getCurrentToken().getText()) == $preBulletNum + 1 &&
        ($preBulletNum == 0 || isameBulletStyle(getCurrentToken().getText(), $preBulletTokenText))
        }?
        {if($preBulletNum == 0) {$preBulletTokenText = getCurrentToken().getText();}}
        BULLET sms[0, $preBulletTokenText] sms[$preBulletNum + 1, $preBulletTokenText] |

        {
        getCurrentToken().getType() == SmsParser.BULLET
        }?
        {
        $preBulletTokenText = getCurrentToken().getText();
        $preBulletNum = bullet2int($preBulletTokenText);
        String newPreBulletTokenText = getCurrentToken().getText();
        int newPreBulletNum = bullet2int(newPreBulletTokenText);
        }
        BULLET sms[newPreBulletNum, newPreBulletTokenText] sms[$preBulletNum, $preBulletTokenText] |

        ordSent sms[$preBulletNum, $preBulletTokenText] |
;*/

smsLinear : (bulletSent | colonSent | ordSent )*?;
//smsLinear : colonSent*?;

colonSent : token*? colonToken;

bullet : BULLET;

delimiter : DELIMITER;

bulletSent : bullet token*? delimiter;

ordSent : token*? delimiter;

colonToken: SUCHAS COLON | FULLSUCHAS | COLON;

tokens: token+;
token : WS | TAG | SUCHAS | BULLET | NUMBER | bracket | WILDCARD;
/*
bullet[String preBulletTokenText]
    @init {
        //System.out.println("preBulletTokenText: " + $preBulletTokenText);
        //int preBulletNum = -1, curBulletNum = -1;
        preBulletNum = bullet2Int(preBulletTokenText);
        if (getCurrentToken().getType() == SmsParser.BULLET)
            curBulletNum = bullet2Int(getCurrentToken().getText());
    }:
    {
    getCurrentToken().getType() == SmsParser.BULLET &&
            (
            preBulletNum == 0 ||
            curBulletNum == preBulletNum + 1 &&
            isameBulletStyle(getCurrentToken().getText(), $preBulletTokenText)
            )
    }?
    BULLET
;*/

WS: [ \t];


DELIMITER : [\r\n,.;，。；];

TAG: '<SPECIALENTITY>'|'<ZHIFUBAO>'|'<XIAOMICAIPIAO>'|'<XIAOMIDINGDAN>'|'<DUOKANTUSHUQUAN>'|'<CREDITCARDHUANKUAN>'|'<RESPONSE>'|'<TOPUP>'|'<REALNUMBER>'|'<TIMESPAN>'|'<FLOW>'|'<MONEY>'|'<BANKCARDNUMBER>'|'<EXPRESSNAME>'|'<EXPRESSNUMBER>'|'<PHONENUMBER>'|'<URL>'|'<TIME>'|'<VERIFICATIONCODE>'|'<UNKNOWN>'|'<DROP>'|
    '<FLIGHTNUMBER>' | '<AIRPORT>' | '<LOCATION>' | '<TRAIN_ORDER_NUMBER>' | '<TRAIN_NUMBER>' | '<TRAIN_STATION>' | '<TIME_START>' | '<TIME_END>' | '<AIRPORT_START>' | '<AIRPORT_DESTINATION>' | '<TRAIN_STATION_START>' | '<TRAIN_STATION_DESTINATION>' |
    '<TRAIN_SEAT>' | '<TRAIN_CARRIAGE_NUMBER>' | '<TRAIN_SEAT_NUMBER>';
FULLSUCHAS: '情况分别为';
SUCHAS: '回复以下编码办理业务'|'各上网套餐情况如下'|'如下'|'例如'|'使用情况'|'其中'|'情况'|'查询结果'|'包月套餐名称';
COLON : [:：];


NUMBER : DIGIT+ ('.' DIGIT+)? | //todo cannot be behind DIGIT
        '第' DIGIT+
        ;

DIGIT : [0-9]|[一二三四五六七八九];


BULLET : (DIGITINBRACKET|NUMBER) [,，:：、] |
        (DIGITINBRACKET|NUMBER) [.] [ \t]*? |
        (DIGITINBRACKET|NUMBER) [ ]+ |
        DIGITINBRACKET
       ;

DIGITINBRACKET : LEFTBRACKET NUMBER RIGHTBRACKET |
        NUMBER RIGHTBRACKET
        ;

LEFTBRACKET : '(' | '[' | '<' | '{' | '（' | '［' | '《' | '｛' | ']';
RIGHTBRACKET : ')' | ']' | '>' | '}' | '）' | '］' | '》' | '｝' | ']';

bracket : LEFTBRACKET | RIGHTBRACKET;

WILDCARD : .;



