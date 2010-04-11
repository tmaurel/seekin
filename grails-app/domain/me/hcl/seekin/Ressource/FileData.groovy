package me.hcl.seekin.Ressource

class FileData {

    private static final int TEN_MEG_IN_BYTES = 1024*1024*10

    static belongsTo = [document:Document]

    byte[] data

    int size

    String extension

    String contentType

    String fileName

    Date dateCreated

    static constraints = {
        data(nullable: false, minSize: 1, maxSize: TEN_MEG_IN_BYTES )
        size(nullable: false)
        dateCreated(nullable: true)
        contentType(nullable: false, validator: { val, obj ->
            return val == "application/pdf" || val == "application/msword"
        })
    }
}
