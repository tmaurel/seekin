package me.hcl.seekin.Util

import groovy.text.SimpleTemplateEngine
import org.xhtmlrenderer.pdf.ITextRenderer;
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document


class PdfService {

    boolean transactional = false

    def buildPdf(file, model, response, url)
    {

        def reader = file.newReader('UTF-8')

        SimpleTemplateEngine ste = new SimpleTemplateEngine()

        def template = ste.createTemplate( reader )
        ByteArrayOutputStream temp = new ByteArrayOutputStream()
        temp << template.make( model )
        def content = temp.toString()

        InputStream stream = new ByteArrayInputStream(content.getBytes("UTF-8"))
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance()
        documentBuilderFactory.setFeature ("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document doc = builder.parse(stream)


        ITextRenderer renderer = new ITextRenderer()
        renderer.setDocument(doc, url)
        renderer.layout()

        renderer.createPDF response.outputStream

    }

    def removeSpecialCharacters(str)
    {
        return str.replaceAll("[^a-zA-Z]+", "");
    }
}
