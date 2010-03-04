package me.hcl.seekin.Ressource

import org.springframework.web.multipart.MultipartFile

class FileService {

    boolean transactional = false

    FileData createFile(MultipartFile data)
    {
        def file = new FileData()
        file.size = data.getSize() / 1024
        file.extension = extractExtension(data)
        file.data = data.getBytes()
        file.contentType = data.getContentType()
        file.fileName = data.getOriginalFilename()
        return file
    }

    String extractExtension(MultipartFile data)
    {
        String filename = data.getOriginalFilename()
        return filename.substring(filename.lastIndexOf( "." ) + 1 )
    }



}
