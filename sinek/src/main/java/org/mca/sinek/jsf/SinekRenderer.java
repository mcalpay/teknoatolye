package org.mca.sinek.jsf;

import com.sun.faces.renderkit.html_basic.TextRenderer;

import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.application.ResourceDependency;
import javax.faces.application.ResourceDependencies;
import java.io.IOException;
import java.util.List;

@FacesRenderer(rendererType = "sinek", componentFamily = "javax.faces.Input")
@ResourceDependencies({
        @ResourceDependency(name = "sinek.css", library = "org.mca", target = "head"),
        @ResourceDependency(name = "jsf.js", library = "javax.faces", target = "body")})
public class SinekRenderer extends Renderer {

    public SinekRenderer() {
        super();
    }

    @Override
    public void encodeBegin(FacesContext facesContext, UIComponent comp) throws IOException {
        UISinek sinek = (UISinek) comp;
        ResponseWriter writer = facesContext.getResponseWriter();
        writer.write("<table id=\"" +sinek.getClientId()+"\" class=\"mctable\">");
        int R = sinek.getWorkSheet().getNumOfColumns();
        renderFooter(writer, R, sinek);
        renderColumnHeaders(writer, R);
        renderCells(facesContext, writer, sinek);
        writer.write("</table>");
    }

    private void renderFooter(ResponseWriter writer, int r, UISinek sinek) throws IOException {
        int i = 0;
        writer.write("<tfoot><tr class=\"footer\">");
        String execute = sinek.getClientId();
        String render = sinek.getClientId();
        writer.write("<th colspan=\"" + (r + 1) + "\">" +
                "<a id=\"" +sinek.getClientId() + "_addr\""+ 
                " href=\"#\" onclick=\"jsf.ajax.request(this,event,{execute:'" +
                execute +
                "',render:'" +
                render +
                "'});return false;\">[Add Row]</a> " +
                "<a id=\"" +sinek.getClientId() + "_addc\""+ 
                " href=\"#\" onclick=\"jsf.ajax.request(this,event,{execute:'" +
                execute +
                "',render:'" +
                render +
                "'});return false;\">[Add Column]</a> " +
                "</th>");
        writer.write("</tr></tfoot>");
    }

    private void renderColumnHeaders(ResponseWriter writer, int r) throws IOException {
        int i = 0;
        char c = 'A';
        writer.write("<tr>");
        while (i <= r) {
            if (i == 0) {
                writer.write("<th></th>");
            } else {
                writer.write("<th>" + c + "</th>");
                c++;
            }

            i++;
        }
        writer.write("</tr>");
    }

    private void renderCells(FacesContext facesContext, ResponseWriter writer, UISinek sinek) throws IOException {
        int i = 0;
        int rno = 1;
        int r = sinek.getWorkSheet().getNumOfColumns();
        List<UIComponent> rows = sinek.getChildren();
        for (UIComponent c : rows) {
            if (i % r == 0) {
                if (i % 2 == 1) {
                    writer.write("<tr class=\"altrow\">");
                } else {
                    writer.write("<tr>");
                }

                rno = renderRowHeader(writer, rno);
            }

            writer.write("<td>");
            c.encodeAll(facesContext);
            c.setRendered(true);
            writer.write("</td>");

            if (i % r == (r - 1)) {
                writer.write("</tr>");
            }

            i++;
        }
    }

    private int renderRowHeader(ResponseWriter writer, int rno) throws IOException {
        writer.write("<th>" + (rno++) + "</th   >");
        return rno;
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

}
