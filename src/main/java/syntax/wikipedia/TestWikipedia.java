package syntax.wikipedia;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author dy[jealousing@gmail.com] on 17-7-9.
 */

public class TestWikipedia {
    public static void main(String[] args) throws Exception {
        Lexer lexer = new mediawikiLexer(CharStreams.fromString("{{Infobox book\n" +
                "| name             = 撒馬爾罕&lt;br /&gt;{{lang|fr|''Samarcande''}}\n" +
                "| image            = Samarcande de Amin Maalouf (Version chinoise traditionnelle).jpeg\n" +
                "| caption          = 中譯本封面\n" +
                "| author           = [[阿敏·馬盧夫]]\n" +
                "| title_orig       = {{lang|fr|''Samarcande''}}\n" +
                "| translator       = 黃思恩、林子涵、彭廣愷\n" +
                "| country          = {{FRA}}&lt;br /&gt;{{LBN}}\n" +
                "| language         = [[法語]]\n" +
                "| publisher        = {{flagicon|FRA}} {{link-en|JC Lattès}}&lt;br /&gt;{{flagicon|ROC}}\n" +
                "河中文化\n" +
                "| pub_date         = 1988年3月1日\n" +
                "| chinese_pub_date = 2011年2月1日\n" +
                "| pages            = 332（[[正體中文]]版）\n" +
                "| isbn             = {{ISBN|978-986-87002-1-5}}（中譯本）\n" +
                "}}\n" +
                "\n" +
                "《'''撒馬爾罕'''》（[[法語]]：{{lang|fr|''Samarcande''}}）是一部發表於1988年的[[歷史小說]]，由{{tsl|en|Lebanese people|黎巴嫩人|黎巴嫩裔}}法國作家[[阿敏·馬盧夫]]所著，故事圍繞11\n" +
                "世紀波斯詩人[[奧瑪·開儼]]和他的詩集《[[魯拜集]]》展開。小說獲得法國{{tsl|en|Prix Maison de la Presse|新聞通訊社文學獎}}&lt;ref&gt;{{cite web |url=http://www.enqueteplus.com/conte世紀波斯詩人[[奧瑪·開儼]]和他的詩集《[[魯拜集]]》展開。小說獲得法國{{tsl|en|Prix Maison d\n" +
                "e la Presse|新聞通訊社文學獎}}&lt;ref&gt;{{cite web |url=http://www.enqueteplus.com/conte\n" +
                "nt/mots-choisis-samarcande-d%E2%80%99alain-malouf-editions-latt%C3%A8s-prix-des-maisons-de-la-presse-1988 |title=Lattès   Prix des Maisons de la Presse 1988 |website=www.enqueteplus.com |accessdate=2017-07-01 |language=fr}}&lt;/ref&gt;。《撒馬爾罕》於2011年2月1日發行中譯本，由[[中華民國|臺灣]]河中文化實業有限公司出版&lt;ref&gt;{{cite web |url=http://www.transoxania.com.tw/books-1.html |title=撒馬爾罕 Samarcande |publisher=河中文化 |accessdate=2017-07-01 |language=zh-TW}}&lt;/ref&gt;。\n" +
                "\n" +
                "== 內容概述 ==\n" +
                "小說前半部份的故事背景設置於11世紀的[[波斯]]（今[[伊朗]]）和[[中亞]]，圍繞一名科學家、一\n" +
                "名哲學家和詩人[[奧瑪·開儼]]展開，並以[[塞爾柱帝國]]的歷史為背景講述了《[[魯拜集]]》的成書過程，奧瑪與帝國[[維齊爾|大臣]]{{tsl|en|Nizam al-Mulk|阿布·阿里·赫珊·伊本·阿里·圖斯}}和[[阿薩辛派]]的[[哈桑·沙巴]]之間的互動，以及他同[[撒馬爾罕]]宮廷女詩人的愛情。後半部份記述一名虛構的美國人班傑明·O·勒薩吉試圖找到《[[魯拜集]]》的原始寫本（虛構），他於1905至1907年間目睹了[[波斯立憲革命]]的歷史，最後他找到的寫本隨[[鐵達尼號]]沈入[[大西洋]]。\n" +
                "\n" +
                "== 評論 ==\n" +
                "{{tsl|en|Ahmed Rashid|艾赫梅得·拉辛德}}在英國《[[獨立報]]》上寫道：「馬盧夫的這部小說引人入勝，也是他首次將那個時代的人事物寫進小說。此書已遠遠超越一般的[[歷史小說]]，故事猶如精\n" +
                "緻刺繡的東方地毯，將貫穿數個世紀錯綜複雜的情節，與詩文、哲學、[[蘇非主義]]者的激情和[[現\n" +
                "代主義]]編織在一起&lt;ref&gt;{{Cite web|last=Rashid|first=Ahmed|date=1992-09-22|url=http://www.independent.co.uk/opinion/book-review--poetry-lovers-tricked-by-a-drowned-manuscript-samarkand--amin-maalouf-tr-russell-harris-quartet-books-pounds-1595-1552997.html|title=Poetry lovers tricked by a drowned manuscript: Samarkand|work=[[The Independent]]|accessdate=2017-07-01|language=en&lt;!-- |archiveurl=http://www.webcitation.org/633y4eObb --&gt;}}&lt;/ref&gt;。」\n" +
                "\n" +
                "== 參考資料 ==\n" +
                "{{reflist}}\n" +
                "\n" +
                "[[Category:1988年小說]]\n" +
                "[[Category:法國歷史小說]]\n" +
                "[[Category:作家題材小說]]"));

        System.out.println(lexer.getAllTokens());
        Parser parser = new mediawikiParser(new CommonTokenStream(lexer));
        ParseTree tree = ((mediawikiParser)parser).r();
        System.out.println(tree.toStringTree(parser));
    }
}
