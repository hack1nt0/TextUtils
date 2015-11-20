package com.xiaomi.nlp.util.regular;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

/**
 * Created by dy on 15-11-6.
 */
public class RegexListenerImpl implements RegexListener {
    //<?XX>=..
    ArrayList<RE_SEG> res = new ArrayList<RE_SEG>();

    boolean getSGroup = false;
    boolean getSClass= false;
    boolean getSTag = false;
    boolean getSWild = false;
    boolean getNewGroup = false;


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (RE_SEG re_seg: res) sb.append(re_seg.toString());
        return sb.toString();
    }

    boolean isValid() {
        return !(getSGroup || getSClass || getSTag || getSWild || getNewGroup);
    }

    @Override
    public void enterWildcard(RegexParser.WildcardContext ctx) {
        if (!isValid()) return;
        String text = ctx.getText();
        //".*" cannot be translate to "<*>" in the context of either re_group or re_class
        if (res.size() > 0 && res.get(res.size() - 1) instanceof RE_LITERALS)
            ((RE_LITERALS)res.get(res.size() - 1)).literals.append(text);
        else res.add(new RE_LITERALS(new StringBuffer(text)));
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
    public void enterS_group(@NotNull RegexParser.S_groupContext ctx) {
        if (!isValid()) return;
        getSGroup = true;
        String group_text = ctx.re_group() == null ? ctx.getText() : ctx.re_group().getText();
        String l_margin_text = ctx.s_group_l_margin() == null ? null : ctx.s_group_l_margin().getText();
        String r_margin_text = ctx.s_group_r_margin() == null ? null : ctx.s_group_r_margin().getText();
        String quant_text = ctx.re_quant() == null ? null : ctx.re_quant().getText();
        res.add(new S_GROUP(group_text, l_margin_text, r_margin_text, quant_text));
    }

    @Override
    public void exitS_group(@NotNull RegexParser.S_groupContext ctx) {
        getSGroup = false;
    }

    @Override
    public void enterS_group_l_margin(@NotNull RegexParser.S_group_l_marginContext ctx) {

    }

    @Override
    public void exitS_group_l_margin(@NotNull RegexParser.S_group_l_marginContext ctx) {

    }

    @Override
    public void enterS_group_r_margin(@NotNull RegexParser.S_group_r_marginContext ctx) {

    }

    @Override
    public void exitS_group_r_margin(@NotNull RegexParser.S_group_r_marginContext ctx) {

    }

    @Override
    public void enterS_group_margin(RegexParser.S_group_marginContext ctx) {

    }

    @Override
    public void exitS_group_margin(RegexParser.S_group_marginContext ctx) {

    }

    @Override
    public void enterS_class(RegexParser.S_classContext ctx) {
        if (!isValid()) return;
        getSClass = true;
        if (res.size() > 0 && !(res.get(res.size() - 1) instanceof S_WILD)) res.add(new S_WILD());
    }

    @Override
    public void exitS_class(RegexParser.S_classContext ctx) {
        getSClass = false;
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
    }

    @Override
    public void exitRe_seq_elem(RegexParser.Re_seq_elemContext ctx) {
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
        if (!isValid()) return;
        getNewGroup = true;
        String txt = ctx.getText();
        if (txt.equals("([0-9.]+)") || txt.equals("([0-9.,]+)") || txt.equals("([0-9.-]+)")) res.add(new RE_LITERALS(new StringBuffer("<??金额>")));
        else if (ctx.getText().equals("(.+)")) res.add(new S_WILD());
        else res.add(new RE_LITERALS(new StringBuffer(ctx.getText())));
    }

    @Override
    public void exitRe_group(RegexParser.Re_groupContext ctx) {
        getNewGroup = false;
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
    public void enterS_wild(@NotNull RegexParser.S_wildContext ctx) {
        if (!isValid()) return;
        getSWild = true;
        String txt = ctx.getText();
        if (txt.equals(".") || txt.equals(".*") || txt.equals(".?") || txt.equals(".+") || txt.equals(".{0") || txt.equals(".{,")) {
            if (res.size() > 0 && res.get(res.size() - 1) instanceof S_WILD) return;
            res.add(new S_WILD());
        }
    }

    @Override
    public void exitS_wild(@NotNull RegexParser.S_wildContext ctx) {
        getSWild = false;
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
        String text = ctx.getText();
        if (text.equals("\\(") || text.equals("\\)")) text = text.substring(1);
        res.add(new RE_LITERALS(new StringBuffer(text)));
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
