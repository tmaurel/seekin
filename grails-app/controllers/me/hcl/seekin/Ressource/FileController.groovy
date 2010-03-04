package me.hcl.seekin.Ressource

class FileController {

    def index = { redirect action: download, params: params }

    def download = {
        def fileDataInstance = FileData.get(params.id)
        if (fileDataInstance)
        {
            response.setContentType(fileDataInstance.contentType)
            response.setHeader("Content-Disposition", "attachment; filename=${fileDataInstance.fileName}")
            response.getOutputStream() << new ByteArrayInputStream( fileDataInstance.data )
        }
        else
        {
            render message(code:'file.not.found')
        }

    }
}
