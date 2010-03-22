package me.hcl.seekin.Message

class MessageTagLib {
    
    def renderOriginalMessageStart = {
        out << "----------${message(code:'message.original')}----------"
    }

    def renderOriginalMessageEnd = {
        out << "--------------------"
    }

    def renderMessage = { attrs, body ->

        def content = body()?.trim()?.replaceAll("\n","<br>")
        def startCount = content.count(renderOriginalMessageStart().toString())
        def endCount = content.count(renderOriginalMessageEnd().toString())
        def min = Math.min(startCount, endCount)
        for(int i=0; i<min; ++i)
        {
            content = content.replaceFirst(renderOriginalMessageStart().toString(), "<div class='quoted_message'>")
            content = content.replaceFirst(renderOriginalMessageEnd().toString(), "</div>")
        }
        out << content
        
    }

}
