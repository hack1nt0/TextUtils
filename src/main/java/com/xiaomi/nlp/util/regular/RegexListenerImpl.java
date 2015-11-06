package com.xiaomi.nlp.util.regular;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dy on 15-11-6.
 */
public class RegexListenerImpl implements RegexListener {
    //<?XX>=..
    ArrayList<String> res = new ArrayList<String>();
    //<!XX>=..
    static HashMap<String, String> headTags = new HashMap<String, String>();

    boolean getReGroup = false;


    @Override
    public void enterWildcard(RegexParser.WildcardContext ctx) {
        //due to they are last visited, so add them safely
        res.add(ctx.getText());
    }

    @Override
    public void exitWildcard(RegexParser.WildcardContext ctx) {

    }

    @Override
    public void enterWildcard_no_lb(RegexParser.Wildcard_no_lbContext ctx) {

    }

    @Override
    public void exitWildcard_no_lb(RegexParser.Wildcard_no_lbContext ctx) {

    }

    @Override
    public void enterWildcard_no_mb_to(RegexParser.Wildcard_no_mb_toContext ctx) {

    }

    @Override
    public void exitWildcard_no_mb_to(RegexParser.Wildcard_no_mb_toContext ctx) {

    }

    @Override
    public void enterS(RegexParser.SContext ctx) {

    }

    @Override
    public void exitS(RegexParser.SContext ctx) {

    }

    @Override
    public void enterS_tag(RegexParser.S_tagContext ctx) {
        if (ctx.s_tag_name().getText().equals("money0")) res.add("<#m><*>.<#m>");
    }

    @Override
    public void exitS_tag(RegexParser.S_tagContext ctx) {

    }

    @Override
    public void enterS_tag_name(RegexParser.S_tag_nameContext ctx) {

    }

    @Override
    public void exitS_tag_name(RegexParser.S_tag_nameContext ctx) {

    }

    @Override
    public void enterRe_choice(RegexParser.Re_choiceContext ctx) {

    }

    @Override
    public void exitRe_choice(RegexParser.Re_choiceContext ctx) {

    }

    @Override
    public void enterRe_or(RegexParser.Re_orContext ctx) {

    }

    @Override
    public void exitRe_or(RegexParser.Re_orContext ctx) {

    }

    @Override
    public void enterRe_seq(RegexParser.Re_seqContext ctx) {

    }

    @Override
    public void exitRe_seq(RegexParser.Re_seqContext ctx) {

    }

    @Override
    public void enterRe_seq_elem(RegexParser.Re_seq_elemContext ctx) {
        String text = ctx.getText();
        //".*" cannot be translate to "<*>" in the context of either re_group or re_class
        if (text.equals(".*")) res.add("<*>");
        else if (ctx.re_factor() != null && ctx.re_factor().re_group() != null) {
            getReGroup = false;
        }
    }

    @Override
    public void exitRe_seq_elem(RegexParser.Re_seq_elemContext ctx) {
        if (getReGroup && ctx.re_quant() != null) {
            if (ctx.re_quant().getText().equals("?")) {
                String upd = res.get(res.size() - 1);
                upd = upd.substring(0, upd.length() - 1) + "|!空>";
                res.add(upd);
            }
        }
        getReGroup = false;
    }

    @Override
    public void enterRe_factor(RegexParser.Re_factorContext ctx) {

    }

    @Override
    public void exitRe_factor(RegexParser.Re_factorContext ctx) {

    }

    @Override
    public void enterRe_char(RegexParser.Re_charContext ctx) {

    }

    @Override
    public void exitRe_char(RegexParser.Re_charContext ctx) {

    }

    @Override
    public void enterRe_class(RegexParser.Re_classContext ctx) {

    }

    @Override
    public void exitRe_class(RegexParser.Re_classContext ctx) {

    }

    @Override
    public void enterRe_class_char(RegexParser.Re_class_charContext ctx) {

    }

    @Override
    public void exitRe_class_char(RegexParser.Re_class_charContext ctx) {

    }

    @Override
    public void enterRe_group(RegexParser.Re_groupContext ctx) {
        //identify the "((xx)|(zz)|..(yy))"
        if (ctx.re_choice_no_lb() == null || ctx.re_choice_no_lb().re_or() == null
                || ctx.re_choice_no_lb().re_seq_no_lb() == null
                || ctx.re_choice_no_lb().re_seq_no_lb().size() <= 1
                || ctx.re_choice_no_lb().re_or().size() + 1 != ctx.re_choice_no_lb().re_seq_no_lb().size()
                ) {
            res.add(ctx.getText());
            return;
        }
        getReGroup = true;
        StringBuffer sb = new StringBuffer();
        for (RegexParser.Re_seq_no_lbContext e: ctx.re_choice_no_lb().re_seq_no_lb()) {
            if (sb.length() != 0) sb.append("|");
            sb.append(e.getText().substring(1, e.getText().length() - 1));
        }
        String key = sb.toString();
        String value = "";
        try {
            value = ctx.re_choice_no_lb().re_seq_no_lb(0).re_seq_elem_no_lb(0).re_factor_no_lb().re_group().re_choice_no_lb().getText();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(ctx.getText());
        }
        if (!headTags.containsKey(key)) headTags.put(key, "<!" + value + ">");
        res.add(headTags.get(key));
    }

    @Override
    public void exitRe_group(RegexParser.Re_groupContext ctx) {

    }

    @Override
    public void enterRe_choice_no_lb(RegexParser.Re_choice_no_lbContext ctx) {

    }

    @Override
    public void exitRe_choice_no_lb(RegexParser.Re_choice_no_lbContext ctx) {

    }

    @Override
    public void enterRe_seq_no_lb(RegexParser.Re_seq_no_lbContext ctx) {

    }

    @Override
    public void exitRe_seq_no_lb(RegexParser.Re_seq_no_lbContext ctx) {

    }

    @Override
    public void enterRe_seq_elem_no_lb(RegexParser.Re_seq_elem_no_lbContext ctx) {

    }

    @Override
    public void exitRe_seq_elem_no_lb(RegexParser.Re_seq_elem_no_lbContext ctx) {

    }

    @Override
    public void enterRe_factor_no_lb(RegexParser.Re_factor_no_lbContext ctx) {

    }

    @Override
    public void exitRe_factor_no_lb(RegexParser.Re_factor_no_lbContext ctx) {

    }

    @Override
    public void enterRe_quant(RegexParser.Re_quantContext ctx) {

    }

    @Override
    public void exitRe_quant(RegexParser.Re_quantContext ctx) {

    }

    @Override
    public void enterInt_seq(RegexParser.Int_seqContext ctx) {

    }

    @Override
    public void exitInt_seq(RegexParser.Int_seqContext ctx) {

    }

    @Override
    public void enterEsc(RegexParser.EscContext ctx) {

    }

    @Override
    public void exitEsc(RegexParser.EscContext ctx) {

    }

    @Override
    public void enterGroup_ref(RegexParser.Group_refContext ctx) {

    }

    @Override
    public void exitGroup_ref(RegexParser.Group_refContext ctx) {

    }

    @Override
    public void enterRe_esc_char(RegexParser.Re_esc_charContext ctx) {

    }

    @Override
    public void exitRe_esc_char(RegexParser.Re_esc_charContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
