package com.xiaomi.nlp.util.regular;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dy on 15-11-6.
 */
public class RegexListenerImpl implements RegexListener {
    //<?XX>=..
    ArrayList<RE_SEG> res = new ArrayList<RE_SEG>();

    boolean hasOuterQuant = false;
    boolean getReGroup = false;
    boolean getReGroupDown = false;
    boolean getSTag = false;
    boolean getDotWild = false;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (RE_SEG re_seg: res) sb.append(re_seg.toString());
        return sb.toString();
    }

    boolean isValid() {
        return !(getReGroup || getReGroupDown || getSTag || getDotWild);
    }

    @Override
    public void enterWildcard(RegexParser.WildcardContext ctx) {
        //due to they are last visited, so add them safely
        if (!isValid()) return;
        if (res.size() > 0 && res.get(res.size() - 1) instanceof RE_LITERALS)
            ((RE_LITERALS)res.get(res.size() - 1)).literals.append(ctx.getText());
        else res.add(new RE_LITERALS(new StringBuffer(ctx.getText())));
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
        getSTag = true;
        res.add(new S_TAG(ctx.s_tag_name().getText()));
    }

    @Override
    public void exitS_tag(RegexParser.S_tagContext ctx) {
        getSTag = false;
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
        if (!isValid()) return;
        String text = ctx.getText();
        //".*" cannot be translate to "<*>" in the context of either re_group or re_class
        if (text.equals(".*")) {
            getDotWild = true;
            res.add(new RE_WILD());
        }
        else if (ctx.re_factor() != null && ctx.re_factor().re_group() != null && ctx.re_quant() != null) {
            hasOuterQuant = true;
        }
    }

    @Override
    public void exitRe_seq_elem(RegexParser.Re_seq_elemContext ctx) {
        if (!getReGroup && getReGroupDown && ctx.re_quant() != null) {
            getReGroupDown = false;
            getReGroup = false;
            ((RE_GROUP)res.get(res.size() - 1)).setQuant(ctx.re_quant().getText());
            return;
        }
        if (getDotWild) {
            getDotWild = false;
            return;
        }
        //normal re_group or re_class
        if (ctx.re_quant() != null) {
            if (!(res.get(res.size() - 1) instanceof RE_WILD)) res.add(new RE_WILD());
            return;
        }
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

    RegexParser.Re_groupContext re_group_extr = null;
    @Override
    public void enterRe_group(RegexParser.Re_groupContext ctx) {
        //identify the "((xx)|(zz)|..(yy))"
        if (!isValid()) return;
        if (ctx.re_choice_no_lb() != null && ctx.re_choice_no_lb().re_or() != null
                && ctx.re_choice_no_lb().re_seq_no_lb() != null
                && (hasOuterQuant ||
                    ctx.re_choice_no_lb().re_or().size() >= 1
                    && ctx.re_choice_no_lb().re_or().size() + 1 == ctx.re_choice_no_lb().re_seq_no_lb().size()
                )) {
            getReGroup = true;
            re_group_extr = ctx;
            StringBuffer sb = new StringBuffer();
            for (RegexParser.Re_seq_no_lbContext e : ctx.re_choice_no_lb().re_seq_no_lb()) {
                if (sb.length() != 0) sb.append("|");
                sb.append(e.getText().substring(1, e.getText().length() - 1));
            }
            RE_GROUP re_group = new RE_GROUP();
            re_group.setChoice(sb.toString());
            res.add(re_group);
        }
    }

    @Override
    public void exitRe_group(RegexParser.Re_groupContext ctx) {
        if (re_group_extr == ctx) {
            re_group_extr = null;
            getReGroupDown = true;
            getReGroup = false;
            return;
        }
        if (getReGroupDown && ctx.re_choice_no_lb() != null && ctx.re_choice_no_lb().re_seq_no_lb() != null
                && ctx.re_choice_no_lb().re_seq_no_lb().size() == 1
                && ctx.re_choice_no_lb().re_seq_no_lb().get(0).re_seq_elem_no_lb().size() >= 2
                ) {
            int cnt = ctx.re_choice_no_lb().re_seq_no_lb().get(0).re_seq_elem_no_lb().size();
            if (cnt >= 3) ((RE_GROUP)res.get(res.size() - 1)).setLeft_margin(ctx.re_choice_no_lb().re_seq_no_lb().get(0).re_seq_elem_no_lb().get(0).getText());
            if (cnt >= 2) ((RE_GROUP)res.get(res.size() - 1)).setRight_margin(ctx.re_choice_no_lb().re_seq_no_lb().get(0).re_seq_elem_no_lb().get(cnt - 1).getText());
            return;
        }

        if (!isValid()) return;

        //normal re_group
        if (!getReGroup) {
            res.add(new RE_LITERALS(new StringBuffer(ctx.getText())));
            return;
        }
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
