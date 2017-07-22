package mlearn;

import template.debug.InputReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dy[jealousing@gmail.com] on 17-5-6.
 */
public class EnglishTokenizer implements StructurePredictor {
    private final Set<String> stopwords = new HashSet<>();
    private final String SPACE = "\t\n ";
    private static EnglishTokenizer instance;

    private EnglishTokenizer() {
        InputReader in = null;
        try {
            in = new InputReader(new FileInputStream("src/resources/stopwords/english.txt"));
            in.readLine();
            while (!in.isExhausted()) stopwords.add(in.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static EnglishTokenizer asInstance() {
        if (instance == null) instance = new EnglishTokenizer();
        return instance;
    }

    @Override
    public List<String> predict(String txt) {
        throw new UnsupportedOperationException();
    }

    public List<String> chinese(String txt) {
        throw new RuntimeException();
    }

    public List<String> english(String txt) {
        return removeStopwords(cut(txt));
        //return cut(txt);
    }

    public List<String> cut(String txt) {
        List<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (char c : txt.toCharArray()) {
            if (SPACE.indexOf(c) < 0) word.append(c);
            else if (word.length() > 0) {
                words.add(word.toString());
                word.setLength(0);
            }
        }
        if (word.length() > 0) words.add(word.toString());
        return words;
    }

    public List<String> removeStopwords(List<String> words) {
        List<String> after = new ArrayList<>();
        for (String w : words) if (!stopwords.contains(w)) after.add(w);
        return after;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //String str = "a,a's,able,about,above,according,accordingly,across,actually,after,afterwards,again,against,ain't,all,allow,allows,almost,alone,along,already,also,although,always,am,among,amongst,an,and,another,any,anybody,anyhow,anyone,anything,anyway,anyways,anywhere,apart,appear,appreciate,appropriate,are,aren't,around,as,aside,ask,asking,associated,at,available,away,awfully,b,be,became,because,become,becomes,becoming,been,before,beforehand,behind,being,believe,below,beside,besides,best,better,between,beyond,both,brief,but,by,c,c'mon,c's,came,can,can't,cannot,cant,cause,causes,certain,certainly,changes,clearly,co,com,come,comes,concerning,consequently,consider,considering,contain,containing,contains,corresponding,could,couldn't,course,currently,d,definitely,described,despite,did,didn't,different,do,does,doesn't,doing,don't,done,down,downwards,during,e,each,edu,eg,eight,either,else,elsewhere,enough,entirely,especially,et,etc,even,ever,every,everybody,everyone,everything,everywhere,ex,exactly,example,except,f,far,few,fifth,first,five,followed,following,follows,for,former,formerly,forth,four,from,further,furthermore,g,get,gets,getting,given,gives,go,goes,going,gone,got,gotten,greetings,h,had,hadn't,happens,hardly,has,hasn't,have,haven't,having,he,he's,hello,help,hence,her,here,here's,hereafter,hereby,herein,hereupon,hers,herself,hi,him,himself,his,hither,hopefully,how,howbeit,however,i,i'd,i'll,i'm,i've,ie,if,ignored,immediate,in,inasmuch,inc,indeed,indicate,indicated,indicates,inner,insofar,instead,into,inward,is,isn't,it,it'd,it'll,it's,its,itself,j,just,k,keep,keeps,kept,know,known,knows,l,last,lately,later,latter,latterly,least,less,lest,let,let's,like,liked,likely,little,look,looking,looks,ltd,m,mainly,many,may,maybe,me,mean,meanwhile,merely,might,more,moreover,most,mostly,much,must,my,myself,n,name,namely,nd,near,nearly,necessary,need,needs,neither,never,nevertheless,new,next,nine,no,nobody,non,none,noone,nor,normally,not,nothing,novel,now,nowhere,o,obviously,of,off,often,oh,ok,okay,old,on,once,one,ones,only,onto,or,other,others,otherwise,ought,our,ours,ourselves,out,outside,over,overall,own,p,particular,particularly,per,perhaps,placed,please,plus,possible,presumably,probably,provides,q,que,quite,qv,r,rather,rd,re,really,reasonably,regarding,regardless,regards,relatively,respectively,right,s,said,same,saw,say,saying,says,second,secondly,see,seeing,seem,seemed,seeming,seems,seen,self,selves,sensible,sent,serious,seriously,seven,several,shall,she,should,shouldn't,since,six,so,some,somebody,somehow,someone,something,sometime,sometimes,somewhat,somewhere,soon,sorry,specified,specify,specifying,still,sub,such,sup,sure,t,t's,take,taken,tell,tends,th,than,thank,thanks,thanx,that,that's,thats,the,their,theirs,them,themselves,then,thence,there,there's,thereafter,thereby,therefore,therein,theres,thereupon,these,they,they'd,they'll,they're,they've,think,third,this,thorough,thoroughly,those,though,three,through,throughout,thru,thus,to,together,too,took,toward,towards,tried,tries,truly,try,trying,twice,two,u,un,under,unfortunately,unless,unlikely,until,unto,up,upon,us,use,used,useful,uses,using,usually,uucp,v,value,various,very,via,viz,vs,w,want,wants,was,wasn't,way,we,we'd,we'll,we're,we've,welcome,well,went,were,weren't,what,what's,whatever,when,whence,whenever,where,where's,whereafter,whereas,whereby,wherein,whereupon,wherever,whether,which,while,whither,who,who's,whoever,whole,whom,whose,why,will,willing,wish,with,within,without,won't,wonder,would,wouldn't,x,y,yes,yet,you,you'd,you'll,you're,you've,your,yours,yourself,yourselves,z,zero";
        String str = "、,。,〈,〉,《,》,一,一切,一则,一方面,一旦,一来,一样,一般,七,万一,三,上下,不仅,不但,不光,不单,不只,不如,不怕,不惟,不成,不拘,不比,不然,不特,不独,不管,不论,不过,不问,与,与其,与否,与此同时,且,两者,个,临,为,为了,为什么,为何,为着,乃,乃至,么,之,之一,之所以,之类,乌乎,乎,乘,九,也,也好,也罢,了,二,于,于是,于是乎,云云,五,人家,什么,什么样,从,从而,他,他人,他们,以,以便,以免,以及,以至,以至于,以致,们,任,任何,任凭,似的,但,但是,何,何况,何处,何时,作为,你,你们,使得,例如,依,依照,俺,俺们,倘,倘使,倘或,倘然,倘若,借,假使,假如,假若,像,八,六,兮,关于,其,其一,其中,其二,其他,其余,其它,其次,具体地说,具体说来,再者,再说,冒,冲,况且,几,几时,凭,凭借,则,别,别的,别说,到,前后,前者,加之,即,即令,即使,即便,即或,即若,又,及,及其,及至,反之,反过来,反过来说,另,另一方面,另外,只是,只有,只要,只限,叫,叮咚,可,可以,可是,可见,各,各个,各位,各种,各自,同,同时,向,向着,吓,吗,否则,吧,吧哒,吱,呀,呃,呕,呗,呜,呜呼,呢,呵,呸,呼哧,咋,和,咚,咦,咱,咱们,咳,哇,哈,哈哈,哉,哎,哎呀,哎哟,哗,哟,哦,哩,哪,哪个,哪些,哪儿,哪天,哪年,哪怕,哪样,哪边,哪里,哼,哼唷,唉,啊,啐,啥,啦,啪达,喂,喏,喔唷,嗡嗡,嗬,嗯,嗳,嘎,嘎登,嘘,嘛,嘻,嘿,四,因,因为,因此,因而,固然,在,在下,地,多,多少,她,她们,如,如上所述,如何,如其,如果,如此,如若,宁,宁可,宁愿,宁肯,它,它们,对,对于,将,尔后,尚且,就,就是,就是说,尽,尽管,岂但,己,并,并且,开外,开始,归,当,当着,彼,彼此,往,待,得,怎,怎么,怎么办,怎么样,怎样,总之,总的来看,总的来说,总的说来,总而言之,恰恰相反,您,慢说,我,我们,或,或是,或者,所,所以,打,把,抑或,拿,按,按照,换句话说,换言之,据,接着,故,故此,旁人,无宁,无论,既,既是,既然,时候,是,是的,替,有,有些,有关,有的,望,朝,朝着,本,本着,来,来着,极了,果然,果真,某,某个,某些,根据,正如,此,此外,此间,毋宁,每,每当,比,比如,比方,沿,沿着,漫说,焉,然则,然后,然而,照,照着,甚么,甚而,甚至,用,由,由于,由此可见,的,的话,相对而言,省得,着,着呢,矣,离,第,等,等等,管,紧接着,纵,纵令,纵使,纵然,经,经过,结果,给,继而,综上所述,罢了,者,而,而且,而况,而外,而已,而是,而言,能,腾,自,自个儿,自从,自各儿,自家,自己,自身,至,至于,若,若是,若非,莫若,虽,虽则,虽然,虽说,被,要,要不,要不是,要不然,要么,要是,让,论,设使,设若,该,诸位,谁,谁知,赶,起,起见,趁,趁着,越是,跟,较,较之,边,过,还是,还有,这,这个,这么,这么些,这么样,这么点儿,这些,这会儿,这儿,这就是说,这时,这样,这边,这里,进而,连,连同,通过,遵照,那,那个,那么,那么些,那么样,那些,那会儿,那儿,那时,那样,那边,那里,鄙人,鉴于,阿,除,除了,除此之外,除非,随,随着,零,非但,非徒,靠,顺,顺着,首先,︿,！,＃,＄,％,＆,（,）,＊,＋,，,０,１,２,３,４,５,６,７,８,９,：,；,＜,＞,？,＠,［,］,｛,｜,｝,～,￥";
        PrintWriter out = new PrintWriter(new FileOutputStream("src/resources/stopwords/chinese.txt"));
        for (String s : str.split(",")) out.println(s);
        out.close();
    }

}
